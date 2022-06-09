package com.example.solly.todo.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.solly.todo.R
import com.example.solly.todo.databinding.FragmentTodoAddItemBinding
import com.example.solly.todo.databinding.FragmentTodoItemBinding
import com.example.solly.todo.presentation.ext.showIntent
import com.example.solly.todo.presentation.view.TodoEditActivity

class MainTodoItemAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val list = listOf("1번","2번","3번","4번")

    private final val TODO_CARD_ITEM = 1
    private final val TODO_CARD_ADD_ITEM = 2

    inner class MainTodoItemViewHolder(val item: FragmentTodoItemBinding): RecyclerView.ViewHolder(item.root) {
        fun onBind(data: String) {
            item.textViewTodoItem.text = data
        }
    }

    inner class MainTodoItemAddViewHolder(val item: FragmentTodoAddItemBinding): RecyclerView.ViewHolder(item.root) {
        fun onBind(data: String) {
            item.cardviewAdd.setOnClickListener {
                item.root.context.showIntent(TodoEditActivity::class.java)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if(position == list.size) {
            TODO_CARD_ADD_ITEM
        } else{
            TODO_CARD_ITEM
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if(viewType != TODO_CARD_ADD_ITEM) {
            val item = DataBindingUtil.inflate<FragmentTodoItemBinding>(LayoutInflater.from(parent.context), R.layout.fragment_todo_item, parent, false)
            MainTodoItemViewHolder(item)
        } else {
            val item = DataBindingUtil.inflate<FragmentTodoAddItemBinding>(LayoutInflater.from(parent.context), R.layout.fragment_todo_add_item, parent, false)
            MainTodoItemAddViewHolder(item)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is MainTodoItemViewHolder)
            holder.onBind(list[position])
        else if(holder is MainTodoItemAddViewHolder)
            holder.onBind("")
    }

    override fun getItemCount(): Int {
        return list.size + 1
    }
}