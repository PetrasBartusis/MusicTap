package com.example.petrasbartusis.musictap.main

import android.app.Activity.RESULT_OK
import android.os.Bundle
import android.view.View
import com.example.petrasbartusis.musictap.R
import com.example.petrasbartusis.taskon_client.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_main.*
import android.content.Intent

class MainFragment : BaseFragment(), MainContract.View {

    private lateinit var presenter: MainContract.Presenter
    private var isPlaying = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = MainPresenter()
        presenter.takeView(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.onViewReady()
        playPause.setOnClickListener {
            presenter.playClicked()
        }
        btnChoose.setOnClickListener {
            presenter.onBrowseClicked()
        }
//        btn_rhythm.setOnClickListener {
//            btn_rhythm.text = StringBuilder(btn_rhythm.text.toString()).append("11").toString()
//        }
    }

    override fun browseFiles() {
        val chooseFile = Intent(Intent.ACTION_GET_CONTENT)
        chooseFile.addCategory(Intent.CATEGORY_OPENABLE)
        chooseFile.type = "*/*"
        val intent = Intent.createChooser(chooseFile, "Choose a file")
        startActivityForResult(intent, ACTIVITY_CHOOSE_FILE)
    }

    override fun startPlaying() {
        isPlaying = if(isPlaying){
            playPause.setBackgroundResource(R.drawable.ic_play)
            false
        } else {
            playPause.setBackgroundResource(R.drawable.ic_pause)
            true
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode != RESULT_OK) return
        val path = ""
        if (requestCode == ACTIVITY_CHOOSE_FILE) {
            val uri = data!!.data
            val FilePath = uri.path
            print("Path  = $FilePath")
        }
    }

    override fun layoutRes() = R.layout.fragment_main

    companion object {
        const val ACTIVITY_CHOOSE_FILE = 0
        fun newInstance(): MainFragment {
            return MainFragment()
        }
    }
}