package com.zero.zerobase.data.state.dynamic

import com.zero.zerobase.data.model.dynamic.DevResponseDynamicInterface

sealed class DevStateV2Dynamic<out T> {
    object Loading : DevStateV2Dynamic<Nothing>()
    data class Result<T>(val item: DevResponseDynamicInterface<T>) : DevStateV2Dynamic<T>()
    data class Empty(val data: Any) : DevStateV2Dynamic<Nothing>()
    data class Error(val error: Throwable) : DevStateV2Dynamic<Nothing>()
}
