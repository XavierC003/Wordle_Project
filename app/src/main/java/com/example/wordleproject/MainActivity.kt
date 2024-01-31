package com.example.wordleproject

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isInvisible

class MainActivity : AppCompatActivity() {
    var wordToGuess = FourLetterWordList.getRandomFourLetterWord()
    val maxGuesses = 3
    var numGuesses = maxGuesses
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var button = findViewById<Button>(R.id.button)
        var guesses = findViewById<TextView>(R.id.guesses)
        var input = findViewById<EditText>(R.id.input)
        var guessString = input.text.toString()
        var correct = findViewById<TextView>(R.id.textView)


        button.setOnClickListener {
            if (numGuesses > 0) {
                input = findViewById<EditText>(R.id.input)
                guessString = input.text.toString().uppercase()
                if (guessString.length == 4) {
                    guesses.text = "" + guesses.text + guessString + " " + checkGuess(guessString) + " "
                    numGuesses--
                    input.text.clear()
                    hideSoftKeyboard(guesses)
                } else {
                    Toast.makeText(it.context, "Guess must be 4 letters long", Toast.LENGTH_SHORT)
                        .show()
                }
            }
            if (numGuesses <= 0 || wordToGuess.equals(guessString)) {
                button.isInvisible = true
                correct.isInvisible = false
                correct.text = "" + correct.text + wordToGuess
            }
        }
    }

    fun hideSoftKeyboard(view: View) {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }


    private fun checkGuess(guess: String): String {
        var result = ""
        for (i in 0..3) {
            if (guess[i] == wordToGuess[i]) {
                result += "O"
            } else if (guess[i] in wordToGuess) {
                result += "+"
            } else {
                result += "X"
            }
        }
        return result
    }
}