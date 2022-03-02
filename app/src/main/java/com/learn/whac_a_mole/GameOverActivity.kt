package com.learn.whac_a_mole

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.OnBackPressedCallback

class GameOverActivity : AppCompatActivity() {
    var recordTextView:TextView? = null
    var topRecordTextView:TextView? = null
    private lateinit var prefs: SharedPreferences
    private val APP_TOP_SCORE = "score"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_over)
        topRecordTextView = findViewById(R.id.topRecordTextView)
        recordTextView = findViewById(R.id.recordTextView)
        var score = intent.getIntExtra("score", 0)
        recordTextView?.text = score.toString()

        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val intent:Intent = Intent(this@GameOverActivity,MainActivity::class.java)
                startActivity(intent)
                finishAffinity()
            }
        }
        var prefs = getSharedPreferences("settings", Context.MODE_PRIVATE)
        if (score > prefs.getInt(APP_TOP_SCORE, 0) ) {
            val editor = prefs.edit()
            editor.putInt(APP_TOP_SCORE, score).apply()
        }
        topRecordTextView?.text = prefs.getInt(APP_TOP_SCORE, 0).toString()

    }

    fun restartGame(view: View){
        val intent:Intent = Intent(this@GameOverActivity,GameActivity::class.java)
        startActivity(intent)
        finishAffinity()
    }

    fun goToMenu(view:View){
        val intent:Intent = Intent(this@GameOverActivity,MainActivity::class.java)
        startActivity(intent)
        finishAffinity();
    }
}