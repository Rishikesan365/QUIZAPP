package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class Results : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_results)
        val button2: Button=findViewById(R.id.button2)
        val peru:TextView=findViewById(R.id.textView2)
        val score:TextView=findViewById(R.id.textView3)
        val button:Button=findViewById(R.id.button2)
        val totquestions=intent.getIntExtra(Data.no_of_questions,0)

        peru.text=intent.getStringExtra(Data.NAME)
        var point =intent.getIntExtra(Data.Correctans,0)
        score.text="You have scored ${point}/${totquestions}"

        button2.setOnClickListener{
            finish()
        }
    }
}