package com.example.example.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.example.dao.QuestionDAO
import com.example.example.entity.QuestionResponse

@Database(version = 1, entities = arrayOf(QuestionResponse::class))
abstract class ExampleDatabase : RoomDatabase() {
    abstract fun getQuestionDao(): QuestionDAO
}