package com.example.example.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.example.entity.QuestionResponse

@Dao
interface QuestionDAO {
    @Insert
    suspend fun insertQuestion(entity: QuestionResponse)

    @Query("SELECT * FROM questionresponse WHERE id = :questionId")
    suspend fun getQuestion(questionId: Int): QuestionResponse

    @Query("SELECT * FROM questionresponse")
    suspend fun getAllQuestions(): MutableList<QuestionResponse>
}