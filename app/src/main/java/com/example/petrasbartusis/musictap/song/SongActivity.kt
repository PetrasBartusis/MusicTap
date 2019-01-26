package com.example.petrasbartusis.musictap.song

import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import com.example.petrasbartusis.musictap.R
import com.example.petrasbartusis.musictap.Song
import com.example.petrasbartusis.taskon_client.base.BaseActivity
import com.oze.music.musicbar.ScrollableMusicBar

class SongActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        openFragment()
    }
    private fun openFragment() {
        val song = intent.getSerializableExtra(KEY_SONG) as Song
        val fragment = SongFragment.newInstance(song)
        openFragment(fragment, false)
    }

    companion object {
        const val KEY_SONG = "key_song"

        fun createIntent(context: Context) = Intent(context, SongActivity::class.java)
    }
}
