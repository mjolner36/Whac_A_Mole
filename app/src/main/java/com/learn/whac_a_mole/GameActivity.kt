package com.learn.whac_a_mole

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView

import androidx.activity.OnBackPressedCallback
import androidx.gridlayout.widget.GridLayout


var timerTextView: TextView? = null
val GAME_TIMER:Long = 30
val MOLE_TIMER:Long = 5
var timerSound:MediaPlayer? = null
var holes: GridLayout? = null
var HOLES_ROWS = 3
var HOLES_COLUMNS = 3
var hole:ImageView? = null
var gameMode = true
var score = 0;
var numberOfMole:Int = -1
private var timer:CountDownTimer? =null
private var moleTimer:CountDownTimer? =null
var scoreTextView:TextView? = null

class GameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        timerTextView  = findViewById(R.id.timerTextView)
        timerSound = MediaPlayer.create(this,R.raw.timer_sound)
        scoreTextView = findViewById(R.id.gameScoreTextView)
        holes = findViewById(R.id.holes);
        startTimer()
        gameStart()



        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val intent:Intent = Intent(this@GameActivity,MainActivity::class.java)
                startActivity(intent)
                finishAffinity();
            }
        }

    }

    fun gameStart(){
        setMoleRandomPosition()
        startTimerMole()
    }



    fun setMoleRandomPosition(){
        numberOfMole = (0 until 9).random()
        hole = holes?.getChildAt(numberOfMole) as ImageView;
        hole?.setImageResource(R.drawable.krot_and_hole)
    }


    fun clickOnMole(view: View){
        if (view.tag == numberOfMole.toString()){
            score += 1
            scoreTextView?.text = score.toString()
            hole?.setImageResource(R.drawable.hole_ground_brown)
        }

    }


    private fun startTimerMole(){
        moleTimer?.cancel()
        moleTimer = object :CountDownTimer(MOLE_TIMER * 100 + 10,1000){
            override fun onTick(l: Long) {
               //nothing
            }

            override fun onFinish() {
                hole?.setImageResource(R.drawable.hole_ground_brown)
                gameStart()
            }
        }.start()
    }


    private fun startTimer(){
       timer?.cancel()
       timer = object : CountDownTimer(GAME_TIMER * 1000 + 100, 1000) {
            override fun onTick(l: Long) {
                timerTextView?.text = (l/1000).toString()+"s"
            }

            override fun onFinish() {
                timerSound?.start()
                val intent:Intent = Intent(this@GameActivity,GameOverActivity::class.java)
                intent.putExtra("score",score)
                finishAffinity();
                startActivity(intent)
            }
        }.start()

    }

}

