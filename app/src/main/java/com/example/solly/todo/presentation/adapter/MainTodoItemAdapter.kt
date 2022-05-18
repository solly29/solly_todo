package com.example.solly.todo.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.solly.todo.R
import com.example.solly.todo.databinding.FragmentTodoItemBinding

class MainTodoItemAdapter(private val click: (Int, Int, Boolean) -> Unit): RecyclerView.Adapter<MainTodoItemAdapter.MainTodoItemViewHolder>() {

    private val list = listOf("1번","2번","3번","4번")

    inner class MainTodoItemViewHolder(val item: FragmentTodoItemBinding): RecyclerView.ViewHolder(item.root) {
        @SuppressLint("ClickableViewAccessibility")
        fun onBind(str: String) {
            item.textViewTodoItem.text = str

            item.root.setOnClickListener {
                click.invoke(50, 0, false)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainTodoItemViewHolder {
        val item = DataBindingUtil.inflate<FragmentTodoItemBinding>(LayoutInflater.from(parent.context), R.layout.fragment_todo_item, parent, false)

        return MainTodoItemViewHolder(item)
    }

    override fun onBindViewHolder(holder: MainTodoItemViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}