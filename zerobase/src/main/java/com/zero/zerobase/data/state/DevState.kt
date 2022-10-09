package com.zero.zerobase.data.state

import com.zero.zerobase.data.model.DevResponse

sealed class DevState {
    object Loading : DevState()
    data class Result<T>(val data: DevResponse<T>) : DevState()
    data class Empty(val data: Any) : DevState()
    data class Error(val error: Throwable) : DevState()
}
