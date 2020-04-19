package dev.virtualplanet.rehabapp.view

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Switch
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import dev.virtualplanet.rehabapp.R
import dev.virtualplanet.rehabapp.controller.Controller
import kotlinx.android.synthetic.main.activity_edit_profile.*
import kotlinx.android.synthetic.main.activity_edit_profile.view.*

class EditProfileActivity : AppCompatActivity() {

    private val controller = Controller

    private val data = FirebaseFirestore.getInstance()

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

        val new_age : String
        val new_height : String
        val new_weight : String
        val new_wheelChair : Boolean

        if (this.e_textView_Age_Value.text.toString().isBlank())
            new_age = "Not Set"
        else
            new_age = this.e_textView_Age_Value.text.toString()

        if (this.e_textView_Height_Value.text.toString().isBlank())
            new_height = "Not Set"
        else
            new_height = this.e_textView_Height_Value.text.toString()

        if (this.e_textView_Weight_Value.text.toString().isBlank())
            new_weight = "Not Set"
        else
            new_weight = this.e_textView_Weight_Value.text.toString()

        new_wheelChair = this.e_textView_WheelChair_Value.isChecked

        controller.saveProfileData(this, new_age, new_weight, new_height, new_wheelChair)

        val intent = Intent(this, ProfileActivity::class.java)

        intent.putExtra("AGE", new_age)
        intent.putExtra("HEIGHT", new_height)
        intent.putExtra("WEIGHT", new_weight)
        intent.putExtra("WHEEL", new_wheelChair)

        startActivity(intent)

        //Toast.makeText(this, "Datos Guardados", Toast.LENGTH_LONG).show()

    }

    fun returnToMain(view: View) {
        val intent = Intent(this, ProfileActivity::class.java)
        startActivity(intent)
    }
}
