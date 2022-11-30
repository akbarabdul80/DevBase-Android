package com.zero.zerobase.data.model

abstract class DevResponseDynamic<T> {
    abstract val success: Int?
    abstract val message: String?
    abstract val data: T?
}