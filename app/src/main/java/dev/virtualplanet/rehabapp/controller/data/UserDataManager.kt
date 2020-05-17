package dev.virtualplanet.rehabapp.controller.data

import android.content.Context
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import dev.virtualplanet.rehabapp.R
import dev.virtualplanet.rehabapp.controller.Controller
import dev.virtualplanet.rehabapp.controller.utils.CraftData
import dev.virtualplanet.rehabapp.controller.utils.ProfileHelper
import dev.virtualplanet.rehabapp.view.EditProfileActivity
import dev.virtualplanet.rehabapp.view.ProfileActivity

object UserDataManager {

    private const val userTable = "USERS"

    fun saveProfileData(context: Context, age: String, weight: String, height: String, wheelChair : Boolean, user: String) {

        Controller.data.collection(userTable).document(user).get().addOnSuccessListener {
            if (!age.equals(Controller.notSetString)) {
                Controller.data.collection(userTable).document(user).update(mapOf(
                    "age" to age
                ))
            }
            if (!weight.equals(Controller.notSetString)) {
                Controller.data.collection(userTable).document(user).update(mapOf(
                    "weight" to weight
                ))
            }
            if (!height.equals(Controller.notSetString)) {
                Controller.data.collection(userTable).document(user).update(mapOf(
                    "height" to height
                ))
            }
            Controller.data.collection(userTable).document(user).update(mapOf(
                "wheel" to wheelChair
            ))
        }


    }

    fun loadProfile(context: ProfileActivity, user: String){
        Controller.data.collection(userTable).document(user)
            .get().addOnSuccessListener {
                val name = it.get("name").toString()
                val age = it.get("age").toString()
                val sex = it.get("sex").toString()
                val height = it.get("height").toString()
                val weight = it.get("weight").toString()
                val wheelchair = it.get("wheel").toString().toBoolean()
                ProfileHelper.changeProfile(context, name, age, sex, height, weight, wheelchair)
            }

    }

    fun addNewUser(user: String, pass: String, confirm: String, mail: String, callback: Callback<String>) {
        if ((user.isBlank()) || (pass.isBlank()) || (confirm.isBlank()) || (mail.isBlank())) {
            callback.onCallback("Alguno de los campos esta vacio")
        } else {
            if (pass == confirm) {
                Controller.data.collection(userTable).document(mail)
                    .get().addOnSuccessListener {
                        callback.onCallback("Esta cuenta ya existe")
                    }.addOnFailureListener {
                        Controller.data.collection(userTable).document(mail).set(mapOf(
                            "name" to user,
                            "mail" to mail,
                            "password" to pass,
                            "sex" to Controller.notSetString,
                            "age" to Controller.notSetString,
                            "weight" to Controller.notSetString,
                            "height" to Controller.notSetString,
                            "wheel" to false
                        ))
                        //TODO REMOVER PARA LA ENTREGA
                        generateTestData(mail)

                        callback.onCallback("success")
                    }
            } else {
                callback.onCallback("Las contraseñas no coinciden")
            }
        }

    }

    private fun generateTestData(mail: String) {
        Controller.data.collection(Controller.progressExerciceTable).document(mail).set(mapOf(
            CraftData.craftData() to 15,
            CraftData.craftData(23) to 0,
            CraftData.craftData(7) to 5,
            CraftData.craftData(18) to 9,
            CraftData.craftData(2) to 12
        ))
    }

    fun validateLogin(user: String, pass: String, loginCallback: Callback<String>) {
        if (!user.isBlank() && !pass.isBlank()) {
            Controller.data.collection(userTable).document(user)
                .get().addOnSuccessListener {
                    val checkPass = it.get("password").toString()

                    if (checkPass == pass) {
                        loginCallback.onCallback("success")
                    } else {
                        loginCallback.onCallback("Contraseña incorrecta")
                    }
                }.addOnFailureListener {
                    loginCallback.onCallback("Contraseña o usuario incorrecto")
                }

        } else {
            loginCallback.onCallback("Alguno de los campos esta vacio")
        }
    }

    fun changePass(user: String?, old: String, new: String, confirm: String, callback: Callback<String>) {
        if (old.isBlank()|| new.isBlank() || confirm.isBlank() || user!!.isBlank()) {
            callback.onCallback("Alguno de los campos esta vacio")
        } else {
            Controller.data.collection(userTable).document(user.toString())
                .get().addOnSuccessListener {
                    val checkPass = it.get("password").toString()
                    if (checkPass == old) {
                        if (new == confirm) {
                            Controller.data.collection(userTable).document(user.toString()).update(mapOf(
                                "password" to new
                            ))
                            callback.onCallback("success")
                        } else {
                            callback.onCallback("La contraseñas nuevas no coinciden.")
                        }
                    } else {
                        callback.onCallback("La contraseña antigua no coinicide.")
                    }
                }


        }
    }

    fun loadProfileHints(context: EditProfileActivity, user: String) {
        Controller.data.collection(userTable).document(user).get().addOnSuccessListener {
                val name = it.get("name").toString()
                val age = it.get("age").toString()
                //val sex = it.get("sex").toString()
                val height = it.get("height").toString()
                val weight = it.get("weight").toString()
                val wheelchair = it.get("wheel").toString().toBoolean()


                context.findViewById<TextView>(R.id.e_profile_content).text = name
                if (!age.equals(Controller.notSetString)) {
                    context.findViewById<EditText>(R.id.e_textView_Age_Value).hint = age
                }

                if (!height.equals(Controller.notSetString)) {
                    context.findViewById<TextView>(R.id.e_textView_Height_Value).hint = height
                }
                if (!weight.equals(Controller.notSetString)) {
                    context.findViewById<TextView>(R.id.e_textView_Weight_Value).hint = weight
                }

                if (wheelchair) {
                    context.findViewById<Switch>(R.id.e_textView_WheelChair_Value).text = "SI"
                    context.findViewById<Switch>(R.id.e_textView_WheelChair_Value).isChecked = true
                } else {
                    context.findViewById<Switch>(R.id.e_textView_WheelChair_Value).text = "NO"
                }

            }

    }
}