package dev.virtualplanet.rehabapp.controller

//import com.google.android.gms.tasks.OnCompleteListener
//import com.google.firebase.firestore.FirebaseFirestore
//import com.google.firebase.firestore.QuerySnapshot
import dev.virtualplanet.rehabapp.model.Exercice
import dev.virtualplanet.rehabapp.model.ExerciceList


object Controller {

    //private val data = FirebaseFirestore.getInstance()

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

    /*fun validateLogin(user: String, pass: String) : String {
        /*data.collection("users")
            .get()
            .addOnCompleteListener(OnCompleteListener<QuerySnapshot> { task ->
                if (task.isSuccessful) {
                    for (document in task.result!!) {
                        //document.get("")
                    }
                } else {
                    //
                }
            })*/
        var toReturn = "ASD"
        if (user != "") {
            data.collection("USERS").document(user)
                .get().addOnSuccessListener {


                    val check_pass = it.get("password").toString()
                    if (check_pass == pass) {
                        toReturn = "Contraseña correcta"
                    } else {
                        toReturn = "Contraseña incorrecta"
                    }
                }.addOnFailureListener {
                    toReturn = "Contraseña o usuario incorrecto"
                }
        } else {
            return "Alguno de los campos esta vacio"
        }
        return toReturn

    }*/




}