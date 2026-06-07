package ro.tuiasi.pp.lab10.chain

import kotlinx.coroutines.delay

/**
 * Handler de la baza lanțului — HappyWorker.
 *
 * Procesează cererea finală și generează răspunsul cu formatul `"Response - <mesaj>"`.
 * Nu delegă mai departe (este ultimul în lanț).
 */
class HappyWorkerHandler : Handler {

    override var next: Handler? = null
    override var previous: Handler? = null

    /**
     * Procesează cererea finală și generează răspunsul.
     *
     * Format răspuns: `"Response - <message>"`
     *
     * @param message Mesajul primit de la Manager
     * @return Răspunsul generat de worker
     */
    override suspend fun handleRequest(message: String): String {
        // 1. Simulați procesarea: delay(200) (lucrătorul are nevoie de mai mult timp)
        delay(200)

        // 2. Logați primirea: println("[HappyWorker] Procesez: $message")
        println("[HappyWorker] Procesez: $message")

        // 3. Generați răspunsul: "Response - $message"
        val raspuns = "Response - $message"

        // 4. Logați răspunsul: println("[HappyWorker] Răspund: $raspuns")
        println("[HappyWorker] Răspund: $raspuns")

        // 5. Returnați răspunsul (nu delegați la next!)
        return raspuns
    }
}