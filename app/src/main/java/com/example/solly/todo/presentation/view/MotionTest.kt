package com.example.solly.todo.presentation.view

import com.example.solly.todo.R
import com.example.solly.todo.databinding.MotionToolbarBinding
import com.example.solly.todo.presentation.base.BaseActivity
import com.example.solly.todo.presentation.base.BaseViewModel

class MotionTest: BaseActivity<BaseViewModel, MotionToolbarBinding>() {
    override val layoutId: Int
        get() = R.layout.motion_toolbar
    override val viewModel: BaseViewModel = BaseViewModel()

    override fun initStartView() {
    }

    override fun initDataBinding() {
    }

    override fun initEvent() {
    }
}