package com.example.quizapp

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import com.example.quizapp.R.id

class TriviaQuestionsActivity : AppCompatActivity() , View.OnClickListener{
    private var position :Int =1
    private var mquestionlist:ArrayList<Questions>?=null
    private var mquestionlist2:ArrayList<Questions>?=null
    private  var mselected : Int=0
    private var mname:String?=null
    private var mcrct:Int=0
    private var progressBar:ProgressBar? =null
    private var progress: TextView?=null
    private var tvquestion: TextView?=null
    private var opt1: TextView?=null
    private var opt2: TextView?=null
    private var opt3: TextView?=null
    private var opt4: TextView?=null
    private var btnok: Button?=null
    //private var total:Int=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trivia_questions)
        //total = intent.getStringExtra(Data.tot)?.toIntOrNull() ?: 0
        //Log.e("TAG","HI$total")
       // mquestionlist2 = ArrayList(Data.getQuestions().take(total))
       mquestionlist=Data.getQuestions()
       //mquestionlist= ArrayList(mquestionlist2!!.take(total))
        setQuestion()
    }

    private fun setQuestion() {
        mname=intent.getStringExtra(Data.NAME)
        progressBar = findViewById(id.probress_bar)
        progress = findViewById(id.progress)
        tvquestion = findViewById(id.disp_question)
        opt1 = findViewById(id.opt1)
        opt2 = findViewById(id.opt2)
        opt3 = findViewById(id.opt3)
        opt4 = findViewById(id.opt4)
        btnok=findViewById(id.ok)
        opt1?.setTextColor(Color.BLACK)
        opt1?.setBackgroundColor(Color.WHITE)
        opt2?.setTextColor(Color.BLACK)
        opt2?.setBackgroundColor(Color.WHITE)
        opt3?.setTextColor(Color.BLACK)
        opt3?.setBackgroundColor(Color.WHITE)
        opt4?.setTextColor(Color.BLACK)
        opt4?.setBackgroundColor(Color.WHITE)
        opt1?.setOnClickListener(this)
        opt2?.setOnClickListener(this)
        opt3?.setOnClickListener(this)
        opt4?.setOnClickListener(this)
        btnok?.setOnClickListener(this)
        val question: Questions = mquestionlist!![position-1]
        progressBar?.progress = position
        progress?.text = "${position}/${mquestionlist?.size}"
        progressBar?.max=mquestionlist?.size?:0
        tvquestion?.text = question.question
        opt1?.text = question.Option_1
        opt2?.text = question.Option_2
        opt3?.text = question.Option_3
        opt4?.text = question.Option_4
        if(position==mquestionlist!!.size){
            btnok?.text="SUBMIT"
        }
        else{
            btnok?.text="OK"
        }

    }
private fun selected_optionview(tv: View?, selectedoption:Int){
    mselected=selectedoption
}
override fun onClick(v: View?){
        when(v?.id){
            id.opt1->{opt1?.let{
                selected_optionview(it, 1)
                opt1?.setBackgroundColor(Color.GRAY)
                checkAnswer(1)
            }}
            id.opt2->{opt2?.let {
                selected_optionview(it, 2)
                opt2?.setBackgroundColor(Color.GRAY)
                checkAnswer(2)
            }}
            id.opt3->{opt3?.let {
                selected_optionview(it, 3)
                opt3?.setBackgroundColor(Color.GRAY)
                checkAnswer(3)
            }}
            id.opt4->{opt4?.let {
                selected_optionview(it, 4)
                opt4?.setBackgroundColor(Color.GRAY)
                checkAnswer(4)
            }}

    id.ok->{
        if(mselected== 0){
            position++
            when{
                position<= mquestionlist!!.size->{
                    setQuestion()
                }
                else->{
                    val intent = Intent(this,Results::class.java)
                    intent.putExtra(Data.NAME,mname)
                    intent.putExtra(Data.Correctans,mcrct)
                    intent.putExtra(Data.no_of_questions,mquestionlist?.size)
                    startActivity(intent)
                    finish()
                }

            }
        }else{
                val questions=mquestionlist?.get(position-1)
            if(questions!!.Crct != mselected){
                when (mselected) {
                    1 -> {
                        opt1?.setTextColor(Color.WHITE)
                        opt1?.setBackgroundColor(Color.RED)
                    }

                    2 -> {
                        opt2?.setTextColor(Color.WHITE)
                        opt2?.setBackgroundColor(Color.RED)

                    }
                    3 -> {opt3?.setTextColor(Color.WHITE)
                        opt3?.setBackgroundColor(Color.RED)}
                    4 -> {
                        opt4?.setTextColor(Color.WHITE)
                        opt4?.setBackgroundColor(Color.RED)
                    }
                }

            }else{
                when (mselected) {
                    1 -> {opt1?.setTextColor(Color.WHITE)
                        opt1?.setBackgroundColor(Color.GREEN)}
                    2 -> {opt2?.setTextColor(Color.WHITE)
                        opt2?.setBackgroundColor(Color.GREEN)}
                    3 -> {opt3?.setTextColor(Color.WHITE)
                        opt3?.setBackgroundColor(Color.GREEN)}
                    4 -> {opt4?.setTextColor(Color.WHITE)
                        opt4?.setBackgroundColor(Color.GREEN)}
                }
                mcrct++
            }

            if(position==mquestionlist!!.size){
                btnok?.text="FINISH"
            }else{
                btnok?.text="Next Question"
            }
            mselected=0
        }
    }
    }
}


    private fun checkAnswer(selectedOption: Int) {
        val question = mquestionlist?.get(position - 1)
        if (question!!.Crct != selectedOption) {
            // Incorrect answer
            when (question.Crct) {
                1 -> {
                    opt1?.setTextColor(Color.WHITE)
                    opt1?.setBackgroundColor(Color.GREEN)
                }
                2 -> {opt2?.setTextColor(Color.WHITE)
                    opt2?.setBackgroundColor(Color.GREEN)}
                3 -> {opt3?.setTextColor(Color.WHITE)
                    opt3?.setBackgroundColor(Color.GREEN)}
                4 -> {opt4?.setTextColor(Color.WHITE)
                    opt4?.setBackgroundColor(Color.GREEN)}
            }

        }
        }

}