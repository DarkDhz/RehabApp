package dev.virtualplanet.rehabapp.controller.utils

import android.widget.TextView
import dev.virtualplanet.rehabapp.R
import dev.virtualplanet.rehabapp.controller.Controller
import dev.virtualplanet.rehabapp.view.ProfileActivity

object ProfileHelper {

    fun changeProfile(context: ProfileActivity, age: String, height: String, weight: String, wheelchair: Boolean) {

        context.findViewById<TextView>(R.id.textView_Age_Value).text = age
        if (age.equals("notSetString")) {
            context.findViewById<TextView>(R.id.textView_Age_Units).text = ""
        }

        context.findViewById<TextView>(R.id.textView_Height_Value).text = height
        if (height.equals(Controller.notSetString)) {
            context.findViewById<TextView>(R.id.textView_Height_Units).text = ""
        }
        context.findViewById<TextView>(R.id.textView_Weight_Value).text = weight
        if (weight.equals(Controller.notSetString)) {
            context.findViewById<TextView>(R.id.textView_Weight_Units).text = ""
        }

        if (wheelchair) {
            context.findViewById<TextView>(R.id.textView_WheelChair_Value).text = ("SI")
        } else {
            context.findViewById<TextView>(R.id.textView_WheelChair_Value).text = ("NO")
        }

        if (height.equals(Controller.notSetString) || weight.equals(Controller.notSetString)) {
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
    
}