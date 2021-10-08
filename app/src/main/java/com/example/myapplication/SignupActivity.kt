package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.utils.User


class SignupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //remove action bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE)
        supportActionBar?.hide()
        setContentView(R.layout.activity_signup)

        val signupBtn = findViewById<Button>(R.id.signupBtn)
        val usernameTxt = findViewById<TextView>(R.id.signUsername)
        val passTxt = findViewById<TextView>(R.id.signPassword)
        val confirmPass = findViewById<TextView>(R.id.confirmPass)

        signupBtn.setOnClickListener {

            if (fieldsNotEmpty(usernameTxt, passTxt, confirmPass)) {
                if (isEqual(passTxt, confirmPass)) {
                    showMessage("welcome ${usernameTxt.text}")

                    var user = User(usernameTxt.text.toString(), passTxt.text.toString())
                    userLocalDb.storeUserData(user)
                    userLocalDb.setUserLoggedIn()
                    startActivity(Intent(this, MainPageActivity::class.java))
                    finish()
                } else
                    showMessage("the passwords are not the same")

            } else
                showMessage("enter your info first ")

        }

    }

    private fun fieldsNotEmpty(
        usernameTxt: TextView,
        passTxt: TextView,
        confirmPass: TextView
    ) = usernameTxt.text.isNotEmpty() && passTxt.text.isNotEmpty() && confirmPass.text.isNotEmpty()

    private fun showMessage(m: String) {
        Toast.makeText(
            this, m, Toast.LENGTH_LONG
        ).show()
    }

    private fun isEqual(a: TextView, b: TextView) =
        a.text.toString() == b.text.toString()
}