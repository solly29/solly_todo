package com.example.solly.todo.presentation.view

import android.widget.Toast
import androidx.activity.viewModels
import com.example.solly.todo.R
import com.example.solly.todo.databinding.ActivityTodoEditBinding
import com.example.solly.todo.presentation.base.BaseActivity
import com.example.solly.todo.presentation.viewmodel.EditViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

@AndroidEntryPoint
class TodoEditActivity: BaseActivity<EditViewModel, ActivityTodoEditBinding>() {
    override val layoutId: Int
        get() = R.layout.activity_todo_edit
    override val viewModel: EditViewModel by viewModels()

    private var isClickMenu = false

    override fun initStartView() {

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
}