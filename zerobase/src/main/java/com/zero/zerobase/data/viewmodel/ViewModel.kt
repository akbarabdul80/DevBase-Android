package com.zero.zerobase.data.viewmodel

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.zero.zerobase.data.model.DevResponse
import com.zero.zerobase.data.state.DevState
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

fun Single<*>.observe(state: MutableLiveData<DevState>) {
    this.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .map {
            if (it is List<*> && it.isEmpty()) {
                DevState.Empty(it)
            } else {
                DevState.Result(it as DevResponse<*>)
            }
        }
        .onErrorReturn(DevState::Error)
        .toFlowable()
        .startWith(DevState.Loading)
        .subscribe(state::postValue)
        .let { return@let CompositeDisposable::add }
}

inline fun <reified T> MutableLiveData<DevState>.observer(
    owner: LifecycleOwner,
    crossinline onLoading: () -> Unit? = { },
    crossinline onResult: (T) -> Unit? = { },
    crossinline onFailed: (message: String) -> Unit? = { },
    crossinline onError: (Throwable) -> Unit? = { },
    crossinline onEmpty: () -> Unit? = { },
    showMessageSuccess: Boolean = false
) {
    this.observe(owner) {
        when (it) {
            is DevState.Loading -> {
                onLoading.invoke()
            }
            is DevState.Result<*> -> {
                if (it.data.success != 0) {
                    it.data.data?.let { data ->
                        onResult.invoke(data as T)
                    } ?: it.data.message?.let { message -> onFailed.invoke(message) }
                    if (showMessageSuccess) it.data.message?.let { message ->

                    }
                } else {
                    it.data.message?.let { message -> onFailed.invoke(message) }
                }
            }
            is DevState.Error -> {
                onError.invoke(it.error)
            }
            is DevState.Empty -> {
                onEmpty.invoke()
            }
        }
    }
}
