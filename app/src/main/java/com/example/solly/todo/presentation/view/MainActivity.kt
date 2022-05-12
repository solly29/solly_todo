package com.example.solly.todo.presentation.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.example.solly.todo.R
import com.example.solly.todo.databinding.ActivityMainBinding
import com.example.solly.todo.presentation.adapter.MainTodoItemAdapter
import com.example.solly.todo.presentation.adapter.SliderTransformer
import com.example.solly.todo.presentation.base.BaseActivity
import com.example.solly.todo.presentation.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {
    override val layoutId: Int
        get() = R.layout.activity_main
    override val viewModel: MainViewModel by viewModels()

    override fun initStartView() {
        binding.viewPagerMainTodo.apply {
            adapter = MainTodoItemAdapter()
            offscreenPageLimit = 3
            setPageTransformer(SliderTransformer(3))
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                }
            })
        }
    }

    override fun initDataBinding() {

    }

    override fun initEvent() {

    }
}