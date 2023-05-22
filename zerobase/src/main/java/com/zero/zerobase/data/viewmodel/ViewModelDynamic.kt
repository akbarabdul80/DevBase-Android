package com.zero.zerobase.data.viewmodel

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.zero.zerobase.data.model.dynamic.DevResponseDynamicInterface
import com.zero.zerobase.data.state.dynamic.DevStateDynamic
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


fun Single<*>.observeDynamic(state: MutableLiveData<DevStateDynamic>) {
    this.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .map {
            if (it is List<*> && it.isEmpty()) {
                DevStateDynamic.Empty(it)
            } else {
                DevStateDynamic.Result(it as DevResponseDynamicInterface<*>)
            }
        }
        .onErrorReturn(DevStateDynamic::Error)
        .toFlowable()
        .startWith(DevStateDynamic.Loading)
        .subscribe(state::postValue)
        .let { return@let CompositeDisposable::add }
}


inline fun <reified T> MutableLiveData<DevStateDynamic>.observerDynamic(
    owner: LifecycleOwner,
    crossinline onLoading: () -> Unit? = { },
    crossinline onResult: (T) -> Unit? = { },
    crossinline onResultAll: (DevResponseDynamicInterface<T>) -> Unit? = { },
    crossinline onFailed: (message: String) -> Unit? = { },
    crossinline onError: (Throwable) -> Unit? = { },
    crossinline onEmpty: () -> Unit? = { },
    showMessageSuccess: Boolean = false
) {
    this.observe(owner) {
        when (it) {
            is DevStateDynamic.Loading -> {
                onLoading.invoke()
            }

            is DevStateDynamic.Result<*> -> {
                if (it.data.success != 0) {
                    it.data.data?.let { data ->
                        onResult.invoke(data as T)
                        onResultAll.invoke(it.data as DevResponseDynamicInterface<T>)
                    } ?: it.data.message?.let { message -> onFailed.invoke(message) }
                    if (showMessageSuccess) it.data.message?.let { message ->

                    }
                } else {
                    it.data.message?.let { message -> onFailed.invoke(message) }
                }
            }

            is DevStateDynamic.Error -> {
                onError.invoke(it.error)
            }

            is DevStateDynamic.Empty -> {
                onEmpty.invoke()
            }
        }
    }
}
