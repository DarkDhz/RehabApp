package dev.virtualplanet.rehabapp.controller

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.widget.*
import com.google.firebase.firestore.FirebaseFirestore
import dev.virtualplanet.rehabapp.R
import dev.virtualplanet.rehabapp.controller.data.Callback
import dev.virtualplanet.rehabapp.controller.data.ProgressDataManager
import dev.virtualplanet.rehabapp.controller.data.UserDataManager
import dev.virtualplanet.rehabapp.controller.utils.Calculator
import dev.virtualplanet.rehabapp.controller.utils.CraftData
import dev.virtualplanet.rehabapp.model.Exercice
import dev.virtualplanet.rehabapp.model.ExerciceList
import dev.virtualplanet.rehabapp.model.ModelFactory
import dev.virtualplanet.rehabapp.view.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


object Controller {

    val data = FirebaseFirestore.getInstance()
    const val userTable = "USERS"
    const val progressTable = "PROGRESS"
    const val sharedTable = "userInfo"
    const val sharedExercices = "selected"

    const val notSetString = "Not Set"
    
    private val factory = ModelFactory

    fun validateLogin(user: String, pass: String, callback: Callback<String>) {
        UserDataManager.validateLogin(user, pass, object : Callback<String> {
            override fun onCallback(value: String) {
                callback.onCallback(value)
            }
        })
    }



    fun validateRegister(user: String, pass: String, confirm: String, mail: String, callback: Callback<String>) {
        UserDataManager.addNewUser(user, pass, confirm, mail, object : Callback<String> {
            override fun onCallback(value: String) {
                callback.onCallback(value)
            }
        })
    }

    fun changePass(user: String?, old: String, new: String, confirm: String, callback: Callback<String>) {
        UserDataManager.changePass(user, old, new, confirm, object : Callback<String> {
            override fun onCallback(value: String) {
                callback.onCallback(value)
            }
        })
    }

    fun autologin(context: Context) {
        val userPreferences = context.getSharedPreferences(sharedTable, Context.MODE_PRIVATE)
        val user = userPreferences.getString("email", "")
        if (user != "") {
            ProgressDataManager.generateData(user.toString())
            val intent = Intent (context, MainActivity::class.java)
            context.startActivity(intent)
        }

    }



    fun logOut(context: Context) {
        val userPreferences = context.getSharedPreferences(sharedTable, Context.MODE_PRIVATE)
        var editor: SharedPreferences.Editor = userPreferences.edit()

        editor.remove("email")
        editor.apply()

        val exercicePreferences = context.getSharedPreferences(sharedExercices, Context.MODE_PRIVATE)
        editor = exercicePreferences.edit()

        for (key in exercicePreferences.all.keys) {
            editor.remove(key)
        }

        editor.apply()

        val intent = Intent(context, LoginActivity::class.java)
        context.startActivity(intent)

    }

    fun changeProfile(context: ProfileActivity, age: String, height: String, weight: String, wheelchair: Boolean) {

        context.findViewById<TextView>(R.id.textView_Age_Value).text = age
        if (age.equals("notSetString")) {
            context.findViewById<TextView>(R.id.textView_Age_Units).text = ""
        }

        context.findViewById<TextView>(R.id.textView_Height_Value).text = height
        if (height.equals(notSetString)) {
            context.findViewById<TextView>(R.id.textView_Height_Units).text = ""
        }
        context.findViewById<TextView>(R.id.textView_Weight_Value).text = weight
        if (weight.equals(notSetString)) {
            context.findViewById<TextView>(R.id.textView_Weight_Units).text = ""
        }

        if (wheelchair) {
            context.findViewById<TextView>(R.id.textView_WheelChair_Value).text = ("SI")
        } else {
            context.findViewById<TextView>(R.id.textView_WheelChair_Value).text = ("NO")
        }

        if (height.equals(notSetString) || weight.equals(notSetString)) {
            context.findViewById<TextView>(R.id.textView_IMC_Value).text = ("No se puede calcular sin altura y peso")
        } else {
            context.findViewById<TextView>(R.id.textView_IMC_Value).text =
                Calculator.calculateIMC(weight.toDouble(), (height.toDouble()/100)).toString()
        }
    }

    fun changeProfile(context: ProfileActivity, name: String, age: String, sex: String, height: String,
                      weight: String, wheelchair: Boolean) {

        changeProfile(context, age, height, weight, wheelchair)
        context.findViewById<TextView>(R.id.profile_content).text = name
        context.findViewById<TextView>(R.id.textView_Age_Value).text = age


    }

