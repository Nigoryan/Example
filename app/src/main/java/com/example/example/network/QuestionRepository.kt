package com.example.example.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object QuestionRepository {
    private lateinit var service: QuestionService

    fun getQuestionService(): QuestionService {
        if (!::service.isInitialized) {

            val retrofit = Retrofit.Builder()
                .baseUrl("http://jservice.io")
                .client(getClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            service = retrofit.create(QuestionService::class.java)

        }
        return service
    }

    private fun getClient(): OkHttpClient {

        val logger = HttpLoggingInterceptor()
        logger.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .addInterceptor(logger)
            .callTimeout(3, TimeUnit.SECONDS)
        return client.build()
    }
}