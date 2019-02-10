package com.example.petrasbartusis.musictap.song

import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.petrasbartusis.musictap.MusicPlayerImpl
import com.example.petrasbartusis.musictap.R
import com.example.petrasbartusis.musictap.Song
import com.example.petrasbartusis.taskon_client.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_song.*
import java.io.File

class SongFragment : BaseFragment(), SongContract.View {

    private lateinit var presenter: SongContract.Presenter

    private lateinit var song: Song

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        song = arguments?.getSerializable(KEY_SONG) as Song
        presenter = SongPresenter(
                MusicPlayerImpl(
                        MediaPlayer.create(context, Uri.fromFile(File(song.path)))
                )
        )
        presenter.takeView(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.onViewReady()

        songTitle.text = song.name

        playButton.setOnClickListener {
            presenter.onPlayCLicked()
        }
    }

    override fun setPlayButtonIcon(isPlaying: Boolean) {
        if(isPlaying){
            playButton.setImageResource(R.drawable.ic_pause_black_24dp)
            songTimer.post(presenter.updateTimer)
        } else {
            playButton.setImageResource(R.drawable.ic_play_arrow)
        }
    }

    override fun setUpPlayer(duration: Int) {
        scrollableMusicBar.loadFrom(song.path, duration)
    }

    override fun showToast(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
    }

    override fun startScrolling() {
        scrollableMusicBar.startAutoProgress(1.0f)
    }

    override fun stopScrolling() {
        scrollableMusicBar.stopAutoProgress()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.dropView()
    }

    override fun updatePlayerData(action: Runnable, currentDuration: Int) {
        songTimer.postDelayed(action, 1000)
        songTimer.text = presenter.milliSecondsToTimer(currentDuration.toLong())
    }

    override fun stopPlayer(action: Runnable){
        songTimer.removeCallbacks(action)
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