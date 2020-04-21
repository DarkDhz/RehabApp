package dev.virtualplanet.rehabapp.controller

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.view.View
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.google.firebase.firestore.FirebaseFirestore
import dev.virtualplanet.rehabapp.R
import dev.virtualplanet.rehabapp.model.Exercice
import dev.virtualplanet.rehabapp.model.ModelFactory
import dev.virtualplanet.rehabapp.view.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


object Controller {

    private val data = FirebaseFirestore.getInstance()
    private const val userTable = "USERS"
    private const val progressTable = "PROGRESS"
    private const val sharedTable = "userInfo"
    private const val sharedExercices = "selected"

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

    fun validateLogin(user: String, pass: String, view: View) {
        //FireAuth
        if (!user.isBlank() && !pass.isBlank()) {
            data.collection(userTable).document(user)
                .get().addOnSuccessListener {
                    val checkPass = it.get("password").toString()
                    if (checkPass == pass) {
                        val userPreferences = view.context.getSharedPreferences(sharedTable, Context.MODE_PRIVATE)
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
                data.collection(userTable).document(mail)
                    .get().addOnSuccessListener {
                        val message = Toast.makeText(view.context, "Esta cuenta ya existe", Toast.LENGTH_LONG)
                        message.show()
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

                        val userPreferences = view.context.getSharedPreferences(sharedTable, Context.MODE_PRIVATE)
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
            val userPreferences = view.context.getSharedPreferences(sharedTable, Context.MODE_PRIVATE)
            val user = userPreferences.getString("email", "")
            if (user != "") {
                data.collection(userTable).document(user.toString())
                    .get().addOnSuccessListener {
                        val checkPass = it.get("password").toString()
                        if (checkPass == old) {
                            if (new == confirm) {
                                data.collection(userTable).document(user.toString()).update(mapOf(
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
        val userPreferences = context.getSharedPreferences(sharedTable, Context.MODE_PRIVATE)
        val user = userPreferences.getString("email", "")
        if (user != "") {
            val date = craftData()
            data.collection(progressTable).document(date).get().addOnFailureListener {
                data.collection(progressTable).document(date).set(mapOf(
                    date to 0
                ))
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
        return day.toString() + "-" + month
    }

    private fun craftData(day: Int, month: Int) : String {
        var year : String = SimpleDateFormat("yyyy").format(Calendar.getInstance().time)
        return day.toString() + "-" + month + "-" + year
    }

    private fun craftData(day: Int, month: Int, year: Int) : String {
        if (month > 9) {
            return day.toString() + "-" + month.toString() + "-" + year.toString()
        } else {
            return day.toString() + "-0" + month.toString() + "-" + year.toString()
        }

    }

    fun removeUser(context: Context) {
        val userPreferences = context.getSharedPreferences(sharedTable, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = userPreferences.edit()

        editor.remove("email")
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
            context.findViewById<TextView>(R.id.textView_WheelChair_Value).text = "SI"
        } else {
            context.findViewById<TextView>(R.id.textView_WheelChair_Value).text = "NO"
        }

        if (height.equals(notSetString) || weight.equals(notSetString)) {
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
    private fun calculateIMC(weight: Double, height: Double) : Double {
        //kg/m^2
        //DOUBLE TO GET ALL DECIMALS
        return (weight/(height*height))
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
                    } else {
                        context.findViewById<Switch>(R.id.e_textView_WheelChair_Value).text = "NO"
                    }

                }
        }
    }

    /**
     * Method to load All the chart data
     */
    fun loadProgressData(context: ProgressActivity){


        val userPreferences = context.getSharedPreferences(sharedTable, Context.MODE_PRIVATE)
        val user = userPreferences.getString("email", "")

        if (user != "") {
            data.collection(progressTable).document(user.toString()).get().addOnSuccessListener {
                val progressChart = context.findViewById<LineChart>(R.id.progress_content)
                progressChart.isEnabled = false
                progressChart.isDragEnabled = true
                progressChart.setScaleEnabled(true)
                val desc = Description()
                desc.text = ""
                progressChart.description = desc

                progressChart.axisLeft.setDrawLabels(false)
                progressChart.axisRight.setDrawLabels(false)
                progressChart.xAxis.setDrawLabels(true)
                progressChart.setTouchEnabled(false)
                val yValues = ArrayList<Entry>()
                for (x in 1 until 32) {
                    val actual : String? = it.get(craftData(x)).toString()
                    if (actual != null && actual != "" && actual != "null") {
                        yValues.add(Entry(x.toFloat(), actual.toFloat()))
                    }
                }
                val set1 = LineDataSet(yValues, "Número de Ejercicios")
                set1.lineWidth = 6f
                set1.circleRadius = 6f
                set1.valueTextSize = 15f
                set1.setCircleColor(Color.LTGRAY)
                set1.setDrawCircleHole(false)
                val dataSets = ArrayList<ILineDataSet>()
                dataSets.add(set1)

                val dat = LineData(dataSets)

                progressChart.data = dat
                progressChart.isVisible = true

            }
        }
    }

    fun loadProgressData(context: ProgressActivity, month : Int, year: Int){
        val userPreferences = context.getSharedPreferences(sharedTable, Context.MODE_PRIVATE)
        val user = userPreferences.getString("email", "")
        var month2 = month+1
        if (user != "") {
            data.collection(progressTable).document(user.toString()).get().addOnSuccessListener {
                val progressChart = context.findViewById<LineChart>(R.id.progress_content)
                progressChart.isEnabled = false
                progressChart.isDragEnabled = true
                progressChart.setScaleEnabled(true)
                val desc = Description()
                desc.text = ""
                progressChart.description = desc

                progressChart.axisLeft.setDrawLabels(false)
                progressChart.axisRight.setDrawLabels(false)
                progressChart.xAxis.setDrawLabels(true)
                progressChart.setTouchEnabled(false)

                val yValues = ArrayList<Entry>()
                for (x in 1 until 32) {
                    val actual : String? = it.get(craftData(x, month2, year)).toString()
                    if (actual != null && actual != "" && actual != "null") {
                        yValues.add(Entry(x.toFloat(), actual.toFloat()))
                    }

                }
                val set1 = LineDataSet(yValues, "Número de Ejercicios")
                set1.lineWidth = 6f
                set1.circleRadius = 6f
                set1.valueTextSize = 15f
                set1.setCircleColor(Color.LTGRAY)
                set1.setDrawCircleHole(false)
                val dataSets = ArrayList<ILineDataSet>()
                dataSets.add(set1)

                val dat = LineData(dataSets)

                progressChart.data = dat
                progressChart.isVisible = true
                progressChart.invalidate()
            }
        }
    }


}