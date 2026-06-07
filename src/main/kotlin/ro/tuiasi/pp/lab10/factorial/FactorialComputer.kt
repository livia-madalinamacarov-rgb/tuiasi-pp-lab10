package ro.tuiasi.pp.lab10.factorial

import kotlinx.coroutines.async
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

/**
 * Modul de calcul factorial paralel folosind corutine și canale.
 */

/**
 * Calculează factorial pentru fiecare valoare din [values] folosind 4 corutine paralele.
 *
 * @param values Lista de valori pentru care se calculează factorial
 * @return Map cu perechile n → n!
 */
suspend fun computeFactorials(values: List<Int>): Map<Int, Long> = coroutineScope {
    // 1. Creați un canal de lucru: val canal = Channel<Int>(capacity = values.size)
    val canal = Channel<Int>(capacity = values.size)

    // 2. Trimiteți toate valorile în canal și închideți-l:
    launch {
        for (v in values) canal.send(v)
        canal.close()
    }

    // 3. Creați un map thread-safe pentru rezultate:
    val rezultate = java.util.concurrent.ConcurrentHashMap<Int, Long>()

    // 4. Lansați 4 corutine consumatoare:
    val joburi = (1..4).map {
        async {
            for (n in canal) {
                rezultate[n] = factorial(n)
            }
        }
    }

    // 5. Așteptați terminarea tuturor corutinelor:
    joburi.forEach { it.await() }

    // 6. Returnați rezultatele:
    rezultate
}

/**
 * Calculează n! (factorial) pentru un număr natural n.
 *
 * @param n Numărul pentru care se calculează factorial (n >= 0)
 * @return Valoarea n! ca Long
 */
fun factorial(n: Int): Long {
    if (n < 0) throw IllegalArgumentException("Numărul trebuie să fie pozitiv sau zero.")
    // Utilizăm fold pentru a calcula iterativ și elegant factorialul
    return (1..n).fold(1L) { acc, i -> acc * i }
}