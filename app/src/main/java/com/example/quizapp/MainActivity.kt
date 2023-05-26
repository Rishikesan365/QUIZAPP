package com.example.quizapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.integerArrayResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.quizapp.ui.theme.QUIZAPPTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button1: Button = findViewById(R.id.button1)
        val nme : EditText =findViewById(R.id.nme)

        val spinner = findViewById<Spinner>(R.id.spinner) // Replace R.id.spinner with the ID of your Spinner


        button1.setOnClickListener {
            if (nme.text.isEmpty()) {
                Toast.makeText(this, "Name field can't be empty", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, TriviaQuestionsActivity::class.java)
                intent.putExtra(Data.NAME, nme.text.toString())
                spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        val selectedItem = parent?.getItemAtPosition(position)
                        val totalQuestions = if (selectedItem is String) {
                            selectedItem.toIntOrNull() ?: 5
                        } else {
                            5
                        }
                        intent.putExtra(Data.TOT,5)
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {
                        intent.putExtra(Data.TOT, 5)
                    }
                }
                val f = intent.getIntExtra(Data.TOT, 0)
                Log.e("TAG", "HI $f")
                startActivity(intent)
                finish()
            }
        }
    }

}