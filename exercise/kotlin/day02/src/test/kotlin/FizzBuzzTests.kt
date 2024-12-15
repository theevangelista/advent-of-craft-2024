import games.FizzBuzz
import games.FizzBuzz.knownWords
import games.WordConfiguration
import io.kotest.assertions.arrow.core.shouldBeNone
import io.kotest.assertions.arrow.core.shouldBeSome
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData

class FizzBuzzTests : FunSpec({
    val configuration = WordConfiguration(knownWords, 1..100)
    context("returns its numbers representation") {
        withData(
            ValidInput(1, "1"),
            ValidInput(67, "67"),
            ValidInput(82, "82"),
            ValidInput(3, "Fizz"),
            ValidInput(99, "FizzBang"),
            ValidInput(33, "FizzBang"),
            ValidInput(35, "BuzzWhizz"),
            ValidInput(70, "BuzzWhizz"),
            ValidInput(5, "Buzz"),
            ValidInput(50, "Buzz"),
            ValidInput(85, "Buzz"),
            ValidInput(15, "FizzBuzz"),
            ValidInput(30, "FizzBuzz"),
            ValidInput(45, "FizzBuzz"),
            ValidInput(7, "Whizz"),
            ValidInput(14, "Whizz"),
            ValidInput(11, "Bang"),
            ValidInput(22, "Bang"),
            ValidInput(77, "WhizzBang"),

        ) { (input, expectedResult) ->
            FizzBuzz.convert(input, configuration).shouldBeSome(expectedResult).also { println("input: $input, expected: $expectedResult") }
        }
    }

    context("fails for numbers out of range") {
        withData(0, -1, 101) { x ->
            FizzBuzz.convert(x, configuration).shouldBeNone()
        }
    }
})

data class ValidInput(val input: Int, val expectedResult: String)