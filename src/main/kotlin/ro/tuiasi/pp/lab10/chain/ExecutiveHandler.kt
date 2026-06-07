package ro.tuiasi.pp.lab10.chain

import kotlinx.coroutines.delay

/**
 * Handler de nivel executiv — intermediar între CEO și Manager.
 *
 * Primește cererea de la CEO, o procesează asincron și o transmite
 * mai departe spre Manager. Răspunsul primit este transmis înapoi.
 */
class ExecutiveHandler : Handler {

    override var next: Handler? = null
    override var previous: Handler? = null

    /**
     * Procesează cererea și o pasează mai departe în lanț.
     *
     * @param message Mesajul primit de la CEO
     * @return Răspunsul primit de la handler-ul următor
     */
    override suspend fun handleRequest(message: String): String {
        // 1. Simulați procesarea: delay(100)
        delay(100)

        // 2. Logați primirea cererii: println("[Executive] Am primit: $message")
        println("[Executive] Am primit: $message")

        // 3. Delegați la next: val raspuns = next?.handleRequest(message) ?: message
        val raspuns = next?.handleRequest(message) ?: message

        // 4. Logați răspunsul: println("[Executive] Răspuns: $raspuns")
        println("[Executive] Răspuns: $raspuns")

        // 5. Returnați răspunsul
        return raspuns
    }
}