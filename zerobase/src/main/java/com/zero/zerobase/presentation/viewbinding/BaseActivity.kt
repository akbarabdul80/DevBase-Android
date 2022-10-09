package com.zero.zerobase.presentation.viewbinding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zero.zerobase.presentation.utils.getBinding
import dmax.dialog.SpotsDialog

abstract class BaseActivity<T : androidx.viewbinding.ViewBinding> : AppCompatActivity() {
    val binding by lazy { getBinding() }
    lateinit var spotsDialogWait: SpotsDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        onViewReady()
    }

    private fun onViewReady() {
        initUI()
        initData()
        initAction()
        initObserver()
    }

    protected open fun initData() {
        // Intended to be empty
    }

    protected open fun initUI() {
        // Intended to be empty
    }

    protected open fun initAction() {
        // Intended to be empty
    }

    protected open fun initObserver() {
        // Intended to be empty
    }
}