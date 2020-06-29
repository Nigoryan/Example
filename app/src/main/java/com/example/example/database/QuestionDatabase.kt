package com.example.example.database

import android.content.Context
import androidx.room.Room

object QuestionDatabase {
    private var database: ExampleDatabase? = null


    fun getDatabase(context: Context): ExampleDatabase? {
        if (database == null) {
            database = Room.databaseBuilder(
                context,
                ExampleDatabase::class.java,
                "database-name"
            ).build()
        }

        return database
    }
}