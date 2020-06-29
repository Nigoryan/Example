package com.example.example.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class QuestionResponse(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    @SerializedName("question") val question: String?,
    @SerializedName("answer") val answer: String?
)