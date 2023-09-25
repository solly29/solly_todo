package com.example.solly.todo.presentation.view

import android.graphics.Rect
import android.util.Log
import android.view.View
import android.widget.ScrollView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.solly.todo.R
import com.example.solly.todo.databinding.ActivityTodoEditBinding
import com.example.solly.todo.presentation.adapter.EditTodoItemAdapter
import com.example.solly.todo.presentation.base.BaseActivity
import com.example.solly.todo.presentation.ext.makeToast
import com.example.solly.todo.presentation.viewmodel.EditViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import kotlin.math.abs

@AndroidEntryPoint
class TodoEditActivity: BaseActivity<EditViewModel, ActivityTodoEditBinding>() {
    private val TAG = javaClass.simpleName
    override val layoutId: Int
        get() = R.layout.activity_todo_edit
    override val viewModel: EditViewModel by viewModels()

    private val addClickEvent: (Int) -> Unit = {
//        binding.rvEditTodoList.scrollToPosition(it + 1)
//        makeToast(binding.rvEditTodoList.bottom.toString())
        Log.e(TAG, binding.rvEditTodoList.bottom.toString())
        binding.nevEdit.scrollToView(binding.rvEditTodoList)
    }

    private val editTodoItemAdapter by lazy { EditTodoItemAdapter(addClickEvent) }

    private var isClickMenu = false

    override fun initStartView() {
        binding.rvEditTodoList.apply {
            adapter = editTodoItemAdapter

            layoutManager = LinearLayoutManager(this@TodoEditActivity)
        }
    }

    override fun initDataBinding() {

    }

    override fun initEvent() {
        with(binding) {
            toolbarEdit.buttonAppbar.setOnClickListener {
                if(!isClickMenu) {
                    toolbarEdit.buttonAppbar.setImageResource(R.drawable.ic_baseline_close_24)
                    toolbarEdit.motionLayoutAppbar.transitionToEnd()
                } else {
                    toolbarEdit.buttonAppbar.setImageResource(R.drawable.ic_baseline_menu_24)
                    toolbarEdit.motionLayoutAppbar.transitionToStart()
                }
                isClickMenu = !isClickMenu
            }

            toolbarEdit.floatingCloseButton.setOnClickListener {
                if(!isClickMenu) {
                    toolbarEdit.buttonAppbar.setImageResource(R.drawable.ic_baseline_close_24)
                    toolbarEdit.motionLayoutAppbar.transitionToEnd()
                } else {
                    toolbarEdit.buttonAppbar.setImageResource(R.drawable.ic_baseline_menu_24)
                    toolbarEdit.motionLayoutAppbar.transitionToStart()
                }
                isClickMenu = !isClickMenu
            }
        }
    }

    private fun NestedScrollView.computeDistanceToView(view: View): Int {
        return abs(calculateRectOnScreen(this).top - (this.scrollY + calculateRectOnScreen(view).bottom))
    }

    private fun calculateRectOnScreen(view: View): Rect {
        val location = IntArray(2)
        view.getLocationOnScreen(location)
        return Rect(
            location[0],
            location[1],
            location[0] + view.measuredWidth,
            location[1] + view.measuredHeight
        )
    }

    fun NestedScrollView.scrollToView(view: View) {
        this.scrollTo(0, computeDistanceToView(view))
    }
}