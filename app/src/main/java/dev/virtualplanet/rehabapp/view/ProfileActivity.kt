package dev.virtualplanet.rehabapp.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
//import com.google.firebase.firestore.FirebaseFirestore
import dev.virtualplanet.rehabapp.R
import dev.virtualplanet.rehabapp.controller.Controller
import kotlinx.android.synthetic.main.activity_profile.*


class ProfileActivity : AppCompatActivity() {

    private val controller = Controller

    //private val data = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        this.init()



    }

    fun editProfile(view: View) {
        val intent = Intent(this, EditProfileActivity::class.java)
        startActivity(intent)
    }

    private fun calculateIMC(weight: Double, height: Double) : Double {
        //kg/m^2
        //DOUBLE TO GET ALL DECIMALS
        return (weight/(height*height))
    }

    private fun init() {




        val extras = intent.extras
        if (extras != null) {
            this.textView_Age_Value.text = extras.getString("AGE")
            this.textView_Height_Value.text = extras.getString("HEIGHT")
            this.textView_Weight_Value.text = extras.getString("WEIGHT")
        } else {
            //DATABASE TESTING "IGNORE"1
            /*
            data.collection("USERS").document("example@gmail.com")
                .get().addOnSuccessListener {
                    val name = it.get("name").toString()
                    val age = it.get("age").toString()
                    val height = it.get("height").toString()
                    val weight = it.get("weight").toString()
                    this.profile_content.text = name
                    this.textView_Age_Value.text = age
                    this.textView_Height_Value.text = height
                    this.textView_Weight_Value.text = weight
                    if (it.get("wheel").toString().toBoolean()) {
                        this.textView_WheelChair_Value.text = "SI"
                    } else {
                        this.textView_WheelChair_Value.text = "SI"
                    }
                    val imc = calculateIMC(weight.toDouble(), (height.toDouble()/100))
                    this.textView_IMC_Value.text = imc.toString()
                }
            */
            // END OF DATABASE TESTING
        }


    }

    fun returnToMain(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
