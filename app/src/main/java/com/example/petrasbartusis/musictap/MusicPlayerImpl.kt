package com.example.petrasbartusis.musictap

import android.media.MediaPlayer

class MusicPlayerImpl(
        private val currentPlayer: MediaPlayer?
): MusicPlayer {
    override fun getCurrentDuration(): Int {
        return currentPlayer?.currentPosition ?: 0
    }

    override fun getDuration(): Int {
        return currentPlayer?.duration ?: 0
    }

    override fun isPlaying(): Boolean {
        return currentPlayer?.isPlaying ?: false
    }

    private var currentState = PLAYER_RESET

    override fun pause() {
        if(currentPlayer?.isPlaying == true){
            currentPlayer.pause()
            currentState = PLAYER_PAUSED
        }
    }

    override fun play() {
        if(currentState == PLAYER_PAUSED || currentState == PLAYER_RESET){
            currentPlayer?.start()
            currentState = PLAYER_PLAYING
        }
    }

    override fun stop() {
        currentPlayer?.stop()
        currentPlayer?.release()
        currentState = PLAYER_RESET
    }

    override fun replay() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object {
        const val PLAYER_PLAYING = 0
        const val PLAYER_PAUSED = 1
        const val PLAYER_RESET = 2
    }

}