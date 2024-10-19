package com.mifdev.mathgame

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    lateinit var addition : Button
    lateinit var subtraction : Button
    lateinit var multiplication : Button
    lateinit var distribution : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        addition = findViewById(R.id.buttonAdd)
        subtraction = findViewById(R.id.buttonSub)
        multiplication = findViewById(R.id.buttonMulti)
        distribution = findViewById(R.id.buttonDistri)

        addition.setOnClickListener {
            val intent = Intent(this@MainActivity, GameActivity::class.java)
            startActivity(intent)
        }

        subtraction.setOnClickListener {
            val intent = Intent(this@MainActivity, GameActivity2::class.java)
            startActivity(intent)
        }

        multiplication.setOnClickListener {
            val intent = Intent(this@MainActivity, GameActivity3::class.java)
            startActivity(intent)
        }

        distribution.setOnClickListener {
            val intent = Intent(this@MainActivity, GameActivity4::class.java)
            startActivity(intent)
        }

    }
}