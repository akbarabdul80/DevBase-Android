package com.zero.zerobase.data.state.dynamic

import com.zero.zerobase.data.model.dynamic.DevResponseDynamicInterface

sealed class DevStateDynamic {
    object Loading : DevStateDynamic()
    data class Result<T>(val data: DevResponseDynamicInterface<T>) : DevStateDynamic()
    data class Empty(val data: Any) : DevStateDynamic()
    data class Error(val error: Throwable) : DevStateDynamic()
}
