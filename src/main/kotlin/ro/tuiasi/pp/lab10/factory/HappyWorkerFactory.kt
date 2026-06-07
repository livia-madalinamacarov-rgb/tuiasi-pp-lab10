package ro.tuiasi.pp.lab10.factory

import ro.tuiasi.pp.lab10.chain.HappyWorkerHandler
import ro.tuiasi.pp.lab10.chain.Handler

/**
 * Factory pentru crearea handler-elor de nivel worker.
 */
class HappyWorkerFactory : Factory {

    /**
     * Creează și returnează un [HappyWorkerHandler].
     *
     * @return Un handler de tip HappyWorker
     */
    override fun createHandler(): Handler {
        return HappyWorkerHandler()
    }
}