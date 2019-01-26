package com.example.petrasbartusis.musictap.main

import com.example.petrasbartusis.taskon_client.base.presenter.BasePresenterImplemetation

class MainPresenter
    : BasePresenterImplemetation<MainContract.View>(),
        MainContract.Presenter {
    override fun onBrowseClicked() {
        onView { browseFiles() }
    }

    override fun onViewReady() {

    }

    override fun playClicked() {
        onView { startPlaying() }
    }

}