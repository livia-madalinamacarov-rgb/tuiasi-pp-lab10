package ro.tuiasi.pp.lab10.chain

import kotlinx.coroutines.delay

/**
 * Handler de nivel manager — intermediar între Executive și HappyWorker.
 *
 * Primește cererea de la Executive, o procesează asincron și o transmite
 * mai departe spre HappyWorker.
 */
class ManagerHandler : Handler {

    override var next: Handler? = null
    override var previous: Handler? = null

    /**
     * Procesează cererea și o pasează mai departe în lanț.
     *
     * @param message Mesajul primit de la Executive
     * @return Răspunsul primit de la HappyWorker
     */
    override suspend fun handleRequest(message: String): String {
        // 1. Simulați procesarea: delay(100)
        delay(100)

        // 2. Logați primirea: println("[Manager] Am primit: $message")
        println("[Manager] Am primit: $message")

        // 3. Delegați la next: val raspuns = next?.handleRequest(message) ?: message
        val raspuns = next?.handleRequest(message) ?: message

        // 4. Logați răspunsul: println("[Manager] Răspuns: $raspuns")
        println("[Manager] Răspuns: $raspuns")

        // 5. Returnați răspunsul
        return raspuns
    }
}