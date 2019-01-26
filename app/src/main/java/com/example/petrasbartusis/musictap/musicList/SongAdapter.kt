package com.example.petrasbartusis.musictap.musicList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.petrasbartusis.musictap.R
import com.example.petrasbartusis.musictap.Song

class SongAdapter (
        private val listener: OnSongClickListener
) : RecyclerView.Adapter<SongViewHolder>() {
    private val list = mutableListOf<Song>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.song_list_item, parent, false)
        return SongViewHolder(itemView, listener)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        holder.bind(list[position])
    }

    fun setItems(songs: List<Song>) {
        list.clear()
        list.addAll(songs)
        notifyDataSetChanged()
    }
}
