package com.example.petrasbartusis.musictap.song

import android.media.MediaPlayer
import com.example.petrasbartusis.musictap.main.MainContract
import com.example.petrasbartusis.taskon_client.base.presenter.BasePresenterImplemetation

class SongPresenter
    : BasePresenterImplemetation<SongContract.View>(),
        SongContract.Presenter {

    override fun playSong(player: MediaPlayer?) {
        if (player != null && player.isPlaying) {
            player.stop()
            //player.release()
            onView { stopScrolling() }
        } else {
            player?.start()
        }
    }

    override fun onViewReady() {
        onView { setUpPlayer() }
    }
}