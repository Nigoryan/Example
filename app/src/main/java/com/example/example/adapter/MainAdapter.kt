package com.example.example.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.example.R
import com.example.example.entity.QuestionResponse
import kotlinx.android.synthetic.main.recycler_item.view.*

class MainAdapter(private val callback: OnItemClick) :
    RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    val list = mutableListOf<QuestionResponse>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_item, parent, false)
        return MainViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item)

        holder.itemView.setOnClickListener {
            callback.OnItemClicked(holder.adapterPosition)
        }
    }

    class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val question: TextView = itemView.tvQuestion
        private val answer: TextView = itemView.tvAnswer
        fun bind(item: QuestionResponse) {
            question.text = item.question
            answer.text = item.answer
        }
    }

    interface OnItemClick {
        fun OnItemClicked(position: Int)
    }
}