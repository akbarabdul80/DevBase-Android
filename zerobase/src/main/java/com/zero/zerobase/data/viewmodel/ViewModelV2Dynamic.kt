package com.zero.zerobase.data.viewmodel

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.zero.zerobase.data.model.dynamic.DevResponseDynamicInterface
import com.zero.zerobase.data.state.dynamic.DevStateDynamic
import com.zero.zerobase.data.state.dynamic.DevStateV2Dynamic
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


fun <T> Single<T>.observeV2Dynamic(state: MutableLiveData<DevStateV2Dynamic<T>>) {
    this.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .map {
            if (it is List<*> && it.isEmpty()) {
                DevStateV2Dynamic.Empty(it)
            } else {
                DevStateV2Dynamic.Result(it as DevResponseDynamicInterface<*>)
            }
        }
        .onErrorReturn { DevStateV2Dynamic.Error(it) }
        .toFlowable()
        .startWith(DevStateV2Dynamic.Loading)
        .subscribe {
            state.postValue(it as DevStateV2Dynamic<T>)
        }
        .let { return@let CompositeDisposable::add }
}


inline fun <reified T> MutableLiveData<DevStateDynamic>.observerV2Dynamic(
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
