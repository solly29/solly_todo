package com.example.solly.todo.presentation.base

import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import com.example.solly.todo.presentation.ext.makeToast
import com.example.solly.todo.presentation.ext.showProgressBar

abstract class BaseActivity<T : BaseViewModel, B : ViewDataBinding> : AppCompatActivity() {

    abstract val layoutId: Int
    abstract val viewModel: T
    lateinit var binding: B

//    private val progressBar = ProgressDialog(applicationContext).apply {
//        setMessage("잠시만 기다려주세요.")
//    }

    abstract fun initStartView()

    abstract fun initDataBinding()

    abstract fun initEvent()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        binding = DataBindingUtil.setContentView(this, layoutId)
        binding.lifecycleOwner = this
        initStartView()

        with(viewModel) {
            toastLiveData.observe(
                this@BaseActivity,
                Observer {
                    this@BaseActivity.makeToast(it)
                }
            )

            loadLiveData.observe(this@BaseActivity, Observer {
                showProgressBar(it)
            })
        }

        initDataBinding()
        initEvent()
    }
}