package dev.virtualplanet.rehabapp.controller

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.provider.ContactsContract
import android.view.View
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import dev.virtualplanet.rehabapp.R
import dev.virtualplanet.rehabapp.model.Exercice
import dev.virtualplanet.rehabapp.model.ExerciceList
import dev.virtualplanet.rehabapp.view.*
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_profile.view.*
import java.time.LocalDateTime
import java.util.*
import kotlin.collections.ArrayList


object Controller {

    private val data = FirebaseFirestore.getInstance()
    private val userTable = "USERS"
    private val progressTable = "PROGRESS"
    private val sharedTable = "userInfo"

    val ex_list = ExerciceList()

    fun getExerciceByName(name: String) : Exercice? {
        return ex_list.getExerciceByName(name)
    }

    fun getExerciceByID(id: String) : Exercice? {
        return null
    }

    fun validateLogin(user: String, pass: String, view: View) {
        if (!user.isBlank() && !pass.isBlank()) {
            data.collection(userTable).document(user)
                .get().addOnSuccessListener {
                    val check_pass = it.get("password").toString()
                    if (check_pass == pass) {
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
                            "sex" to "Not Set",
                            "age" to "Not Set",
                            "weight" to "Not Set",
                            "height" to "Not Set",
                            "wheel" to false
                        ))
                        var dataFormat = craftData()
                        data.collection(progressTable).document(mail).set(mapOf(
                            dataFormat to 15
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
                        val check_pass = it.get("password").toString()
                        if (check_pass == old) {
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
        return "" + LocalDateTime.now().dayOfMonth + "/" +
                LocalDateTime.now().month + "/" + LocalDateTime.now().year

    }

    private fun craftData(day: Int) : String {
        return "" + day + "/" +
                LocalDateTime.now().month + "/" + LocalDateTime.now().year

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

    private fun calculateIMC(weight: Double, height: Double) : Double {
        //kg/m^2
        //DOUBLE TO GET ALL DECIMALS
        return (weight/(height*height))
    }

    fun saveProfileData(context: Context, age: String, weight: String, height: String, wheelChair : Boolean) {

        val userPreferneces = context.getSharedPreferences(sharedTable, Context.MODE_PRIVATE)
        val user = userPreferneces.getString("email", "")

        if (user != "") {
            data.collection(userTable).document(user.toString()).get().addOnSuccessListener {
                if (!age.equals("Not Set")) {
                    data.collection(userTable).document(user.toString()).update(mapOf(
                        "age" to age
                    ))
                }
                if (!weight.equals("Not Set")) {
                    data.collection(userTable).document(user.toString()).update(mapOf(
                        "weight" to weight
                    ))
                }
                if (!height.equals("Not Set")) {
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
                    if (!age.equals("Not Set")) {
                        context.findViewById<EditText>(R.id.e_textView_Age_Value).hint = age
                    }

                    if (!height.equals("Not Set")) {
                        context.findViewById<TextView>(R.id.e_textView_Height_Value).hint = height
                    }
                    if (!weight.equals("Not Set")) {
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

    fun loadProgressData(context: ProgressActivity){
        val progress_chart = context.findViewById<LineChart>(R.id.progress_content)

        progress_chart.isDragEnabled = true
        progress_chart.setScaleEnabled(true)
        val desc = Description()
        desc.text = ""
        progress_chart.description = desc

        progress_chart.axisLeft.setDrawLabels(false)
        progress_chart.axisRight.setDrawLabels(false)
        progress_chart.xAxis.setDrawLabels(true)
        progress_chart.setTouchEnabled(false)




        val userPreferences = context.getSharedPreferences(sharedTable, Context.MODE_PRIVATE)
        val user = userPreferences.getString("email", "")
        if (user != "") {
            data.collection(progressTable).document(user!!).get().addOnSuccessListener {
                val y_values = ArrayList<Entry>()
                for (x in 0 until 32) {
                    val actual : String? = it.get(craftData(x)).toString()
                    if (actual != null) {
                        y_values.add(Entry(x.toFloat(), actual.toFloat()))
                    }

                }
                val set1 = LineDataSet(y_values, "Number of exercices")
                set1.lineWidth = 6f
                set1.circleRadius = 6f
                set1.valueTextSize = 15f
                set1.setCircleColor(Color.LTGRAY)
                set1.setDrawCircleHole(false)

                val dataSets = ArrayList<ILineDataSet>()
                dataSets.add(set1)

                val data = LineData(dataSets)

                progress_chart.data = data
            }
        } else {
            val y_values = ArrayList<Entry>()
            y_values.add(Entry(0f, 6f))
            y_values.add(Entry(1f, 3f))
            y_values.add(Entry(2f, 7f))
            y_values.add(Entry(3f, 5f))
            y_values.add(Entry(4f, 5f))
            y_values.add(Entry(5f, 4f))
            y_values.add(Entry(6f, 5f))

            val set1 = LineDataSet(y_values, "Number of exercices")
            set1.lineWidth = 6f
            set1.circleRadius = 6f
            set1.valueTextSize = 15f
            set1.setCircleColor(Color.LTGRAY)
            set1.setDrawCircleHole(false)

            val dataSets = ArrayList<ILineDataSet>()
            dataSets.add(set1)

            val data = LineData(dataSets)

            progress_chart.data = data
        }



    }


}