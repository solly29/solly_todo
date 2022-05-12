package com.example.solly.todo.data.util

import com.example.solly.todo.domain.model.ResponseData
import com.example.solly.todo.domain.model.Result
import retrofit2.Response

fun <T> Response<T>.converterResponse(): Result<T> {
    return if (isSuccessful) {
        body()?.let {
            if (it is ResponseData<*>) {
                if (it.code == "100")
                    Result.Success(it)
                else
                    Result.Error(it.code, it.msg)
            } else {
                Result.Error("E997", "데이터 타입 오류입니다.")
            }
        } ?: run {
            Result.Error("E998", "null 오류입니다.")
        }
    } else {
        Result.Error("E999", "네트워크 통신 오류입니다.")
    }
}