package com.example.example.activityMain

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.example.R
import com.example.example.adapter.MainAdapter
import com.example.example.dao.QuestionDAO
import com.example.example.database.QuestionDatabase
import com.example.example.entity.QuestionResponse
import com.example.example.network.QuestionRepository
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity(), MainAdapter.OnItemClick {

    lateinit var viewModel: MainViewModel
    lateinit var mainAdapter: MainAdapter
    lateinit var questionDatabase: QuestionDAO
    val coroutineScope = CoroutineScope(Dispatchers.IO)
    val databaseLiveData = MutableLiveData<MutableList<QuestionResponse>>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        questionDatabase = QuestionDatabase.getDatabase(this@MainActivity)?.getQuestionDao()!!
        viewModel = ViewModelProvider(
            this,
            MainViewModelFactory(QuestionRepository)
        ).get(MainViewModel::class.java)

        viewModel.questionLiveData.observe(this, observer)
        mainAdapter = MainAdapter(this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = mainAdapter
        btn.setOnClickListener {
            if (isOnline()) {
                viewModel.getQuestions()
            } else {
                coroutineScope.async {
                    val result = questionDatabase.getAllQuestions()
                    databaseLiveData.postValue(result)
                }
                Toast.makeText(this, "Turn on the internet", Toast.LENGTH_SHORT).show()
            }

        }
        databaseLiveData.observe(this, observer)
    }

    private val observer = Observer<MutableList<QuestionResponse>> { list ->
        mainAdapter.list.addAll(list)
        mainAdapter.notifyDataSetChanged()
    }

    override fun OnItemClicked(position: Int) {
        val question: QuestionResponse = mainAdapter.list[position]
        coroutineScope.launch {
            questionDatabase.insertQuestion(question)
        }
    }

    fun isOnline(): Boolean {
        val connectivityManager =
            this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivityManager != null) {
            val networkInfo = connectivityManager.activeNetworkInfo
            return networkInfo != null && networkInfo.isConnected
        }
        return false
    }
}