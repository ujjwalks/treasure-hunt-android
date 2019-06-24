package com.murslabs.treasurehunt.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.murslabs.treasurehunt.R
import com.murslabs.treasurehunt.base.BaseFragmentActivity
import com.murslabs.treasurehunt.databinding.ActivityHomeBinding
import com.murslabs.treasurehunt.login.LoginActivity

class HomeActivity : BaseFragmentActivity<ActivityHomeBinding>(), HomeContract.Navigator {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialize()
    }

    override fun finish() {
        super.finish()
    }

    override fun onStart() {
        super.onStart()
        val user = FirebaseAuth.getInstance().currentUser
        if (user != null) {
            startActivity(getLaunchIntent(this))
            finish()
        }
    }

    override fun initialize() {
        setAndBindContentView(R.layout.activity_home)
    }

    companion object {
        fun getLaunchIntent(from: Context) = Intent(from, LoginActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
    }
}