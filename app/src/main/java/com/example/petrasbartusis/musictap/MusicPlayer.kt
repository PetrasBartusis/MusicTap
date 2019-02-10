package com.example.petrasbartusis.musictap

interface MusicPlayer {
    fun pause()
    fun play()
    fun stop()
    fun replay()
    fun isPlaying(): Boolean
    fun getDuration(): Int
    fun getCurrentDuration(): Int
}