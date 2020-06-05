package dev.virtualplanet.rehabapp.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.google.firebase.firestore.FirebaseFirestore
import dev.virtualplanet.rehabapp.R
import dev.virtualplanet.rehabapp.controller.Controller
import kotlinx.android.synthetic.main.activity_edit_profile.*
import kotlinx.android.synthetic.main.activity_profile.*


class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        this.init()
    }

    fun editProfile(view: View) {
        val intent = Intent(view.context, EditProfileActivity::class.java)

        val newName = this.profile_content.text.toString()
        intent.putExtra("NAME", newName)

        val newSex: String = this.sex_content.text.toString()
        val newAge: String = this.textView_Age_Value.text.toString()
        val newHeight: String = this.textView_Height_Value.text.toString()
        val newWeight: String = this.textView_Weight_Value.text.toString()
        val tmpWheelchair = this.textView_WheelChair_Value.text.toString()
        val newWheelchair : Boolean

        if (tmpWheelchair == "SI") {
            newWheelchair = true
        } else {
            newWheelchair = false
        }

        intent.putExtra("NAME", newName)
        intent.putExtra("SEX", newSex)
        intent.putExtra("AGE", newAge)
        intent.putExtra("HEIGHT", newHeight)
        intent.putExtra("WEIGHT", newWeight)
        intent.putExtra("WHEEL", newWheelchair)

        startActivity(intent)

    }

    private fun init() {
        if (intent.extras != null) {

            val name = intent.getStringExtra("NAME")
            val sex = intent.getStringExtra("SEX")
            val age = intent.getStringExtra("AGE")
            val height = intent.getStringExtra("HEIGHT")
            val weight = intent.getStringExtra("WEIGHT")
            val wheel = intent.getBooleanExtra("WHEEL", false)

            Controller.changeProfile(this, name, age, sex, height, weight, wheel)
        } else {
            findViewById<TextView>(R.id.textView_Age_Value).isVisible = false
            findViewById<TextView>(R.id.textView_WheelChair_Value).isVisible = false
            findViewById<TextView>(R.id.textView_IMC_Value).isVisible = false
            findViewById<TextView>(R.id.sex_content).isVisible = false
            findViewById<TextView>(R.id.profile_content).isVisible = false
            findViewById<TextView>(R.id.textView_Weight_Value).isVisible = false
            findViewById<TextView>(R.id.textView_Height_Value).isVisible = false

            Controller.loadProfile(this)
        }
    }

    fun returnToMain(view: View) {
        val intent = Intent(view.context, MainActivity::class.java)
        startActivity(intent)
    }

    override fun onBackPressed() {
        val intent = Intent(applicationContext, MainActivity::class.java)
        startActivity(intent)
    }
}
