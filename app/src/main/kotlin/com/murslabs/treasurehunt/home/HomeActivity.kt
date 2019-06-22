package com.murslabs.treasurehunt.home

import android.os.Bundle
import com.murslabs.treasurehunt.R
import com.murslabs.treasurehunt.base.BaseFragmentActivity
import com.murslabs.treasurehunt.databinding.ActivityHomeBinding

class HomeActivity : BaseFragmentActivity<ActivityHomeBinding>(), HomeContract.Navigator {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialize()
    }

    override fun finish() {
        super.finish()
    }

    private fun initialize() {
        setAndBindContentView(R.layout.activity_home)
    }
}