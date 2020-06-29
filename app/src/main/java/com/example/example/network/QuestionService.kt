package com.example.example.network

import com.example.example.entity.QuestionResponse
import retrofit2.http.GET

interface QuestionService {
    @GET("/api/clues")
    suspend fun getQuestions(): MutableList<QuestionResponse>
}