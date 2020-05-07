package dev.virtualplanet.rehabapp.controller.data

import dev.virtualplanet.rehabapp.controller.Controller

object UserDataManager {

    fun addNewUser(user: String, pass: String, confirm: String, mail: String, callback: Callback<String>) {
        if ((user.isBlank()) || (pass.isBlank()) || (confirm.isBlank()) || (mail.isBlank())) {
            callback.onCallback("Alguno de los campos esta vacio")
        } else {
            if (pass == confirm) {
                Controller.data.collection(Controller.userTable).document(mail)
                    .get().addOnSuccessListener {
                        callback.onCallback("Esta cuenta ya existe")
                    }.addOnFailureListener {
                        Controller.data.collection(Controller.userTable).document(mail).set(mapOf(
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
                        ProgressExerciceDataManager.generateTestData(mail)

                        callback.onCallback("success")
                    }
            } else {
                callback.onCallback("Las contraseñas no coinciden")
            }
        }

    }

    fun validateLogin(user: String, pass: String, loginCallback: Callback<String>) {
        if (!user.isBlank() && !pass.isBlank()) {
            Controller.data.collection(Controller.userTable).document(user)
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
            Controller.data.collection(Controller.userTable).document(user.toString())
                .get().addOnSuccessListener {
                    val checkPass = it.get("password").toString()
                    if (checkPass == old) {
                        if (new == confirm) {
                            Controller.data.collection(Controller.userTable).document(user.toString()).update(mapOf(
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
}