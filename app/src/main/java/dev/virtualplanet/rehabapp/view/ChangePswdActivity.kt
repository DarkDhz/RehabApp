package dev.virtualplanet.rehabapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import dev.virtualplanet.rehabapp.R
import dev.virtualplanet.rehabapp.controller.Controller
import kotlinx.android.synthetic.main.activity_change_pswd.*

class ChangePswdActivity : AppCompatActivity() {

    private val controller = Controller

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_pswd)
    }

    fun savePass(view: View) {
        //TODO
        controller.changePass(this.pswd_old_value.text.toString(), this.pswd_new_value.text.toString(),
            this.pswd_repeat.text.toString(), view)
    }

    fun goBack(view: View) {
        val intent = Intent(this, SettingsActivity::class.java)
        startActivity(intent)
    }
}
