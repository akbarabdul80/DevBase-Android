package com.zero.devbase

import com.zero.devbase.databinding.ActivityMainBinding
import com.zero.zerobase.presentation.utils.toast
import com.zero.zerobase.presentation.viewbinding.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun initAction() {
        super.initAction()
        toast("Hello World")
    }
}