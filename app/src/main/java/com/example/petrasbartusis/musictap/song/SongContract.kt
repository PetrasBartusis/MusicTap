package com.example.petrasbartusis.musictap.song

import android.media.MediaPlayer
import com.example.petrasbartusis.musictap.Song
import com.example.petrasbartusis.taskon_client.base.presenter.BasePresenter

interface SongContract {
    interface View {
        fun showToast(message: String)
        fun stopScrolling()
        fun setUpPlayer()
    }
    interface Presenter: BasePresenter<View> {
        fun onViewReady()
        fun playSong(player: MediaPlayer?)

    }
}