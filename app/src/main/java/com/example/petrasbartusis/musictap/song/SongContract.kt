package com.example.petrasbartusis.musictap.song

import com.example.petrasbartusis.taskon_client.base.presenter.BasePresenter

interface SongContract {
    interface View {
        fun showToast(message: String)
        fun stopScrolling()
        fun setUpPlayer(duration: Int)
        fun startScrolling()
        fun setPlayButtonIcon(isPlaying: Boolean)
        fun stopPlayer(action: Runnable)
        fun updatePlayerData(action: Runnable, currentDuration: Int)
    }
    interface Presenter: BasePresenter<View> {
        fun onViewReady()
        fun onPlayCLicked()
        var updateTimer: Runnable
        fun milliSecondsToTimer(milliseconds: Long): String
    }
}