package com.example.petrasbartusis.musictap.main

import com.example.petrasbartusis.taskon_client.base.presenter.BasePresenter

interface MainContract {
    interface View {
        fun browseFiles()
    }
    interface Presenter: BasePresenter<View> {
        fun onViewReady()
        fun onBrowseClicked()
    }
}