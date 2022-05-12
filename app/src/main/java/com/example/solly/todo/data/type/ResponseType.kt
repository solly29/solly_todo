package com.example.solly.todo.data.type

import com.example.solly.todo.domain.model.ResponseData
import retrofit2.Response


typealias ResponseType<T> = Response<ResponseData<T>>