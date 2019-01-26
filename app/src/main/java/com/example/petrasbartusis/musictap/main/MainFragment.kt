package com.example.petrasbartusis.musictap.main

import android.Manifest
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import com.example.petrasbartusis.musictap.R
import com.example.petrasbartusis.musictap.musicList.MusicListActivity
import com.example.petrasbartusis.taskon_client.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : BaseFragment(), MainContract.View {

    private lateinit var presenter: MainContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = MainPresenter()
        presenter.takeView(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.onViewReady()
        btnChoose.setOnClickListener {
            //check for permissions
            if(ContextCompat.checkSelfPermission(activity!!.applicationContext,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED ||
                    ContextCompat.checkSelfPermission(activity!!.applicationContext,
                            Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){
                //ask for permission
                requestPermissions(
                        arrayOf(
                                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.READ_EXTERNAL_STORAGE
                        ),
                        WRITE_EXTERNAL_STORAGE
                )
            } else {
                presenter.onBrowseClicked()
            }
        }
    }

    override fun browseFiles() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val chooseDir = Intent(Intent.ACTION_OPEN_DOCUMENT_TREE)
            chooseDir.addCategory(Intent.CATEGORY_DEFAULT)
            startActivityForResult(
                    Intent.createChooser(
                            chooseDir,
                            "Choose directory"
                    ), ACTIVITY_CHOOSE_FILE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode != RESULT_OK) return

        if (requestCode == ACTIVITY_CHOOSE_FILE) {
            val uri = data!!.data
            var filePath = uri?.path
            filePath = filePath?.replace("/tree/raw:/", "")
            goToMusicList(filePath)
        }
    }

    private fun goToMusicList(directoryPath: String?) {
        val musicListIntent = MusicListActivity
                .createIntent(context!!)
                .putExtra(DIRECTORY_PATH, directoryPath)
        startActivity(musicListIntent)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.dropView()
    }

    override fun layoutRes() = R.layout.fragment_main

    companion object {
        const val DIRECTORY_PATH = "directoryPath"
        const val ACTIVITY_CHOOSE_FILE = 0
        const val WRITE_EXTERNAL_STORAGE = 0


        fun newInstance(): MainFragment {
            return MainFragment()
        }
    }
}