package com.example.solly.todo.presentation.view

import android.app.ProgressDialog
import android.content.Context

object ProgressDialog {

    private var progress: ProgressDialog? = null

    fun show(context: Context, content: String) {
        progress = ProgressDialog(context).apply {
            setMessage(content)
            setCancelable(false)
            setOnKeyListener(null)
        }
        progress?.show()
    }

    fun dismiss() {
        progress?.run { if (isShowing) dismiss() }
    }
}