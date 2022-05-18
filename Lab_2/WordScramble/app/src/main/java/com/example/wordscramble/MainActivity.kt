package com.example.wordscramble

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.wordscramble.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setNewWord()
    }

    private fun getRandomScrambleWord(): String {
        val words = resources.getStringArray(R.array.words)
        return words[Random.nextInt(words.size)]
    }

    private fun setNewWord() {
        binding.inputField.text.clear()
        val word = getRandomScrambleWord()
        binding.scrambleWord.text = word.toCharArray().let{
            it.shuffle()
            it.concatToString()
        }
        binding.checkButton.setOnClickListener {

            val userWord = binding.inputField.text.toString()

            if (userWord == word) {
                Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show()
                setNewWord()
            } else {
                Toast.makeText(this, "Nope, try again!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}