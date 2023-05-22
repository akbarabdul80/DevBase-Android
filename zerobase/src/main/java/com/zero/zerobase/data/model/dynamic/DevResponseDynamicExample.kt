package com.zero.zerobase.data.model.dynamic

import com.google.gson.annotations.SerializedName

data class DevResponseDynamicExample<T>(
    @SerializedName("success")
    override val success: Int?,
    @SerializedName("message")
    override val message: String?,
    @SerializedName("data")
    override val data: T?
) : DevResponseDynamicInterface<T>
