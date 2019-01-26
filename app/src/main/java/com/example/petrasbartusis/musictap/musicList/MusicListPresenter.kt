package com.example.petrasbartusis.musictap.musicList

import android.media.MediaPlayer
import com.example.petrasbartusis.musictap.Song
import com.example.petrasbartusis.taskon_client.base.presenter.BasePresenterImplemetation
import java.io.File

class MusicListPresenter : BasePresenterImplemetation<MusicListContract.View>(),
        MusicListContract.Presenter {

    override fun onViewReady(directoryPath: String) {
        onView { setItems(getFiles(directoryPath)) }
    }

    override fun playSong(player: MediaPlayer?) {
        if (player != null && player.isPlaying) {
            player.stop()
            player.release()
            onView { resetPlayer() }
        } else {
            player?.start()
        }
    }

    private fun getFiles(path: String): MutableList<Song> =
            File(path).listFiles().map{
                Song(it.name, it.absolutePath)
            }.toMutableList()

}