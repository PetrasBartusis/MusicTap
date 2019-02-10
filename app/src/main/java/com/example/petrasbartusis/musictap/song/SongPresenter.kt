package com.example.petrasbartusis.musictap.song

import com.example.petrasbartusis.musictap.MusicPlayer
import com.example.petrasbartusis.taskon_client.base.presenter.BasePresenterImplemetation

class SongPresenter(
        private val musicPlayer: MusicPlayer
) : BasePresenterImplemetation<SongContract.View>(),
        SongContract.Presenter {

    override fun onPlayCLicked() {
        if(musicPlayer.isPlaying()){
            musicPlayer.pause()
            onView { stopScrolling() }
        } else {
            musicPlayer.play()
            onView { startScrolling() }
        }
        onView { setPlayButtonIcon(musicPlayer.isPlaying()) }
    }

    override var updateTimer = object : Runnable {
        override fun run() {
            val action = this
            val currentDuration: Int
            if (musicPlayer.isPlaying()) {
                currentDuration = musicPlayer.getCurrentDuration()
                onView { updatePlayerData(action, currentDuration) }
            } else {
                onView { stopPlayer(action) }
            }
        }
    }

    override fun milliSecondsToTimer(milliseconds: Long): String {
        var finalTimerString = ""
        val secondsString: String

        val hours = (milliseconds / (1000 * 60 * 60)).toInt()
        val minutes = (milliseconds % (1000 * 60 * 60)).toInt() / (1000 * 60)
        val seconds = (milliseconds % (1000 * 60 * 60) % (1000 * 60) / 1000).toInt()

        if (hours > 0) {
            finalTimerString = hours.toString() + ":"
        }

        secondsString = if (seconds < 10) {
            "0$seconds"
        } else {
            "" + seconds
        }

        finalTimerString = "$finalTimerString$minutes:$secondsString"

        return finalTimerString
    }

    override fun onViewReady() {
        onView { setUpPlayer(musicPlayer.getDuration()) }
    }

    override fun dropView() {
        musicPlayer.stop()
        super.dropView()
    }
}