package com.example.utils

class User() {
    private var username: String = ""
    private var password: String = ""
    private var email = ""

    constructor(username: String, password: String) : this() {
        this.username = username
        this.password = password
    }

    constructor(username: String, password: String, email: String) : this(username, password) {
        this.email = email
    }


    fun getEmail() = email

    fun getUsername() = username

    fun getPassword() = password

    fun updatePassword(password: String) {
        this.password = password
    }

    fun updateUsername(username: String) {
        this.username = username
    }

    fun updateEmail(email: String) {
        this.email = email
    }
}