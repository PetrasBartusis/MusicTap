package com.example.petrasbartusis.musictap.song

import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.view.View
import com.example.petrasbartusis.musictap.R
import com.example.petrasbartusis.musictap.Song
import com.example.petrasbartusis.taskon_client.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_song.*
import java.io.File
import androidx.core.os.HandlerCompat.postDelayed



class SongFragment : BaseFragment(), SongContract.View {

    private lateinit var presenter: SongContract.Presenter

    private var currentPlayer: MediaPlayer? = null

    private lateinit var song: Song

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = SongPresenter()
        song = arguments?.getSerializable(KEY_SONG) as Song
        presenter.takeView(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.onViewReady()

        songTitle.text = song.name

        btnPlay.setOnClickListener {
            if(currentPlayer?.isPlaying == true){
                presenter.stopPlaying(currentPlayer)
                btnPlay.setImageResource(R.drawable.ic_play_light)
            } else {
                presenter.playSong(currentPlayer)
                btnPlay.setImageResource(R.drawable.ic_refresh_light)
                songTimer.post(mUpdateTime)
            }
        }
    }

    override fun setUpPlayer() {
        currentPlayer = MediaPlayer.create(context, Uri.fromFile(File(song.path)))
        scrollableMusicBar.loadFrom(song.path, currentPlayer!!.duration)
    }

    override fun showToast(message: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun startScrolling() {
        scrollableMusicBar.startAutoProgress(1.0f)
    }

    override fun stopScrolling() {
        currentPlayer = MediaPlayer.create(context, Uri.fromFile(File(song.path)))
        scrollableMusicBar.stopAutoProgress()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.dropView()
    }

    private val mUpdateTime = object : Runnable {
        override fun run() {
            val currentDuration: Int
            if (currentPlayer!=null && currentPlayer?.isPlaying == true) {
                currentDuration = currentPlayer?.currentPosition ?: 0
                updatePlayer(currentDuration)
                songTimer.postDelayed(this, 1000)
            } else {
                songTimer.removeCallbacks(this)
            }
        }
    }

    private fun updatePlayer(currentDuration: Int) {
        songTimer.text = "" + milliSecondsToTimer(currentDuration.toLong())
    }

    private fun milliSecondsToTimer(milliseconds: Long): String {
        var finalTimerString = ""
        var secondsString = ""

        // Convert total duration into time
        val hours = (milliseconds / (1000 * 60 * 60)).toInt()
        val minutes = (milliseconds % (1000 * 60 * 60)).toInt() / (1000 * 60)
        val seconds = (milliseconds % (1000 * 60 * 60) % (1000 * 60) / 1000).toInt()
        // Add hours if there
        if (hours > 0) {
            finalTimerString = hours.toString() + ":"
        }

        // Prepending 0 to seconds if it is one digit
        secondsString = if (seconds < 10) {
            "0$seconds"
        } else {
            "" + seconds
        }

        finalTimerString = "$finalTimerString$minutes:$secondsString"

        // return timer string
        return finalTimerString
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