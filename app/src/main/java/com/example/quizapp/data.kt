package com.example.quizapp

import android.util.Log
import java.util.ArrayList
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

object Data {

    const val NAME:String="User"
    const val no_of_questions: String = "no od questions"
    const val Correctans:String="correct answer"
    const val TOT: String="5"
    fun getQuestions(): ArrayList<Questions> {
        val questionsList = ArrayList<Questions>()

        val q1 = Questions(1, "On a dartboard, what number is opposite to No. 1?", "19", "20", "12", "15", 1)
        questionsList.add(q1)
        val q2 = Questions(2, "Virgin Trains,Virgin Atlantic and Virgin Racing are all companies owned by which famous entrepreneur", "Donald Trump", "Richard Branson", "Bill Gates", "Alan Sugar", 2)
        questionsList.add(q2)
        val q5 = Questions(3,"How many Harry Potter books are there?","8","7","5","6",2)
        questionsList.add(q5)
        val q6 = Questions(4,"What is the first book of the old Testament?","Numbers","Genesis","Exodus","Leviticus",2)
        questionsList.add(q6)
        val q4 = Questions(5,"In past times,what would a gentleman keep in his fob pocket?","Keys","Money","Notebook","Watch",4)
        questionsList.add(q4)
        val q7 = Questions(6,"What alcoholic drink is made from molasses?","Gin","Vodka","rum","Whisky",3)
        questionsList.add(q7)
        val q8 = Questions(7,"Area 51 is located in which US state?","Utah","Arizona","Nevada","New Mexico",3)
        questionsList.add(q8)
        val q9 = Questions(8,"which sign of zodiac is represented by crab","Cancer","Virgo","libra","Sagittarius",1)
        questionsList.add(q9)
        val q10 = Questions(9,"The flag of Europea Union has how many starts on it?","16","14","12","10",3)
        questionsList.add(q10)
        val q11 = Questions(10,"How many Furlongs are there in a mile","2","6","8","5",3)
        questionsList.add(q11)
        val q12 = Questions(11,"What is the name of the Jewish Year","Ney year","Succos","Rosh Hashanah","Elul",3)
        //questionsList.add(q12)
        val client = OkHttpClient()
        val request = Request.Builder().url("https://opentdb.com/api.php?amount=10&category=9&difficulty=easy").build()

        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response = client.newCall(request).execute()
                if (!response.isSuccessful) {
                    println("Failed to fetch questions: ${response.code}")
                    return@launch
                }

                val responseBody = response.body?.string()
                val jsonObject = JSONObject(responseBody)
                val resultsArray = jsonObject.getJSONArray("results")

                for (i in 0 until resultsArray.length()) {
                    val questionObject = resultsArray.getJSONObject(i)
                    val question = questionObject.getString("question")
                    val crctanswer = questionObject.getString("correct_answer")
                    val incrct_answer=questionObject.getJSONArray("incorrect_answers")
                    val option1 = if (incrct_answer.length() > 0) incrct_answer.getString(0) else ""
                    val option2 = if (incrct_answer.length() > 1) incrct_answer.getString(1) else ""
                    val option3 = if (incrct_answer.length() > 2) incrct_answer.getString(2) else ""

                    val q4 = Questions(4, question = question, option1, option2, option3, crctanswer, 4)
                    questionsList.add(q4)
                }
            } catch (e: IOException) {
                println("Error occurred while making the API call: ${e.message}")
            }

    }

        return questionsList
    }
}
