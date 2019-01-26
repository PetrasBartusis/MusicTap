package com.example.petrasbartusis.taskon_client.base.presenter

interface BasePresenter<in V> {
    fun takeView(view: V)

    fun dropView()
}