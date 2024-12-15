package games

import arrow.core.None
import arrow.core.Option
import arrow.core.Some


data class Word(val value: Int, val name: String)
class WordConfiguration(private val words: List<Word>, val allowedRange: IntRange) {

    fun getWords(input: Int): String? {
        if (isOutOfRange(input)) return null
        return sortedWords()
            .filter { word -> input % word.value == 0 }
            .joinToString("") { word -> word.name }
            .let { if (it.isEmpty()) input.toString() else it }
    }

    private fun isOutOfRange(input: Int): Boolean = input !in allowedRange

    private fun sortedWords(): List<Word> = words.sortedBy { it.value }
}


object FizzBuzz {
    val knownWords = listOf(
        Word(3, "Fizz"),
        Word(5, "Buzz"),
        Word(7, "Whizz"),
        Word(11, "Bang"),
    )

    fun convert(input: Int, configuration: WordConfiguration): Option<String> = when (val result = configuration.getWords(input)) {
        null -> None
        else -> Some(result)
    }


}