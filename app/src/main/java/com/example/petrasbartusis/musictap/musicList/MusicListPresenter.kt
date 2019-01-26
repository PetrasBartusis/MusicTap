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

    private fun getFiles(path: String): MutableList<Song> =
            File(path).listFiles().map{
                Song(it.name.split('.')[0], it.absolutePath)
            }.toMutableList()

}