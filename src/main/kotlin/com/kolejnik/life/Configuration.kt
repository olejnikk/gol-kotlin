package com.kolejnik.life


class Configuration(config: String) {

    private val parts = config.split("/")
    val stayAliveNeighbours = stringToSet(parts[0])
    val deadToAliveNeighbours = stringToSet(parts[1])

    private fun stringToSet(text: String): Set<Int> {
        val set = HashSet<Int>()
        for (digit in text) {
            set.add(Character.getNumericValue(digit))
        }
        return set
    }

}