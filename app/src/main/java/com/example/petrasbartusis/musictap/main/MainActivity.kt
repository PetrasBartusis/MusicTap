package com.example.petrasbartusis.musictap.main

import android.os.Bundle
import com.example.petrasbartusis.musictap.R
import com.example.petrasbartusis.taskon_client.base.BaseActivity

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            openFragment(MainFragment.newInstance(), false)
        }
    }
}
