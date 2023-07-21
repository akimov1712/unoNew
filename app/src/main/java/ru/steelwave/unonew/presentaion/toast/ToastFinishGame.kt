package ru.steelwave.unonew.presentaion.toast

import android.content.Context
import android.media.MediaPlayer
import android.os.Handler
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.Toast
import ru.steelwave.unonew.R
import kotlin.random.Random

class ToastFinishGame {

    companion object{

        private var isPlaying = false
        private val handler = Handler()
        private const val DELAY_IN_MILLIS: Long = 8000

        private var song: MediaPlayer? = null

        fun toastFinishGame(context: Context){
            if (!isPlaying) {
                isPlaying = true
                val random = Random.nextInt(1,7)
                song = when(random){
                    1 -> MediaPlayer.create(context, R.raw.music1)
                    2 -> MediaPlayer.create(context, R.raw.music2)
                    3 -> MediaPlayer.create(context, R.raw.music3)
                    4 -> MediaPlayer.create(context, R.raw.music4)
                    5 -> MediaPlayer.create(context, R.raw.music5)
                    else -> MediaPlayer.create(context, R.raw.music6)
                }
                song?.start()
                val toast = Toast(context)
                val view = LayoutInflater.from(context).inflate(
                    R.layout.toast_finish_game,
                    null,
                    false
                )
                toast.setView(view)
                toast.duration = Toast.LENGTH_LONG
                toast.setGravity(Gravity.CENTER_VERTICAL,0,0)
                toast.show()
                handler.postDelayed({
                    onMelodyCompletion()
                }, DELAY_IN_MILLIS)
            }
        }

        private fun onMelodyCompletion() {
            isPlaying = false
        }

    }

}