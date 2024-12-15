import games.FizzBuzz
import games.FizzBuzz.knownWords
import games.WordConfiguration
import io.kotest.core.spec.style.StringSpec
import io.kotest.property.Arb
import io.kotest.property.arbitrary.filter
import io.kotest.property.arbitrary.int
import io.kotest.property.forAll

val fizzBuzzStrings  = listOf("Fizz", "Buzz", "Whizz", "Bang", "FizzBuzz", "FizzWhizz", "FizzBang", "BuzzWhizz", "BuzzBang", "WhizzBang")
fun validStringsFor(x: Int): List<String> = fizzBuzzStrings + x.toString()

class FizzBuzzProperties : StringSpec({
    val configuration = WordConfiguration(knownWords, 1..100)
    val allowedRange =  configuration.allowedRange
    "parse return a valid string for numbers between 1 and 100" {

        forAll(Arb.int(allowedRange)) { x ->
            FizzBuzz.convert(x, configuration).isSome { result -> validStringsFor(x).contains(result) }
        }
    }

    "parse fail for numbers out of range" {
        forAll(Arb.int().filter { i -> i !in allowedRange}) { x ->
            FizzBuzz.convert(x, configuration).isNone()
        }
    }
})