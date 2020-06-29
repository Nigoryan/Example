package com.example.example.activityMain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.example.network.QuestionRepository

class MainViewModelFactory(
    private val repository: QuestionRepository
) : ViewModelProvider.NewInstanceFactory() {


    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass == MainViewModel::class.java)
            MainViewModel(repository) as T
        else
            throw IllegalArgumentException("Wrong ViewModel class")
    }
}