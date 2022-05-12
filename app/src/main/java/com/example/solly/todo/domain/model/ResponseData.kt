package com.example.solly.todo.domain.model

data class ResponseData<T>(
    val size: String,
    val data: List<T>,
    val code: String,
    val msg: String
)