package com.example.petrasbartusis.musictap.musicList

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.petrasbartusis.musictap.R
import com.example.petrasbartusis.musictap.Song
import com.example.petrasbartusis.musictap.song.SongActivity
import com.example.petrasbartusis.taskon_client.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_music_list.*

class MusicListFragment :
        BaseFragment(),
        MusicListContract.View,
        OnSongClickListener {
    private lateinit var presenter: MusicListContract.Presenter

    private var songAdapter: SongAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = MusicListPresenter()
        presenter.takeView(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        songAdapter = SongAdapter(this)
        songList.adapter = songAdapter
        songList.layoutManager = LinearLayoutManager(activity)
        presenter.onViewReady(arguments?.getString(MusicListFragment.DIRECTORY_PATH)?:"")
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.dropView()
    }

    override fun setItems(songList: List<Song>) {
        songAdapter?.setItems(songList)
    }

    override fun onSongClicked(song: Song, itemView: View) {
        val songIntent = SongActivity
                .createIntent(context!!)
                .putExtra(KEY_SONG, song)
        startActivity(songIntent)
    }

    override fun showToast(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
    }

    override fun layoutRes() = R.layout.fragment_music_list

    companion object {
        const val DIRECTORY_PATH = "directoryPath"
        const val KEY_SONG = "key_song"

        fun newInstance(path: String): MusicListFragment {
            val fragment = MusicListFragment()
            val bundle = Bundle()
            bundle.putSerializable(DIRECTORY_PATH, path)
            fragment.arguments = bundle
            return fragment
        }
    }
}