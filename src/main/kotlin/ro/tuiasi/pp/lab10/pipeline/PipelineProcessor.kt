package ro.tuiasi.pp.lab10.pipeline

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * Procesor pipeline pentru liste de întregi, implementat cu canale Kotlin.
 *
 * Pipeline-ul are 3 etape conectate prin canale:
 * 1. **Înmulțire**: fiecare element × alpha
 * 2. **Sortare**: colectarea tuturor elementelor și sortarea lor
 * 3. **Colectare**: returnarea listei finale
 *
 * Etapele rulează concurent în corutine separate, communicate prin `Channel<Int>`.
 */
class PipelineProcessor {

    /**
     * Procesează lista [input] prin pipeline-ul de 3 etape.
     *
     * @param input Lista de întregi de procesat
     * @param alpha Factorul de înmulțire pentru etapa 1
     * @return Lista rezultat după înmulțire și sortare
     */
    fun process(input: List<Int>, alpha: Int): List<Int> = runBlocking {
        // Etapa 1 → Etapa 2: canal de înmulțire
        val canalInmultire = Channel<Int>(Channel.BUFFERED)

        // Corutina producătoare (Etapa 1):
        launch {
            for (element in input) {
                canalInmultire.send(element * alpha)
            }
            canalInmultire.close()
        }

        // Etapa 2 → Etapa 3: canal de sortare
        val canalSortat = Channel<Int>(Channel.BUFFERED)

        // Corutina de sortare (Etapa 2):
        launch {
            val listaTemp = mutableListOf<Int>()
            for (element in canalInmultire) {
                listaTemp.add(element)
            }
            listaTemp.sort()
            for (element in listaTemp) {
                canalSortat.send(element)
            }
            canalSortat.close()
        }

        // Etapa 3 — colectare rezultat:
        val rezultat = mutableListOf<Int>()
        for (element in canalSortat) {
            rezultat.add(element)
        }

        rezultat
    }
}