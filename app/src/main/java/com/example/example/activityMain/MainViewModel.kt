package com.example.example.activityMain

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.example.database.QuestionDatabase
import com.example.example.entity.QuestionResponse
import com.example.example.network.QuestionRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(val repository: QuestionRepository) : ViewModel() {

    val coroutineScope = CoroutineScope(Dispatchers.IO)

    val questionLiveData = MutableLiveData<MutableList<QuestionResponse>>()

    fun getQuestions() {
        coroutineScope.launch {
            val result = repository.getQuestionService().getQuestions()
            questionLiveData.postValue(result)
        }
    }
}