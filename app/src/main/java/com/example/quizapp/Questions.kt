package com.example.quizapp

data class Questions (
    val id:Int,
    val question:String,
    val Option_1:String,
    val Option_2:String,
    val Option_3:String,
    val Option_4:String,
    val Crct: Int)
data class Org_Question(
    val category: String,
    val question: String,
    val correctAnswer: String,
    val incorrectAnswers: List<String>
)

