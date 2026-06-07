package ro.tuiasi.pp.lab10.factory

/**
 * Factory producer — returnează factory-ul potrivit în funcție de tipul cerut.
 */
object FactoryProducer {

    /**
     * Returnează factory-ul corespunzător tipului specificat.
     *
     * @param type Tipul factory-ului: `"ELITE"` sau `"HAPPY_WORKER"`
     * @return Factory-ul corespunzător
     * @throws IllegalArgumentException dacă tipul nu este recunoscut
     */
    fun getFactory(type: String): Factory {
        return when (type.uppercase()) {
            "ELITE" -> EliteFactory()
            "HAPPY_WORKER" -> HappyWorkerFactory()
            else -> throw IllegalArgumentException("Tip factory necunoscut: $type")
        }
    }
}