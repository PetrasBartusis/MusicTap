package com.example.petrasbartusis.musictap.musicList

import com.example.petrasbartusis.musictap.Song
import com.example.petrasbartusis.taskon_client.base.presenter.BasePresenter

interface MusicListContract {
    interface View {
        fun setItems(songList: List<Song>)
        fun showToast(message: String)
    }
    interface Presenter: BasePresenter<View> {
        fun onViewReady(directoryPath: String)
    }
}