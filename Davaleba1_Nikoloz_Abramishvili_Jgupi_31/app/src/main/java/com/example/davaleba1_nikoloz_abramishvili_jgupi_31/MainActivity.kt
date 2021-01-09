package com.example.davaleba1_nikoloz_abramishvili_jgupi_31

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.*


class MainActivity : AppCompatActivity() {
    var MAX_TRY = 5
    var tries = 0
    var DEFAULT_LIMIT = 20
    var limit = 20
    var randomNumber = 0
    private fun generateNumber() {
        val numberLimitEditText = findViewById<View>(R.id.numberLimit) as EditText
        if (!numberLimitEditText.text.toString().isEmpty()) {
            limit = numberLimitEditText.text.toString().toInt()
        } else {
            limit = DEFAULT_LIMIT

        }
        tries = MAX_TRY
        updateTextTries()
        val rand = Random()
        randomNumber = rand.nextInt(limit) + 1
    }

    private fun updateTextTries() {
        val textViewTries = findViewById<View>(R.id.textViewTries) as TextView
        textViewTries.text = "Tries - $tries"
    }

    fun restartGame(view: View) {
        generateNumber()
        val button = view as Button
        button.visibility = View.INVISIBLE
    }

    fun onPlayGame(view: View?) {
        val editTextNumber = findViewById<View>(R.id.editTextNumber) as EditText
        val editTextName = findViewById<View>(R.id.editTextTextName) as EditText
        val restartGameBtn = findViewById<View>(R.id.restartGameBtn) as Button
        restartGameBtn.translationX = 2000f

//        Button playButton = (Button) view;
        Log.i("User_NUMBER", editTextNumber.text.toString())
        val message: String
        if (editTextNumber.text.toString().isEmpty()) {
            message = "Please enter the number"
        } else {
            tries--
            if (tries >= 0) {
                updateTextTries()
                val userNumber = editTextNumber.text.toString().toInt()
                if (userNumber < randomNumber) {
                    message = "Try Higher"
                } else if (userNumber > randomNumber) {
                    message = "Try Lower"
                } else {
                    message = if (tries == 0) {
                        editTextName.text.toString()+" You Won!. Your Point is - 5"
                    } else {
                        editTextName.text.toString()+" You Won!. Your Point is - " + tries * 10
                    }
                    restartGameBtn.animate().translationX(0f).duration = 300
                    restartGameBtn.visibility = View.VISIBLE
                    editTextNumber.setText("")
                }
            } else {
                message = "Try again."
                restartGameBtn.animate().translationX(0f).rotation(3600f).duration = 300
                restartGameBtn.visibility = View.VISIBLE
                editTextNumber.setText("")
            }
        }
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        generateNumber()
    }
}
