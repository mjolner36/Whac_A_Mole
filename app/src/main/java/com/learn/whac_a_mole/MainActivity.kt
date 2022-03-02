package com.learn.whac_a_mole

import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.content.SharedPreferences
import android.media.AudioManager
import android.media.MediaPlayer
import android.media.MediaRecorder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    var mediaPlayer:MediaPlayer? = null
    var valueOfScoreTextView:TextView? = null
    private val APP_TOP_SCORE = "score"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mediaPlayer = MediaPlayer.create(this,R.raw.button_press)
        valueOfScoreTextView = findViewById(R.id.valueOfScoreTextView)
        valueOfScoreTextView?.text =  getSharedPreferences("settings", Context.MODE_PRIVATE).getInt(APP_TOP_SCORE, 0).toString()
    }

    private fun playButtonPress() {
        mediaPlayer?.start()
    }

    fun onClick(view: View){
        playButtonPress()
        val intent = Intent(this@MainActivity, GameActivity::class.java)
        startActivity(intent)
    }

}