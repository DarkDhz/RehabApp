package dev.virtualplanet.rehabapp.model

class User {

    private var user = String()

    private var num = 0

    constructor(us: String, n: Int) {
        user = us
        num = n
    }

    fun setUser(us: String) {
        this.user = us
    }

    fun getUser(): String {
        return user
    }

}