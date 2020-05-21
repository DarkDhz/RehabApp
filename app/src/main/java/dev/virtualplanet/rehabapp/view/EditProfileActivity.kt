package dev.virtualplanet.rehabapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import dev.virtualplanet.rehabapp.R
import dev.virtualplanet.rehabapp.controller.Controller
import kotlinx.android.synthetic.main.activity_edit_profile.*

class EditProfileActivity : AppCompatActivity() {

    private val controller = Controller

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)
        this.init()
    }

    fun init() {
        Controller.loadProfileHints(this)
    }

    /* Hay que dejar un timpo entre que los nuevos datos se guarden y que estos se carguen
    en el firebase, pues firebase tarda en actualizarse. Por eso no nos vamos a la pantalla del
    perfil al guardar, sino que mostramos un mensaje de que los datos se han guardado.
    */
    fun saveProfile(view: View) {

        val newName = this.e_profile_content.text.toString()
        val newSex : String
        val newAge : String
        val newHeight : String
        val newWeight : String
        val newWheelchair = this.e_textView_WheelChair_Value.isChecked

        if (this.e_sex_content.text.toString().isBlank())
            newSex = "Not Set"
        else
            newSex = this.e_sex_content.text.toString()

        if (this.e_textView_Age_Value.text.toString().isBlank())
            newAge = "Not Set"
        else
            newAge = this.e_textView_Age_Value.text.toString()

        if (this.e_textView_Height_Value.text.toString().isBlank())
            newHeight = "Not Set"
        else
            newHeight = this.e_textView_Height_Value.text.toString()

        if (this.e_textView_Weight_Value.text.toString().isBlank())
            newWeight = "Not Set"
        else
            newWeight = this.e_textView_Weight_Value.text.toString()

        controller.saveProfileData(this, newSex, newAge, newWeight, newHeight, newWheelchair)

        val intent = Intent(view.context, ProfileActivity::class.java)

        intent.putExtra("NAME", newName)
        intent.putExtra("SEX", newSex)
        intent.putExtra("AGE", newAge)
        intent.putExtra("HEIGHT", newHeight)
        intent.putExtra("WEIGHT", newWeight)
        intent.putExtra("WHEEL", newWheelchair)

        startActivity(intent)

        Toast.makeText(view.context, "Datos Guardados", Toast.LENGTH_LONG).show()

    }

    fun returnToMain(view: View) {
        val intent = Intent(view.context, ProfileActivity::class.java)
        startActivity(intent)
    }
}
