package com.example.solly.todo.presentation.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.solly.todo.R
import com.example.solly.todo.databinding.FragmentTodoAddItemBinding
import com.example.solly.todo.databinding.FragmentTodoItemBinding
import com.example.solly.todo.databinding.ItemEditAddTodoListBinding
import com.example.solly.todo.databinding.ItemEditTodoListBinding
import com.example.solly.todo.presentation.ext.showIntent
import com.example.solly.todo.presentation.view.TodoEditActivity

class EditTodoItemAdapter(private val addClickEvent: (Int) -> Unit): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val list = mutableListOf<String>()

    private final val TODO_CARD_ITEM = 1
    private final val TODO_CARD_ADD_ITEM = 2

    inner class EditTodoItemViewHolder(val item: ItemEditTodoListBinding): RecyclerView.ViewHolder(item.root) {
        fun onBind(data: String) {

        }
    }

    inner class EditTodoItemAddViewHolder(val item: ItemEditAddTodoListBinding): RecyclerView.ViewHolder(item.root) {
        fun onBind(data: String) {
            item.llAddTodoList.setOnClickListener {
                list.add("1")
                notifyItemInserted(list.size)
                addClickEvent.invoke(list.size)
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
            val item = DataBindingUtil.inflate<ItemEditTodoListBinding>(LayoutInflater.from(parent.context), R.layout.item_edit_todo_list, parent, false)
            EditTodoItemViewHolder(item)
        } else {
            val item = DataBindingUtil.inflate<ItemEditAddTodoListBinding>(LayoutInflater.from(parent.context), R.layout.item_edit_add_todo_list, parent, false)
            EditTodoItemAddViewHolder(item)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is EditTodoItemViewHolder)
            holder.onBind(list[position])
        else if(holder is EditTodoItemAddViewHolder)
            holder.onBind("")
    }

    override fun getItemCount(): Int {
        return list.size + 1
    }
}