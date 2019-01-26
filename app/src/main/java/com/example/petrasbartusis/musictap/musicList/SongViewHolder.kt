package com.example.petrasbartusis.musictap.musicList

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.petrasbartusis.musictap.Song
import kotlinx.android.synthetic.main.song_list_item.view.*

class SongViewHolder(
        itemView: View,
        private val listener: OnSongClickListener
) : RecyclerView.ViewHolder(itemView) {
    fun bind(song: Song) {
        itemView.setOnClickListener {
            listener.onSongClicked(song, itemView)
        }
        itemView.text_view.text = song.name
    }
}