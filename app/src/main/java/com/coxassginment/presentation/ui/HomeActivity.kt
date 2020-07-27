package com.coxassginment.presentation.ui

import android.os.Bundle
import com.coxassginment.R

class HomeActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(findViewById(R.id.toolbar))

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.container, UserListFragment.newInstance())
                .commit()
        }
    }
}