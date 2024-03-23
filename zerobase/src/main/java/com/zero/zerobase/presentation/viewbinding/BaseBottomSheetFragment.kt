package com.zero.zerobase.presentation.viewbinding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.zero.zerobase.presentation.utils.getBinding
import dmax.dialog.SpotsDialog

abstract class BaseBottomSheetFragment<T : androidx.viewbinding.ViewBinding> : BottomSheetDialogFragment() {
    val binding by lazy { getBinding() }
    val spotsDialogWait: SpotsDialog by lazy {
        SpotsDialog(requireContext(), "Mohon Tunggu...")
    }

    private val TAG: String = this::class.java.simpleName

    protected abstract val isDialogCancelable: Boolean

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isCancelable = isDialogCancelable
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