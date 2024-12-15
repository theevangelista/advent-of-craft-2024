package communication

import doubles.TestLogger
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class SantaCommunicatorTest : DescribeSpec({
    val numberOfDaysToRest = 2
    val christmas = SantaCommunicator.Christmas(24, numberOfDaysToRest)
    val logger = TestLogger()
    lateinit var communicator: SantaCommunicator

    beforeEach {
        communicator = SantaCommunicator(christmas)
    }

    describe("composeMessage") {
        it("should compose correct message") {
            communicator
                .composeMessage(
                    SantaCommunicator.Reindeer(
                        "Dasher",
                        "North Pole",
                        5,
                    ),
                ) shouldBe "Dear Dasher, please return from North Pole in 17 day(s) to be ready and rest before Christmas."
        }
    }

    describe("isOverdue") {
        it("should detect overdue reindeer") {
            val overdue = communicator.isOverdue(
                SantaCommunicator.Reindeer(
                    "Dasher",
                    "North Pole",
                    christmas.numberOfDaysBeforeChristmas,
                ),
                logger
            )

            overdue shouldBe true
            logger.getLog() shouldBe "Overdue for Dasher located North Pole."
        }

        it("should return false when not overdue") {
            communicator.isOverdue(
                SantaCommunicator.Reindeer(
                    "Dasher",
                    "North Pole",
                    numberOfDaysToRest - christmas.numberOfDaysBeforeChristmas - 1,
                ),
                logger
            ) shouldBe false
        }
    }
})
