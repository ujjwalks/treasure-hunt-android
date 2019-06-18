package com.mindtickle.assignment.assignmentapp.home

import android.os.Bundle
import com.mindtickle.assignment.assignmentapp.R
import com.mindtickle.assignment.assignmentapp.base.BaseFragmentActivity
import com.mindtickle.assignment.assignmentapp.databinding.ActivityHomeBinding
import javax.inject.Inject

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