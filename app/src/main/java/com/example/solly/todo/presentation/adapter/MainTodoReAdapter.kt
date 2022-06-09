package com.example.solly.todo.presentation.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.solly.todo.R
import com.example.solly.todo.databinding.FragmentTodoItemBinding
import com.example.solly.todo.databinding.ItemMainTodoListBinding

class MainTodoReAdapter: RecyclerView.Adapter<MainTodoReAdapter.MainTodoReViewHolder>() {

    private val list = listOf("1번","2번","3번","4번")

    inner class MainTodoReViewHolder(val item: ItemMainTodoListBinding): RecyclerView.ViewHolder(item.root) {
        fun onBind(str: String) {
//            item.imageTodoStar.setOnClickListener {
//                Log.e("image", "${item.imageTodoStar.state}")
//                Log.e("image", R.drawable.ic_baseline_star_border_24.toString())
//                if(item.imageTodoStar.tag == R.drawable.ic_baseline_star_border_24) {
//                    item.imageTodoStar.setImageResource(R.drawable.ic_baseline_star_24)
//                } else {
//                    item.imageTodoStar.setImageResource(R.drawable.ic_baseline_star_border_24)
//                }
//            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainTodoReViewHolder {
        val item = DataBindingUtil.inflate<ItemMainTodoListBinding>(LayoutInflater.from(parent.context), R.layout.item_main_todo_list, parent, false)

        return MainTodoReViewHolder(item)
    }

    override fun onBindViewHolder(holder: MainTodoReViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}