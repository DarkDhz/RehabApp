package dev.virtualplanet.rehabapp.controller

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.net.Uri
import android.util.Log
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat.startActivity
import androidx.core.view.isVisible
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.google.firebase.firestore.FirebaseFirestore
import dev.virtualplanet.rehabapp.R
import dev.virtualplanet.rehabapp.model.Exercice
import dev.virtualplanet.rehabapp.model.ExerciceList
import dev.virtualplanet.rehabapp.model.ModelFactory
import dev.virtualplanet.rehabapp.view.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


object Controller {

    private val data = FirebaseFirestore.getInstance()
    private const val userTable = "USERS"
    private const val progressTable = "PROGRESS"
    const val sharedTable = "userInfo"
    const val sharedExercices = "selected"

    private const val notSetString = "Not Set"


    //TODO https://stackoverflow.com/questions/15832335/android-custom-row-item-for-listview
    private val factory = ModelFactory

    private val exList = factory.makeExerciceList()

    fun getExerciceByName(name: String) : Exercice? {
        return exList.getExerciceByName(name)
    }

    fun getExerciceByID(id: String) : Exercice? {
        return null
    }

    fun validateLogin(user: String, pass: String, loginCallback: Callback<String>) {
        if (!user.isBlank() && !pass.isBlank()) {
            data.collection(userTable).document(user)
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



    fun validateRegister(user: String, pass: String, confirm: String, mail: String, callback: Callback<String>) {
        if ((user.isBlank()) || (pass.isBlank()) || (confirm.isBlank()) || (mail.isBlank())) {
            callback.onCallback("Alguno de los campos esta vacio")
        } else {
            if (pass == confirm) {
                data.collection(userTable).document(mail)
                    .get().addOnSuccessListener {
                        callback.onCallback("Esta cuenta ya existe")
                    }.addOnFailureListener {
                        data.collection(userTable).document(mail).set(mapOf(
                            "name" to user,
                            "mail" to mail,
                            "password" to pass,
                            "sex" to notSetString,
                            "age" to notSetString,
                            "weight" to notSetString,
                            "height" to notSetString,
                            "wheel" to false
                        ))
                        //TODO REMOVER PARA LA ENTREGA
                        data.collection(progressTable).document(mail).set(mapOf(
                            craftData() to 15,
                            craftData(23) to 0,
                            craftData(7) to 5,
                            craftData(18) to 9,
                            craftData(2) to 12
                        ))
                        callback.onCallback("success")
                    }

            } else {
                callback.onCallback("Las contraseñas no coinciden")
            }
        }
    }

    fun changePass(user: String?, old: String, new: String, confirm: String, callback: Callback<String>) {
        if (old.isBlank()|| new.isBlank() || confirm.isBlank() || user!!.isBlank()) {
            callback.onCallback("Alguno de los campos esta vacio")
        } else {
            data.collection(userTable).document(user.toString())
                .get().addOnSuccessListener {
                    val checkPass = it.get("password").toString()
                    if (checkPass == old) {
                        if (new == confirm) {
                            data.collection(userTable).document(user.toString()).update(mapOf(
                                "password" to new
                            )).addOnSuccessListener {
                                callback.onCallback("success")

                            }.addOnFailureListener {
                                callback.onCallback("Ha sucedido un error")
                            }
                        } else {
                            callback.onCallback("La contraseñas nuevas no coinciden.")
                        }
                    } else {
                        callback.onCallback("La contraseña antigua no coinicide.")
                    }
                }


        }
    }

    fun autologin(context: Context) {
        val userPreferences = context.getSharedPreferences(sharedTable, Context.MODE_PRIVATE)
        val user = userPreferences.getString("email", "")
        if (user != "") {
            val date = craftData()

            data.collection(progressTable).document(user.toString()).get().addOnSuccessListener {
                val actual : String? = it.get(date).toString()
                if (actual == null || actual == "null") {
                    data.collection(progressTable).document(user.toString()).update(
                        mapOf(
                            date to 0
                        )
                    )
                }
            }
            val intent = Intent (context, MainActivity::class.java)
            context.startActivity(intent)
        }

    }

    private fun craftData() : String {
        return SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().time)

    }

    private fun craftData(day: Int) : String {
        var month : String = SimpleDateFormat("MM-yyyy").format(Calendar.getInstance().time)
        return ("$day-$month")
    }

    private fun craftData(day: Int, month: Int) : String {
        var year : String = SimpleDateFormat("yyyy").format(Calendar.getInstance().time)
        when (month > 9) {
            true -> return ("$day-$month-$year")
            false -> return ("$day-0$month-$year")
        }

}

    private fun craftData(day: Int, month: Int, year: Int) : String {
        when (month > 9) {
            true -> return ("$day-$month-$year")
            false -> return ("$day-0$month-$year")
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
        val userPreferences = context.getSharedPreferences(sharedTable, Context.MODE_PRIVATE)
        val user = userPreferences.getString("email", "")
        if (user != "") {
            data.collection(userTable).document(user.toString())
                .get().addOnSuccessListener {
                    val name = it.get("name").toString()
                    val pass = it.get("password").toString()
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
     * Method to calculate de IMC of a person
     */
    private fun calculateIMC(weight: Double, height: Double) : Long {
        //kg/m^2
        //DOUBLE TO GET ALL DECIMALS
        return Math.round(weight/(height*height))
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
    fun loadProgressData(context: LinearProgressActivity){


        val userPreferences = context.getSharedPreferences(sharedTable, Context.MODE_PRIVATE)
        val user = userPreferences.getString("email", "")

        if (user != "") {
            data.collection(progressTable).document(user.toString()).get().addOnSuccessListener {
                val yValues = ArrayList<Entry>()
                val list = ArrayList<Int>()
                for (x in 1 until 32) {
                    val actual : String? = it.get(craftData(x)).toString()
                    if (actual != null && actual != "" && actual != "null") {
                        if (actual.toInt() > 0){
                            yValues.add(Entry(x.toFloat(), actual.toFloat()))
                            list.add(actual.toInt())
                        }
                    }
                }
                setLineChartData(context, yValues, list)


            }
        }
    }

    fun loadProgressData(context: LinearProgressActivity, month : Int, year: Int){
        val userPreferences = context.getSharedPreferences(sharedTable, Context.MODE_PRIVATE)
        val user = userPreferences.getString("email", "")
        val month2 = month+1
        if (user != "") {
            data.collection(progressTable).document(user.toString()).get().addOnSuccessListener {
                val yValues = ArrayList<Entry>()
                val list = ArrayList<Int>()
                for (x in 1 until 32) {
                    val actual : String? = it.get(craftData(x, month2, year)).toString()
                    if (actual != null && actual != "" && actual != "null") {
                        if (actual.toInt() > 0){
                            yValues.add(Entry(x.toFloat(), actual.toFloat()))
                            list.add(actual.toInt())
                        }
                    }
                }
                setLineChartData(context, yValues, list)

            }
        }
    }

    private fun setLineChartData(context: LinearProgressActivity , values: ArrayList<Entry>, list : ArrayList<Int>) {
        context.findViewById<TextView>(R.id.progress_media).text = ("Media: " + calculateAverage(list).toString())
        val chart = context.findViewById<LineChart>(R.id.progress_content)
        chart.isEnabled = false
        chart.isDragEnabled = true
        chart.setScaleEnabled(true)
        val desc = Description()
        desc.text = ""
        chart.description = desc

        chart.axisLeft.setDrawLabels(false)
        chart.axisRight.setDrawLabels(false)
        chart.xAxis.setDrawLabels(true)
        chart.setTouchEnabled(false)

        val set1 = LineDataSet(values, "Número de Ejercicios")
        set1.lineWidth = 6f
        set1.circleRadius = 6f
        set1.valueTextSize = 15f
        set1.setCircleColor(Color.LTGRAY)
        set1.setDrawCircleHole(false)
        val dataSets = ArrayList<ILineDataSet>()
        dataSets.add(set1)
        val dat = LineData(dataSets)
        chart.data = dat
        chart.isVisible = true
        chart.invalidate()

    }

    private fun setBarChartData(context: BarProgressActivity , values: ArrayList<BarEntry>, list : ArrayList<Int>) {
        context.findViewById<TextView>(R.id.bar_progress_media).text = ("Media: " + calculateAverage(list).toString())
        val chart = context.findViewById<BarChart>(R.id.bar_progress_content)
        context.findViewById<TextView>(R.id.bar_progress_media).text = ("Media: " + calculateAverage(list).toString())
        val set1 = BarDataSet(values, "Número de ejercicios")
        set1.setDrawIcons(false)
        set1.valueTextSize = 15f
        val dataSets: java.util.ArrayList<IBarDataSet> = java.util.ArrayList()
        dataSets.add(set1)

        val data = BarData(dataSets)
        data.barWidth = 1.5f
        val desc = Description()
        chart.isEnabled = false
        chart.isDragEnabled = true
        desc.text = ""
        chart.axisLeft.setDrawLabels(false)
        chart.axisRight.setDrawLabels(false)
        chart.xAxis.setDrawLabels(true)
        chart.setTouchEnabled(false)
        chart.description = desc
        chart.data = data
        chart.isVisible = true
        chart.invalidate()

    }

    fun loadProgressData(context: BarProgressActivity){
        val userPreferences = context.getSharedPreferences(sharedTable, Context.MODE_PRIVATE)
        val user = userPreferences.getString("email", "")
        if (user != "") {
            data.collection(progressTable).document(user.toString()).get().addOnSuccessListener {
                val yValues = ArrayList<BarEntry>()
                val list = ArrayList<Int>()
                for (x in 1 until 32) {
                    val actual : String? = it.get(craftData(x)).toString()
                    if (actual != null && actual != "" && actual != "null") {
                        if (actual.toInt() > 0){
                            yValues.add(BarEntry(x.toFloat(), actual.toFloat()))
                            list.add(actual.toInt())
                        }
                    }
                }
                setBarChartData(context, yValues, list)

            }
        }
    }

    fun loadProgressData(context: BarProgressActivity, month : Int, year: Int){
        val userPreferences = context.getSharedPreferences(sharedTable, Context.MODE_PRIVATE)
        val user = userPreferences.getString("email", "")
        val month2 = month+1
        if (user != "") {
            data.collection(progressTable).document(user.toString()).get().addOnSuccessListener {
                val yValues = ArrayList<BarEntry>()
                val list = ArrayList<Int>()
                for (x in 1 until 32) {
                    val actual : String? = it.get(craftData(x, month2, year)).toString()
                    if (actual != null && actual != "" && actual != "null") {
                        if (actual.toInt() > 0){
                            yValues.add(BarEntry(x.toFloat(), actual.toFloat()))
                            list.add(actual.toInt())
                        }
                    }
                }
                setBarChartData(context, yValues, list)

            }
        }
    }

    private fun calculateAverage(list: List<Int>) : Int {
        if (list.isEmpty()) {
            return 0
        }
        var toReturn = 0
        for (x in list) {
            toReturn += x
        }
        return toReturn/list.size
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
            val date = craftData()

            data.collection(progressTable).document(user.toString()).get().addOnSuccessListener {
                val actual : String? = it.get(craftData()).toString()

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


}