package ro.tuiasi.pp.lab10.factory

import ro.tuiasi.pp.lab10.chain.CEOHandler
import ro.tuiasi.pp.lab10.chain.Handler

/**
 * Factory pentru crearea handler-elor de nivel elit (CEO).
 */
class EliteFactory : Factory {

    /**
     * Creează și returnează un [CEOHandler].
     *
     * @return Un handler de tip CEO
     */
    override fun createHandler(): Handler {
        return CEOHandler()
    }
}