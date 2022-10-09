package com.zero.zerobase.presentation.utils

import android.view.LayoutInflater
import androidx.viewbinding.ViewBinding
import com.zero.zerobase.presentation.viewbinding.BaseActivity
import com.zero.zerobase.presentation.viewbinding.BaseBottomSheetFragment
import com.zero.zerobase.presentation.viewbinding.BaseFragment
import java.lang.reflect.ParameterizedType

internal fun <V : ViewBinding> Class<*>.getBinding(layoutInflater: LayoutInflater): V {
    return try {
        @Suppress("UNCHECKED_CAST")
        getMethod("inflate", LayoutInflater::class.java).invoke(null, layoutInflater) as V
    } catch (t: Throwable) {
        throw t
    }
}

internal fun Class<*>.checkMethod(): Boolean {
    return try {
        getMethod("inflate", LayoutInflater::class.java)
        true
    } catch (ex: Exception) {
        false
    }
}


internal fun Any.findClass(): Class<*> {
    var javaClass: Class<*>? = this.javaClass
    var result: Class<*>? = null
    while (result == null || !result.checkMethod()) {
        result = (javaClass?.genericSuperclass as? ParameterizedType)
            ?.actualTypeArguments?.firstOrNull {
                if (it is Class<*>) {
                    it.checkMethod()
                } else {
                    false
                }
            } as? Class<*>
        javaClass = javaClass?.superclass
    }
    return result
}

internal fun <V : ViewBinding> BaseActivity<V>.getBinding(): V {
    return findClass().getBinding(layoutInflater)
}


internal fun <V : ViewBinding> BaseBottomSheetFragment<V>.getBinding(): V {
    return findClass().getBinding(layoutInflater)
}

internal fun <V : ViewBinding> BaseFragment<V>.getBinding(): V {
    return findClass().getBinding(layoutInflater)
}
