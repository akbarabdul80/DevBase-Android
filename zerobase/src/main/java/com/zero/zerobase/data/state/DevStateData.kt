package com.zero.zerobase.data.state

import com.zero.zerobase.data.model.DevResponse

sealed class DevStateData<out T> {
    object Loading : DevStateData<Nothing>()
    data class Result<T>(val data: DevResponse<T>) : DevStateData<T>()
    data class Empty(val data: Any) : DevStateData<Nothing>()
    data class Error(val error: Throwable) : DevStateData<Nothing>()
}