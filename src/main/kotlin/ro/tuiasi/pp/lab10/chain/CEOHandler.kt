package ro.tuiasi.pp.lab10.chain

import kotlinx.coroutines.delay

/**
 * Handler-ul de nivel cel mai înalt din lanț — CEO.
 *
 * Inițiază cererea pe lanțul descendent cu formatul `"Request - <mesaj>"`
 * și primește răspunsul final de la baza lanțului.
 *
 * Simulează procesarea asincronă cu `delay()`.
 */
class CEOHandler : Handler {

    override var next: Handler? = null
    override var previous: Handler? = null

    /**
     * Transformă mesajul în cerere formală și o trimite mai departe în lanț.
     *
     * Format mesaj trimis: `"Request - <message>"`
     *
     * @param message Mesajul inițial
     * @return Răspunsul final primit de la baza lanțului
     */
    override suspend fun handleRequest(message: String): String {
        // 1. Simulați procesarea asincronă: delay(100) (100ms)
        delay(100)

        // 2. Formați mesajul cererii: "Request - $message"
        val cerere = "Request - $message"

        // 3. Logați sau printați: println("[CEO] Trimit cererea: $cerere")
        println("[CEO] Trimit cererea: $cerere")

        // 4. Delegați la next: val raspuns = next?.handleRequest(cerere) ?: cerere
        val raspuns = next?.handleRequest(cerere) ?: cerere

        // 5. Logați răspunsul primit: println("[CEO] Am primit răspunsul: $raspuns")
        println("[CEO] Am primit răspunsul: $raspuns")

        // 6. Returnați răspunsul
        return raspuns
    }
}