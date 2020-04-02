package dev.virtualplanet.rehabapp.controller

import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import dev.virtualplanet.rehabapp.model.Exercice
import dev.virtualplanet.rehabapp.model.ExerciceList
import dev.virtualplanet.rehabapp.view.LoginActivity
import dev.virtualplanet.rehabapp.view.MainActivity


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
        if (user != "") {
            data.collection("USERS").document(user)
                .get().addOnSuccessListener {
                    val check_pass = it.get("password").toString()
                    if (check_pass == pass) {
                        val message = Toast.makeText(view.context, "Contraseña correcta", Toast.LENGTH_LONG)
                        message.show()
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
        if ((user == "") || (pass == "") || (confirm == "") || (mail == "")) {
            val message = Toast.makeText(view.context, "Alguno de los campos esta vacio", Toast.LENGTH_LONG)
            message.show()
        } else {
            if (pass == confirm) {
                data.collection("USERS").document(mail)
                    .get().addOnSuccessListener {
                        val message = Toast.makeText(view.context, "Esta cuenta ya existe", Toast.LENGTH_LONG)
                        message.show()
                    }.addOnFailureListener() {
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
                        val intent = Intent (view.context, MainActivity::class.java)
                        view.context.startActivity(intent)
                    }

            } else {
                val message = Toast.makeText(view.context, "Las contraseñas no coinciden", Toast.LENGTH_LONG)
                message.show()
            }
        }
    }

    fun changePass(user: String, old: String, new: String, confirm: String, view: View) {
        if ((old == "") || (new == "") || (confirm == "")) {
            val message = Toast.makeText(view.context, "Alguno de los campos esta vacio", Toast.LENGTH_LONG)
            message.show()
        } else if (user == "") {
            val message = Toast.makeText(view.context, "Error Shared Preferences", Toast.LENGTH_LONG)
            message.show()
        } else {

        }
    }




}