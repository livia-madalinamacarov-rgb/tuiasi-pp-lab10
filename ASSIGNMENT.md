# Lab 10 — Kotlin Corutine (kotlinx.coroutines)

## Obiective

- Familiarizarea cu corutinele Kotlin (`kotlinx.coroutines`)
- Utilizarea `launch`, `async`, `await`, `delay` pentru programare asincronă
- Canale Kotlin (`Channel<T>`) pentru comunicare între corutine
- Integrarea corutinelor cu pattern-urile Chain of Responsibility și Abstract Factory
- Procesare paralelă cu corutine multiple

---

## Tema 1 — Double Chain of Responsibility + Abstract Factory cu corutine

### Cerință

Implementați pattern-ul Double Chain of Responsibility cu corutine Kotlin.
Fiecare handler este `suspend fun` și folosește `launch` sau `async` intern.

### Structura handler-elor

Lanțul de handler-e (de la top la bottom):
```
CEOHandler → ExecutiveHandler → ManagerHandler → HappyWorkerHandler
```

Fiecare handler:
1. Primește cererea pe lanțul descendent
2. Procesează asincron cu `delay()` (simulare procesare)
3. Pasează răspunsul pe lanțul ascendent înapoi la CEO

### Interfață de bază

```kotlin
interface Handler {
    var next: Handler?
    var previous: Handler?
    suspend fun handleRequest(message: String): String
}
```

### Handler-e de implementat (pachet `ro.tuiasi.pp.lab10.chain`)

| Clasă | Comportament |
|-------|-------------|
| `CEOHandler` | Pornește cererea: `"Request - <mesaj>"`, primește răspunsul final |
| `ExecutiveHandler` | Procesează și pasează mai departe |
| `ManagerHandler` | Procesează și pasează mai departe |
| `HappyWorkerHandler` | Procesează cererea finală: `"Response - <mesaj>"` |

### Factory (pachet `ro.tuiasi.pp.lab10.factory`)

```kotlin
interface Factory {
    fun createHandler(): Handler
}

class EliteFactory : Factory      // Creează CEOHandler
class HappyWorkerFactory : Factory // Creează HappyWorkerHandler

object FactoryProducer {
    fun getFactory(type: String): Factory
}
```

---

## Tema 2 — Pipeline ADT cu corutine

### Cerință

Implementați o procesare în pipeline a unui vector de întregi folosind canale Kotlin (`Channel<Int>`).

### Pipeline (3 etape)

```
Input → [Etapa 1: înmulțire × alpha] → [Etapa 2: sortare] → [Etapa 3: afișare] → Output
```

### Clasă de implementat (pachet `ro.tuiasi.pp.lab10.pipeline`)

```kotlin
class PipelineProcessor {
    fun process(input: List<Int>, alpha: Int): List<Int>
}
```

### Cerințe tehnice

- Etapa 1: produce elementele înmulțite în `Channel<Int>` folosind `launch`
- Etapa 2: consumă din etapa 1, colectează toate, sortează, produce în alt canal
- Etapa 3: consumă din etapa 2 și returnează rezultatele
- Folosiți `runBlocking` + `Channel` cu `BUFFERED` sau capacitate explicită

---

## Tema 3 — Factorial paralel cu 4 corutine

### Cerință

Calculați factorial pentru mai multe valori simultan, folosind 4 corutine consumatoare și un `Channel` ca coadă de lucru.

### Funcție de implementat (pachet `ro.tuiasi.pp.lab10.factorial`)

```kotlin
suspend fun computeFactorials(values: List<Int>): Map<Int, Long>
```

### Arhitectură

- Un `Channel<Int>` în care se trimit toate valorile din `values`
- 4 corutine `async { }` care consumă din canal și calculează factorial
- Rezultatele se colectează într-un `Map<Int, Long>`

### Exemplu

```
computeFactorials(listOf(5, 6, 7, 8)) → {5=120, 6=720, 7=5040, 8=40320}
```

---

## Criterii de evaluare

| Criteriu | Puncte |
|---------|--------|
| Tema 1 — Chain + Factory cu corutine | 35p |
| Tema 2 — Pipeline cu canale | 30p |
| Tema 3 — Factorial paralel | 35p |

**Total: 100 puncte**

---

## Rulare teste

```bash
gradle test
```
