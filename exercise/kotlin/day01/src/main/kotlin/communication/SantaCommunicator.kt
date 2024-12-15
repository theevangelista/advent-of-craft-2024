package communication

class SantaCommunicator(private val christmas: Christmas) {

    data class Reindeer(val name: String, val currentLocation: String, val daysToComeback: Int)
    data class Christmas(val numberOfDaysBeforeChristmas: Int, val restDays: Int = 2) {
        fun daysBeforeReturn(daysToComeback: Int): Int {
            return numberOfDaysBeforeChristmas - daysToComeback - restDays
        }
    }

    fun composeMessage(
        reindeer: Reindeer,
    ): String {
        val daysBeforeReturn = christmas.daysBeforeReturn(reindeer.daysToComeback)
        val (name, currentLocation) = reindeer
        return "Dear $name, please return from $currentLocation in $daysBeforeReturn day(s) to be ready and rest before Christmas."
    }

    fun isOverdue(
        reindeer: Reindeer,
        logger: Logger
    ): Boolean {
        val daysBeforeReturn = christmas.daysBeforeReturn(reindeer.daysToComeback)
        if (daysBeforeReturn <= 0) {
            val (reindeerName, currentLocation) = reindeer
            logger.log("Overdue for $reindeerName located $currentLocation.")
            return true
        }
        return false
    }
}