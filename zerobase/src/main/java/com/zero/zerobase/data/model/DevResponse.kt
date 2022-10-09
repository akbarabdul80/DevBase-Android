package com.zero.zerobase.data.model

import com.google.gson.annotations.SerializedName

data class DevResponse<T>(
    @SerializedName("sukses") val success: Int?,
    @SerializedName("pesan") val message: String?,
    @SerializedName("received") val data: T?
)
