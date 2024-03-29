package com.zero.zerobase.presentation.viewbinding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.zero.zerobase.R
import com.zero.zerobase.presentation.utils.getBinding
import dmax.dialog.SpotsDialog

abstract class BaseFragment<T : androidx.viewbinding.ViewBinding> : Fragment() {
    val binding by lazy { getBinding() }
    private val spotsDialogWait: SpotsDialog by lazy {
        SpotsDialog(requireContext(), "Mohon Tunggu...")
    }

    private val TAG: String = this::class.java.simpleName
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onViewReady()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = binding.root


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