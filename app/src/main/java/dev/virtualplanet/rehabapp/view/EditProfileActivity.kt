package dev.virtualplanet.rehabapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.view.View
import android.widget.*
import dev.virtualplanet.rehabapp.R
import dev.virtualplanet.rehabapp.controller.Controller
import kotlinx.android.synthetic.main.activity_edit_profile.*

class EditProfileActivity : AppCompatActivity() {

    private val controller = Controller
    private lateinit var user : DataUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)
        this.init()
    }

    private inner class DataUser {
        var name : String
        var sex : String
        var age : String
        var height : String
        var weight : String
        var wheel : Boolean

        constructor(name: String, sex: String, age: String, height: String, weight: String, wheel: Boolean) {
            this.name = name
            this.sex = sex
            this.age = age
            this.height = height
            this.weight = weight
            this.wheel = wheel
        }
    }

    fun init() {
        if (intent.extras != null) {

            val name = intent.getStringExtra("NAME")
            Log.i("myapp23", "$name")
            val sex = intent.getStringExtra("SEX")
            Log.i("myapp23", "$sex")
            val age = intent.getStringExtra("AGE")
            Log.i("myapp23", "$age")
            val height = intent.getStringExtra("HEIGHT")
            Log.i("myapp23", "$height")
            val weight = intent.getStringExtra("WEIGHT")
            Log.i("myapp23", "$weight")
            val wheel = intent.getBooleanExtra("WHEEL", false)
            Log.i("myapp23", "$wheel")
            //user = DataUser(name, sex, age, height, weight, wheel)

            user = DataUser(name, sex, age, height, weight, wheel)
            findViewById<TextView>(R.id.e_profile_content).text = name
            if (!age.equals(Controller.notSetString)) {
                findViewById<EditText>(R.id.e_textView_Age_Value).hint = age
            }

            if (!sex.equals(Controller.notSetString)) {
                findViewById<EditText>(R.id.e_sex_content).hint = sex
            }

            if (!height.equals(Controller.notSetString)) {
                findViewById<TextView>(R.id.e_textView_Height_Value).hint = height
            }
            if (!weight.equals(Controller.notSetString)) {
                findViewById<TextView>(R.id.e_textView_Weight_Value).hint = weight
            }

            if (wheel) {
                findViewById<Switch>(R.id.e_textView_WheelChair_Value).text = "SI"
                findViewById<Switch>(R.id.e_textView_WheelChair_Value).isChecked = true
            } else {
                findViewById<Switch>(R.id.e_textView_WheelChair_Value).text = "NO"
                findViewById<Switch>(R.id.e_textView_WheelChair_Value).isChecked = false
            }

        }
    }

    /* Hay que dejar un timpo entre que los nuevos datos se guarden y que estos se carguen
    en el firebase, pues firebase tarda en actualizarse. Por eso no nos vamos a la pantalla del
    perfil al guardar, sino que mostramos un mensaje de que los datos se han guardado.
    */
    fun saveProfile(view: View) {
        val intent = Intent(view.context, ProfileActivity::class.java)

        val newName = this.e_profile_content.text.toString()
        intent.putExtra("NAME", newName)

        val newSex: String
        val newAge: String
        val newHeight: String
        val newWeight: String
        val newWheelchair = this.e_textView_WheelChair_Value.isChecked

        if (this.e_sex_content.text.toString().isBlank()) {
            newSex = "Not Set"
            intent.putExtra("SEX", user.sex)
        } else {
            newSex = this.e_sex_content.text.toString()
            intent.putExtra("SEX", newSex)
        }


        if (this.e_textView_Age_Value.text.toString().isBlank()) {
            newAge = "Not Set"
            intent.putExtra("AGE", user.age)
        } else {
            newAge = this.e_textView_Age_Value.text.toString()
            intent.putExtra("AGE", newAge)
        }


        if (this.e_textView_Height_Value.text.toString().isBlank()) {
            newHeight = "Not Set"
            intent.putExtra("HEIGHT", user.height)
        } else {
            newHeight = this.e_textView_Height_Value.text.toString()
            intent.putExtra("HEIGHT", newHeight)
        }


        if (this.e_textView_Weight_Value.text.toString().isBlank()) {
            newWeight = "Not Set"
            intent.putExtra("WEIGHT", user.weight)
        } else {
            newWeight = this.e_textView_Weight_Value.text.toString()
            intent.putExtra("WEIGHT", newWeight)
        }


        controller.saveProfileData(this, newSex, newAge, newWeight, newHeight, newWheelchair)



        intent.putExtra("WHEEL", newWheelchair)
        Toast.makeText(view.context, "Datos Guardados", Toast.LENGTH_LONG).show()

        startActivity(intent)



    }

    fun returnToMain(view: View) {
        val intent = Intent(view.context, ProfileActivity::class.java)
        startActivity(intent)
    }

    fun editWC(view: View) {
        if (view.findViewById<Switch>(R.id.e_textView_WheelChair_Value).isChecked) {
            view.findViewById<Switch>(R.id.e_textView_WheelChair_Value).text = "SI"
        } else {
            view.findViewById<Switch>(R.id.e_textView_WheelChair_Value).text = "NO"
        }
    }

    override fun onBackPressed() {
        val intent = Intent(applicationContext, ProfileActivity::class.java)
        startActivity(intent)
    }
}
