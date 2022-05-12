package com.example.solly.todo.presentation.ext

import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.example.solly.todo.presentation.view.ProgressDialog

fun Context.makeToast(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}

fun <T> Context.showIntent(cls: Class<T>, intentBlock: (Intent.() -> Intent)? = null) {
    val intent = Intent(this, cls)
    startActivity(intentBlock?.invoke(intent) ?: intent)
}

/* 프로그래스바 */
fun Context.showProgressBar(boolean: Boolean, content: String = "잠시만 기다려주세요"){
    if(boolean)
        ProgressDialog.show(this, content)
    else
        ProgressDialog.dismiss()
}