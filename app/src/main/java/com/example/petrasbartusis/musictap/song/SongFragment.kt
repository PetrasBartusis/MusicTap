package com.example.petrasbartusis.musictap.song

import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.view.View
import com.example.petrasbartusis.musictap.R
import com.example.petrasbartusis.musictap.Song
import com.example.petrasbartusis.taskon_client.base.BaseFragment
import com.oze.music.musicbar.ScrollableMusicBar
import kotlinx.android.synthetic.main.fragment_song.*
import java.io.File

class SongFragment : BaseFragment(), SongContract.View {

    private lateinit var presenter: SongContract.Presenter

    private var currentPlayer: MediaPlayer? = null

    private val song: Song = arguments?.getSerializable(KEY_SONG) as Song

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = SongPresenter()
        presenter.takeView(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.onViewReady()
        songTitle.text = song.name
    }

    override fun setUpPlayer() {
        currentPlayer = createPlayer()
        scrollableMusicBar.loadFrom(song.path, currentPlayer!!.duration)
        currentPlayer?.setOnPreparedListener {
            scrollableMusicBar.startAutoProgress(1.0f)
        }
    }

    private fun createPlayer(): MediaPlayer? {
        return MediaPlayer.create(context, Uri.fromFile(File(song.path)))
    }

    override fun showToast(message: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun stopScrolling() {
        scrollableMusicBar.stopAutoProgress()
        //scrollableMusicBar.loadFrom(song.path, currentPlayer!!.duration)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.dropView()
    }

    override fun layoutRes() = R.layout.fragment_song

    companion object {
        const val KEY_SONG = "key_song"

        fun newInstance(song: Song): SongFragment {
            val fragment = SongFragment()
            val bundle = Bundle()
            bundle.putSerializable(KEY_SONG, song)
            fragment.arguments = bundle
            return fragment
        }
    }
}