package com.example.petrasbartusis.musictap.musicList

import android.view.View
import com.example.petrasbartusis.musictap.Song

interface OnSongClickListener {
    fun onSongClicked(song: Song, itemView: View)
}