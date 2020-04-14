package dev.virtualplanet.rehabapp.controller

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.provider.ContactsContract
import android.view.View
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import dev.virtualplanet.rehabapp.R
import dev.virtualplanet.rehabapp.model.Exercice
import dev.virtualplanet.rehabapp.model.ExerciceList
import dev.virtualplanet.rehabapp.view.EditProfileActivity
import dev.virtualplanet.rehabapp.view.LoginActivity
import dev.virtualplanet.rehabapp.view.MainActivity
import dev.virtualplanet.rehabapp.view.ProfileActivity
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_profile.view.*
import java.time.LocalDateTime
import java.util.*


object Controller {

    private val data = FirebaseFirestore.getInstance()

    val ex_list = ExerciceList()

    fun getExerciceByName(name: String) : Exercice? {
        return ex_list.getExerciceByName(name)
    }

    fun getExerciceByID(id: String) : Exercice? {
        return null
    }

    fun getUser() {

    }

    /*users.document("test").set(mapOf(
            "name" to "Martin Garrix",
            "mail" to "test",
            "password" to "1234abcd",
            "sex" to "man",
            "age" to 23,
            "weight" to 80,
            "height" to 180,
            "wheel" to false
        ))*/

    fun validateLogin(user: String, pass: String, view: View) {
        if (!user.isBlank() && !pass.isBlank()) {
            data.collection("USERS").document(user)
                .get().addOnSuccessListener {
                    val check_pass = it.get("password").toString()
                    if (check_pass == pass) {
                        val userPreferences = view.context.getSharedPreferences("userInfo", Context.MODE_PRIVATE)
                        val editor: SharedPreferences.Editor = userPreferences.edit()

                        editor.putString("email", user)
                        editor.apply()

                        val intent = Intent(view.context, MainActivity::class.java)
                        view.context.startActivity(intent)
                    } else {
                        val message = Toast.makeText(view.context, "Contraseña incorrecta", Toast.LENGTH_LONG)
                        message.show()
                    }
                }.addOnFailureListener {
                    val message = Toast.makeText(view.context, "Contraseña o usuario incorrecto", Toast.LENGTH_LONG)
                    message.show()
                }

        } else {
            val message = Toast.makeText(view.context, "Alguno de los campos esta vacio", Toast.LENGTH_LONG)
            message.show()
        }

    }

    fun validateRegister(user: String, pass: String, confirm: String, mail: String, view: View) {
        if ((user.isBlank()) || (pass.isBlank()) || (confirm.isBlank()) || (mail.isBlank())) {
            val message = Toast.makeText(view.context, "Alguno de los campos esta vacio", Toast.LENGTH_LONG)
            message.show()
        } else {
            if (pass == confirm) {
                data.collection("USERS").document(mail)
                    .get().addOnSuccessListener {
                        val message = Toast.makeText(view.context, "Esta cuenta ya existe", Toast.LENGTH_LONG)
                        message.show()
                    }.addOnFailureListener {
                        data.collection("USERS").document(mail).set(mapOf(
                            "name" to user,
                            "mail" to mail,
                            "password" to pass,
                            "sex" to "Not Set",
                            "age" to "Not Set",
                            "weight" to "Not Set",
                            "height" to "Not Set",
                            "wheel" to false
                        ))
                        var dataFormat = "" + LocalDateTime.now().dayOfMonth + "/" +
                            LocalDateTime.now().month + "/" + LocalDateTime.now().year
                        data.collection("PROGRESS").document(mail).set(mapOf(
                            dataFormat to 0
                        ))

                        val userPreferences = view.context.getSharedPreferences("userInfo", Context.MODE_PRIVATE)
                        val editor: SharedPreferences.Editor = userPreferences.edit()

                        editor.putString("email", mail)
                        editor.apply()

                        val intent = Intent (view.context, MainActivity::class.java)
                        view.context.startActivity(intent)
                    }

            } else {
                val message = Toast.makeText(view.context, "Las contraseñas no coinciden", Toast.LENGTH_LONG)
                message.show()
            }
        }
    }

    fun changePass(old: String, new: String, confirm: String, view: View) {
        if ((old == "") || (new == "") || (confirm == "")) {
            val message = Toast.makeText(view.context, "Alguno de los campos esta vacio", Toast.LENGTH_LONG)
            message.show()
        } else {
            val userPreferences = view.context.getSharedPreferences("userInfo", Context.MODE_PRIVATE)
            val user = userPreferences.getString("email", "")
            if (user != "") {
                data.collection("USERS").document(user.toString())
                    .get().addOnSuccessListener {
                        val check_pass = it.get("password").toString()
                        if (check_pass == old) {
                            if (new == confirm) {
                                data.collection("USERS").document(user.toString()).update(mapOf(
                                    "password" to new
                                )).addOnSuccessListener {
                                    val message = Toast.makeText(view.context, "Contraseña actualizada", Toast.LENGTH_LONG)
                                    message.show()
                                    val intent = Intent (view.context, MainActivity::class.java)
                                    view.context.startActivity(intent)
                                }.addOnFailureListener {
                                    val message = Toast.makeText(view.context, "Ha sucedido un error", Toast.LENGTH_LONG)
                                    message.show()
                                }

                            } else {
                                val message = Toast.makeText(view.context, "La contraseñas nuevas no coinciden.", Toast.LENGTH_LONG)
                                message.show()
                            }
                        } else {
                            val message = Toast.makeText(view.context, "La contraseña antigua no coinicide.", Toast.LENGTH_LONG)
                            message.show()
                        }
                    }

            }
        }
    }

