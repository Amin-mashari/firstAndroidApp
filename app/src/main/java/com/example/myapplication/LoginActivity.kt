package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.content.Intent
import android.view.View
import android.view.Window
import android.widget.Toast
import com.example.utils.User


class LoginActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //remove action bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE)
        supportActionBar?.hide()
        setContentView(R.layout.activity_login)

        val loginBtn = findViewById<Button>(R.id.loginBtn)
        val registerTxt = findViewById<TextView>(R.id.signupTxt)

        loginBtn.setOnClickListener(this)
        registerTxt.setOnClickListener(this)

    }

    override fun onStart() {
        super.onStart()
        if (userLocalDb.isUserLoggedIn()) {
            startActivity(Intent(this, MainPageActivity::class.java))
            finish()
        }

    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.loginBtn -> {
                logInOperation()
            }
            R.id.signupTxt -> {
                registerOperation()
            }
        }

    }

    private fun registerOperation() {
        startActivity(Intent(this, SignupActivity::class.java))
        finish()
    }

    private fun logInOperation() {
        val usernameTxt = findViewById<TextView>(R.id.edUsername)
        val passTxt = findViewById<TextView>(R.id.edPassword)

        if (userAndPassNotEmpty(usernameTxt, passTxt)) {

            if (isUserExist(usernameTxt, passTxt)) {
                showMessage("Welcome ${usernameTxt.text}")
                saveUser(usernameTxt, passTxt)
                startActivity(Intent(this, MainPageActivity::class.java))
                finish()
            } else
                showMessage("the user not exist! \nplease signup first")

        } else
            showMessage("enter your info first ")
    }

    private fun saveUser(usernameTxt: TextView, passTxt: TextView) {
        var user = User(usernameTxt.text.toString(), passTxt.text.toString())
        userLocalDb.storeUserData(user)
        userLocalDb.setUserLoggedIn()
    }

    private fun isUserExist(usernameTxt: TextView, passTxt: TextView): Boolean {
        val user = userLocalDb.getLoggedInUser()

        return isEqual(user.getUsername(), usernameTxt.text.toString()) &&
                isEqual(user.getPassword(), passTxt.text.toString())
    }

    private fun showMessage(m: String) {
        Toast.makeText(
            this, m, Toast.LENGTH_LONG
        ).show()
    }

    private fun userAndPassNotEmpty(usernameTxt: TextView, passTxt: TextView) =
        usernameTxt.text.trim().isNotEmpty() && passTxt.text.trim().isNotEmpty()

    private fun isEqual(a: String, b: String) =
        a == b
}