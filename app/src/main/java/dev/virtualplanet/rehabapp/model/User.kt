package dev.virtualplanet.rehabapp.model

class User {

    private var user = String()
    private var password = String()
    private var mail = String()
    //private var num = 0

    constructor(us: String, pass: String, ma: String) {
        user = us
        password = pass
        mail = ma
    }

    fun setUser(us: String) {
        this.user = us
    }

    fun getUser(): String {
        return user
    }

}