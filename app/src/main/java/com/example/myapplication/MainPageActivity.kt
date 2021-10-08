package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class MainPageActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_page)

        val logoutTxt = findViewById<TextView>(R.id.logoutTxt)

        logoutTxt.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.logoutTxt -> {
                logOutOperation()
            }
        }
    }

    private fun logOutOperation() {
        userLocalDb.setUserLoggedOut()
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }


}