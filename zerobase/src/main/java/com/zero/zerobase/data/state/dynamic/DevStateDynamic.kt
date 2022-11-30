package com.zero.zerobase.data.state.dynamic

import com.zero.zerobase.data.model.DevResponseDynamic

sealed class DevStateDynamic {
    object Loading : DevStateDynamic()
    data class Result<T>(val data: DevResponseDynamic<T>) : DevStateDynamic()
    data class Empty(val data: Any) : DevStateDynamic()
    data class Error(val error: Throwable) : DevStateDynamic()
}
