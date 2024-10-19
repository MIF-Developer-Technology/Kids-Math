package com.mifdev.mathgame

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.Locale
import kotlin.random.Random

class GameActivity4 : AppCompatActivity() {

    lateinit var textScore : TextView
    lateinit var textLife : TextView
    lateinit var textTime : TextView

    lateinit var textQuestion : TextView
    lateinit var editTextAnswer : EditText

    lateinit var buttonOk : Button
    lateinit var buttonNext : Button

    var correctAnswer = 0
    var userScore = 0
    var userLife = 3

    lateinit var timer : CountDownTimer
    private val startTimerInMillis : Long = 20000
    var timeLeftInMillis : Long = startTimerInMillis

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game2)

        supportActionBar!!.title = "Distribution"

        textScore = findViewById(R.id.textViewScore)
        textLife = findViewById(R.id.textViewLife)
        textTime = findViewById(R.id.textViewTime)
        textQuestion = findViewById(R.id.textViewQuestion)
        editTextAnswer = findViewById(R.id.editTextAnswer)
        buttonOk = findViewById(R.id.buttonOk)
        buttonNext = findViewById(R.id.buttonNext)

        gameContinue()

        buttonOk.setOnClickListener {

            val input = editTextAnswer.text.toString()

            if(input == "")
            {
                Toast.makeText(applicationContext, "Please write an answer or click on the next button",
                    Toast.LENGTH_LONG).show()
            }
            else{

                pauseTimer()

                val userAnswer = input.toInt()

                if (userAnswer == correctAnswer)
                {

                    userScore += 10
                    textQuestion.text = "Congratulations! Your answer is correct."
                    textScore.text = userScore.toString()

                }
                else
                {

                    userLife -= 1
                    textQuestion.text = "Sorry! Your answer is wrong."
                    textLife.text = userLife.toString()

                }

            }

        }
        buttonNext.setOnClickListener {

            pauseTimer()
            resetTimer()

            editTextAnswer.setText("")

            if (userLife == 0)
            {

                Toast.makeText(applicationContext, "Game Over!", Toast.LENGTH_LONG).show()
                val intent = Intent(this@GameActivity4,ResultActivity::class.java)
                intent.putExtra("score",userScore)
                startActivity(intent)
                finish()

            }
            else
            {

                gameContinue()

            }

        }

    }

    fun gameContinue()
    {
        val number1 = Random.nextInt(0,100)
        var number2 = Random.nextInt(0,100)

        while (number2 == 0) { // Ensure number2 is not zero
            number2 = Random.nextInt(0, 100)
        }

        textQuestion.text = "$number1 / $number2"
        correctAnswer = number1 / number2

        startTimer()

    }

    fun startTimer()
    {

        timer = object : CountDownTimer(timeLeftInMillis,1000){
            override fun onTick(millisUntilFinished: Long) {

                timeLeftInMillis = millisUntilFinished
                updateText()

            }

            override fun onFinish() {

                pauseTimer()
                resetTimer()
                updateText()

                userLife--
                textLife.text = userLife.toString()
                textQuestion.text = "Sorry! Your time is up."

            }

        }.start()
    }

    fun updateText()
    {
        val remainingTime : Int = (timeLeftInMillis/1000).toInt()
        textTime.text = String.format(Locale.getDefault(),"%02d",remainingTime)
    }

    fun pauseTimer()
    {
        timer.cancel()
    }

    fun resetTimer()
    {
        timeLeftInMillis = startTimerInMillis
        updateText()
    }

}