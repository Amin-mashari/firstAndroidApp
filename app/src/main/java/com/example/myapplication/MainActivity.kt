package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.view.Window
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import com.example.utils.UserLocalStore


lateinit var userLocalDb: UserLocalStore

//todo delete action bar in login and signup
//todo show and fade app logo

class MainActivity : AppCompatActivity() {

    private val DELAY = 5000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //remove action bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE)
        supportActionBar?.hide()
        setContentView(R.layout.activity_logo)

        val topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation)
        val bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation)

        val picLogo = findViewById<ImageView>(R.id.upLogo)
        val textLogo = findViewById<TextView>(R.id.bottomLogo)

        picLogo.startAnimation(topAnim)
        textLogo.startAnimation(bottomAnim)

        Handler(Looper.getMainLooper()).postDelayed({
            userLocalDb = UserLocalStore(this)

            if (userLocalDb.isUserLoggedIn()) {
                startActivity(Intent(this, MainPageActivity::class.java))
                finish()
            } else {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()

            }
        }, DELAY.toLong())
    }


}