    fun autologin(context: Context) {
        val userPreferences = context.getSharedPreferences("userInfo", Context.MODE_PRIVATE)
        val user = userPreferences.getString("email", "")
        if (user != "") {
            val intent = Intent (context, MainActivity::class.java)
            context.startActivity(intent)
        }

    }

    fun removeUser(context: Context) {
        val userPreferences = context.getSharedPreferences("userInfo", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = userPreferences.edit()

        editor.remove("email")
        editor.apply()
        val intent = Intent(context, LoginActivity::class.java)
        context.startActivity(intent)
    }

    fun changeProfile(context: ProfileActivity, age: String, height: String, weight: String, wheelchair: Boolean) {

        context.findViewById<TextView>(R.id.textView_Age_Value).text = age
        if (age.equals("Not Set")) {
            context.findViewById<TextView>(R.id.textView_Age_Units).text = ""
        }

        context.findViewById<TextView>(R.id.textView_Height_Value).text = height
        if (height.equals("Not Set")) {
            context.findViewById<TextView>(R.id.textView_Height_Units).text = ""
        }
        context.findViewById<TextView>(R.id.textView_Weight_Value).text = weight
        if (weight.equals("Not Set")) {
            context.findViewById<TextView>(R.id.textView_Weight_Units).text = ""
        }

        if (wheelchair) {
            context.findViewById<TextView>(R.id.textView_WheelChair_Value).text = "SI"
        } else {
            context.findViewById<TextView>(R.id.textView_WheelChair_Value).text = "NO"
        }

        if (height.equals("Not Set") || weight.equals("Not Set")) {
            context.findViewById<TextView>(R.id.textView_IMC_Value).text = "No se puede calcular sin altura y peso"
        } else {
            context.findViewById<TextView>(R.id.textView_IMC_Value).text =
                calculateIMC(weight.toDouble(), (height.toDouble()/100)).toString()
        }
    }

    fun changeProfile(context: ProfileActivity, name: String, age: String, sex: String, height: String,
                      weight: String, wheelchair: Boolean) {

        changeProfile(context, age, height, weight, wheelchair)
        context.findViewById<TextView>(R.id.profile_content).text = name
        context.findViewById<TextView>(R.id.textView_Age_Value).text = age


    }


    fun loadProfile(context: ProfileActivity){
        val userPreferences = context.getSharedPreferences("userInfo", Context.MODE_PRIVATE)
        val user = userPreferences.getString("email", "")
        if (user != "") {
            data.collection("USERS").document(user.toString())
                .get().addOnSuccessListener {
                    val name = it.get("name").toString()
                    val age = it.get("age").toString()
                    val sex = it.get("sex").toString()
                    val height = it.get("height").toString()
                    val weight = it.get("weight").toString()
                    val wheelchair = it.get("wheel").toString().toBoolean()

                    changeProfile(context, name, age, sex, height, weight, wheelchair)
                }
        }
    }

    private fun calculateIMC(weight: Double, height: Double) : Double {
        //kg/m^2
        //DOUBLE TO GET ALL DECIMALS
        return (weight/(height*height))
    }

    fun saveProfileData(context: Context, age: String, weight: String, height: String) {

        val userPreferneces = context.getSharedPreferences("userInfo", Context.MODE_PRIVATE)
        val user = userPreferneces.getString("email", "")

        if (user != "") {
            data.collection("USERS").document(user.toString()).get().addOnSuccessListener {
                if (!age.equals("Not Set")) {
                    data.collection("USERS").document(user.toString()).update(mapOf(
                        "age" to age
                    ))
                }
                if (!weight.equals("Not Set")) {
                    data.collection("USERS").document(user.toString()).update(mapOf(
                        "weight" to weight
                    ))
                }
                if (!height.equals("Not Set")) {
                    data.collection("USERS").document(user.toString()).update(mapOf(
                        "height" to height
                    ))
                }
            }
        }

    }

    fun loadProfileHints(context: EditProfileActivity) {
        val userPreferences = context.getSharedPreferences("userInfo", Context.MODE_PRIVATE)
        val user = userPreferences.getString("email", "")
        if (user != "") {
            data.collection("USERS").document(user.toString())
                .get().addOnSuccessListener {
                    val name = it.get("name").toString()
                    val age = it.get("age").toString()
                    val sex = it.get("sex").toString()
                    val height = it.get("height").toString()
                    val weight = it.get("weight").toString()
                    val wheelchair = it.get("wheel").toString().toBoolean()


                    context.findViewById<TextView>(R.id.e_profile_content).text = name
                    if (!age.equals("Not Set")) {
                        context.findViewById<EditText>(R.id.e_textView_Age_Value).hint = age
                    }

                    if (!height.equals("Not Set")) {
                        context.findViewById<TextView>(R.id.e_textView_Height_Value).hint = height
                    }
                    if (!weight.equals("Not Set")) {
                        context.findViewById<TextView>(R.id.e_textView_Weight_Value).hint = weight
                    }


                    /*TODO
                    if (wheelchair) {
                        context.findViewById<Switch>(R.id.e_textView_WheelChair_Value).text = "SI"
                    } else {
                        context.findViewById<Switch>(R.id.e_textView_WheelChair_Value).
                    }*/

                }
        }
    }


}