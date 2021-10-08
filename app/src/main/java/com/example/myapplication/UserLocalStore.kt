package com.example.myapplication

import android.content.SharedPreferences
import android.content.Context

class UserLocalStore(private val context: Context) {
    private val SP_NAME = "userDetails"
    private var userLocalDb: SharedPreferences = context.getSharedPreferences(SP_NAME, 0)


    fun storeUserData(user: User) {
        val editor: SharedPreferences.Editor = userLocalDb.edit()
        editor.putString("name", user.getUsername())
        editor.putString("password", user.getPassword())
        editor.putString("email", user.getEmail())
        editor.commit()

    }

    fun getLoggedInUser(): User {
        var name = userLocalDb.getString("name", "")
        var pass = userLocalDb.getString("password", "")
        var email = userLocalDb.getString("email", "")
        return User(name.toString(), pass.toString(), email.toString())
    }

    private fun setUserLoggedIn(loggedIn: Boolean) {
        val editor: SharedPreferences.Editor = userLocalDb.edit()
        editor.putBoolean("loggedIn", loggedIn)
        editor.commit()
    }

    fun isUserLoggedIn(): Boolean {
        return userLocalDb.getBoolean("loggedIn", false)
    }

    fun setUserLoggedIn() {
        setUserLoggedIn(true)
    }

    fun setUserLoggedOut() {
        setUserLoggedIn(false)
    }

    fun clearUserData() {
        val editor: SharedPreferences.Editor = userLocalDb.edit()
        editor.clear()
        editor.commit()
    }
}