    fun loadProfile(context: ProfileActivity){
        val userPreferences = context.getSharedPreferences(sharedTable, Context.MODE_PRIVATE)
        val user = userPreferences.getString("email", "")
        if (user != "") {
            data.collection(userTable).document(user.toString())
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



    /**
     * Method to save File Data
     */
    fun saveProfileData(context: Context, age: String, weight: String, height: String, wheelChair : Boolean) {

        val userPreferences = context.getSharedPreferences(sharedTable, Context.MODE_PRIVATE)
        val user = userPreferences.getString("email", "")

        if (user != "") {
            data.collection(userTable).document(user.toString()).get().addOnSuccessListener {
                if (!age.equals(notSetString)) {
                    data.collection(userTable).document(user.toString()).update(mapOf(
                        "age" to age
                    ))
                }
                if (!weight.equals(notSetString)) {
                    data.collection(userTable).document(user.toString()).update(mapOf(
                        "weight" to weight
                    ))
                }
                if (!height.equals(notSetString)) {
                    data.collection(userTable).document(user.toString()).update(mapOf(
                        "height" to height
                    ))
                }
                data.collection(userTable).document(user.toString()).update(mapOf(
                    "wheel" to wheelChair
                ))
            }
        }

    }

    /**
     * Method to load the hints content from EditProfileActivity
     */
    fun loadProfileHints(context: EditProfileActivity) {
        val userPreferences = context.getSharedPreferences(sharedTable, Context.MODE_PRIVATE)
        val user = userPreferences.getString("email", "")
        if (user != "") {
            data.collection(userTable).document(user.toString())
                .get().addOnSuccessListener {
                    val name = it.get("name").toString()
                    val age = it.get("age").toString()
                    val sex = it.get("sex").toString()
                    val height = it.get("height").toString()
                    val weight = it.get("weight").toString()
                    val wheelchair = it.get("wheel").toString().toBoolean()


                    context.findViewById<TextView>(R.id.e_profile_content).text = name
                    if (!age.equals(notSetString)) {
                        context.findViewById<EditText>(R.id.e_textView_Age_Value).hint = age
                    }

                    if (!height.equals(notSetString)) {
                        context.findViewById<TextView>(R.id.e_textView_Height_Value).hint = height
                    }
                    if (!weight.equals(notSetString)) {
                        context.findViewById<TextView>(R.id.e_textView_Weight_Value).hint = weight
                    }

                    if (wheelchair) {
                        context.findViewById<Switch>(R.id.e_textView_WheelChair_Value).text = "SI"
                        context.findViewById<Switch>(R.id.e_textView_WheelChair_Value).setChecked(true)
                    } else {
                        context.findViewById<Switch>(R.id.e_textView_WheelChair_Value).text = "NO"
                    }

                }
        }
    }

    /**
     * Method to load All the chart data
     */
    fun loadExericeProgressData(context: LinearProgressActivity){
        val userPreferences = context.getSharedPreferences(sharedTable, Context.MODE_PRIVATE)
        val user = userPreferences.getString("email", "")

        if (user != "") {
            ProgressDataManager.loadExericeProgressData(context, user.toString())
        }
    }

    fun loadExericeProgressData(context: LinearProgressActivity, month : Int, year: Int){
        val userPreferences = context.getSharedPreferences(sharedTable, Context.MODE_PRIVATE)
        val user = userPreferences.getString("email", "")
        if (user != "") {
            ProgressDataManager.loadExericeProgressData(context, month, year, user.toString())
        }
    }

    fun loadExericeProgressData(context: BarProgressActivity){
        val userPreferences = context.getSharedPreferences(sharedTable, Context.MODE_PRIVATE)
        val user = userPreferences.getString("email", "")
        if (user != "") {
            ProgressDataManager.loadExericeProgressData(context, user.toString())
        }
    }

    fun loadExericeProgressData(context: BarProgressActivity, month : Int, year: Int){
        val userPreferences = context.getSharedPreferences(sharedTable, Context.MODE_PRIVATE)
        val user = userPreferences.getString("email", "")
        if (user != "") {
            ProgressDataManager.loadExericeProgressData(context, month, year, user.toString())
        }
    }



    fun addSavedExercices(context: Context, name : String) : Boolean {
        val exercicePreferences = context.getSharedPreferences(sharedExercices, Context.MODE_PRIVATE)
        val valid = exercicePreferences.getString(name, "null")
        if (valid == "null") {
            val editor: SharedPreferences.Editor = exercicePreferences.edit()
            editor.putString(name, "valid")
            editor.apply()
            return true
        } else {
            return false
        }

    }

    fun loadSavedExercices(context: Context) : ArrayList<String> {
        val toReturn = ArrayList<String>()
        val exercicePreferences = context.getSharedPreferences(sharedExercices, Context.MODE_PRIVATE)
        for ( value in exercicePreferences.all.keys) {
            toReturn.add(value)
        }
        //Toast.makeText(context, toReturn.toString(), Toast.LENGTH_LONG).show()
        return toReturn
    }

    fun removeSavedExercice(context: Context, name: String) : Boolean {
        val exercicePreferences = context.getSharedPreferences(sharedExercices, Context.MODE_PRIVATE)
        val valid = exercicePreferences.getString(name, "null")
        if (valid != "null") {
            val editor: SharedPreferences.Editor = exercicePreferences.edit()
            editor.remove(name)
            editor.apply()
            return true
        } else {
            return false
        }
    }

    fun createExerciceList(): ExerciceList {
        return factory.makeExerciceList()
    }

    fun createExercice(n: String, des: String, rep: Int, ser: Int, time: Int, path : String) : Exercice {
       return factory.makeExerice(n, des, rep, ser, time, path)
    }

    fun addExerciceCount(user: String?, num: Int) {
        if (user != "") {
            val date = CraftData.craftData()

            data.collection(progressTable).document(user.toString()).get().addOnSuccessListener {
                val actual : String? = it.get(date).toString()

                if (actual == null || actual != "" || actual == "null") {
                    val act = actual!!.toInt()
                    data.collection(progressTable).document(user.toString()).update(
                        mapOf(
                            date to (act + num)
                        )
                    )
                }
            }
        }
    }

    fun getList(name: String, packageName: String) : ExerciceList {
        val exerciceList : ExerciceList = createExerciceList()
        val path = "android.resource://$packageName/"
        when (name) {
            "Hombro" -> {

                exerciceList.add(createExercice("Flexión del Hombro", "Eleve su brazo hasta señalar el techo manteniendo su codo.",
                    15, 3, 30, path + R.raw.hombro_flexion))

                exerciceList.add(createExercice("Rotación del Hombro con apoyo", "Mantenga el codo apoyado en un lugar (como muestra la figura) y las escápulas (paletas) hacia abajo y juntas. Gire el antebrazo hacia delante y hacia atrás.",
                    10, 1, 20, path + R.raw.hombro_rot_in))

                exerciceList.add(createExercice("Caminado por pared de dedos", "Con el codo, utilice los dedos para caminar hacia arriba por la pared o en el marco de la puerta lo más alto posible.",
                    10, 3, 20, path + R.raw.hombro_pared))

                exerciceList.add(createExercice("Rotación Interna del Hombro", "Lleve su mano detrás de la espalda y hacia el lado opuesto.",
                    10, 1, 20, path + R.raw.hombro_rotacion))

                exerciceList.add(createExercice("Abducción del Hombro", "Levante los brazos lateralmente, con los codos  y las palmas hacia abajo. No encoja los hombros, ni incline el tronco.",
                    10, 3, 20, path + R.raw.hombro_abd))
                return exerciceList
            }
            "Codo" -> {
                exerciceList.add(createExercice("Flexo-Extensión del codo", "Elevar lentamente la extremidad superior hasta que esté totalmente horizontal con el codo extendido.",
                    15, 3, 30, path + R.raw.codo_fl_ex))

                exerciceList.add(createExercice("Flexión de codo", "Realizar rotación de la extremidad de forma que la mano (conel peso) mire hacia arriba.",
                    15, 3, 30, path + R.raw.codo_fl))

                exerciceList.add(createExercice("Flexo-Extensión del codo estirado", "Estirado, los brazos extendidos lateralmente a la altura de los hombros,  flexión-extensión de los antebrazos sobre los brazos.",
                    15, 3, 30, path + R.raw.codo_estirado))

                exerciceList.add(createExercice("Flexo-Extensión del codo frontal", "Sentado en una silla o de pie, las manos juntas sobre el pecho, extender los brazos hacia delante.",
                    15, 3, 30, path + R.raw.codo_adelante))

                return exerciceList
            }
        }
        return exerciceList
    }


}