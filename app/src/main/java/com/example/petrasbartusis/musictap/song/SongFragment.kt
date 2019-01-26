package com.example.petrasbartusis.musictap.song

import android.os.Bundle
import android.view.View
import com.example.petrasbartusis.musictap.R
import com.example.petrasbartusis.musictap.Song
import com.example.petrasbartusis.taskon_client.base.BaseFragment

class SongFragment : BaseFragment(), SongContract.View {

    private lateinit var presenter: SongContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = SongPresenter()
        presenter.takeView(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.onViewReady()

    }

    override fun showToast(message: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun resetPlayer() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.dropView()
    }

    override fun layoutRes() = R.layout.fragment_main

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