package com.zero.zerobase.data.model.dynamic

interface DevResponseDynamicInterface<T> {
    val success: Int?
    val message: String?
    val data: T?
}