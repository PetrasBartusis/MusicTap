package com.example.petrasbartusis.musictap.musicList

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.petrasbartusis.musictap.R
import com.example.petrasbartusis.taskon_client.base.BaseActivity

class MusicListActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        openFragment()
    }
    private fun openFragment() {
        val path = intent.getStringExtra(DIRECTORY_PATH)
        val fragment = MusicListFragment.newInstance(path)
        openFragment(fragment, false)
    }

    companion object {
        const val DIRECTORY_PATH = "directoryPath"

        fun createIntent(context: Context) = Intent(context, MusicListActivity::class.java)
    }
}
