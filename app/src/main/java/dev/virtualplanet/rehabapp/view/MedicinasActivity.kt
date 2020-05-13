package dev.virtualplanet.rehabapp.view

import android.app.ActionBar
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.print.PrintAttributes
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.marginLeft
import dev.virtualplanet.rehabapp.R
import dev.virtualplanet.rehabapp.controller.Controller

class MedicinasActivity : AppCompatActivity() {
    lateinit var e : EditText
    lateinit var e2 : EditText
    lateinit var e3 : EditText
    lateinit var e4 : EditText
    lateinit var e5 : EditText
    lateinit var spinner2: Spinner
    lateinit var spinner3: Spinner
    lateinit var spinner4: Spinner
    lateinit var spinner5: Spinner
    lateinit var spinner6: Spinner
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_medicinas)
        val medicinas = resources.getStringArray(R.array.Medicinas)
        val datas = resources.getStringArray(R.array.Datas)
        val spinner = findViewById<Spinner>(R.id.spinner)
        val l : LinearLayout = findViewById(R.id.layout)
        val lista = Controller.loadSavedMedicines(this)
        var i : Int = 0
        e = EditText(this)
        e2 = EditText(this)
        e3 = EditText(this)
        e4 = EditText(this)
        e5 = EditText(this)
        e.setText("")
        e2.setText("")
        e3.setText("")
        e4.setText("")
        e5.setText("")
        spinner2 = Spinner(this@MedicinasActivity)
        if (spinner2 != null) {
            val adapter2 = ArrayAdapter(
                this@MedicinasActivity,
                android.R.layout.simple_spinner_item,
                datas
            )
            spinner2.adapter = adapter2
        }
        spinner3 = Spinner(this@MedicinasActivity)
        if (spinner3 != null) {
            val adapter2 = ArrayAdapter(
                this@MedicinasActivity,
                android.R.layout.simple_spinner_item,
                datas
            )
            spinner3.adapter = adapter2
        }
        spinner4 = Spinner(this@MedicinasActivity)
        if (spinner4 != null) {
            val adapter2 = ArrayAdapter(
                this@MedicinasActivity,
                android.R.layout.simple_spinner_item,
                datas
            )
            spinner4.adapter = adapter2
        }
        spinner5 = Spinner(this@MedicinasActivity)
        if (spinner5 != null) {
            val adapter2 = ArrayAdapter(
                this@MedicinasActivity,
                android.R.layout.simple_spinner_item,
                datas
            )
            spinner5.adapter = adapter2
        }
        spinner6 = Spinner(this@MedicinasActivity)
        if (spinner6 != null) {
            val adapter2 = ArrayAdapter(
                this@MedicinasActivity,
                android.R.layout.simple_spinner_item,
                datas
            )
            spinner6.adapter = adapter2
        }
        if (lista != null){
            for (seleccion : String in lista){
                if (seleccion == "Analgesico") {
                    val l2: LinearLayout = LinearLayout(this@MedicinasActivity)
                    val params: LinearLayout.LayoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    params.setMargins(0, 20, 0, 20)
                    l2.layoutParams = params
                    var t: TextView = TextView(this@MedicinasActivity)
                    t.setText(seleccion)
                    t.setPadding(0, 0, 50, 0)
                    l2.addView(t)
                    e = EditText(this@MedicinasActivity)
                    e.setText(Controller.loadSavedDay(this, "e"))
                    i++
                    e.gravity = Gravity.CENTER
                    e.setPadding(0, 0, 50, 0)
                    e.setBackgroundColor(Color.TRANSPARENT)
                    l2.addView(e)
                    var t2: TextView = TextView(this@MedicinasActivity)
                    t2.setText("/")
                    l2.addView(t2)
                    spinner2.gravity = Gravity.CENTER
                    spinner2.setSelection(Controller.loadMedicineInt(this, "s1"))
                    l2.addView(spinner2)
                    l.addView(l2)
                }
                else if (seleccion == "Relajante"){
                    val l2: LinearLayout = LinearLayout(this@MedicinasActivity)
                    val params: LinearLayout.LayoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    params.setMargins(0, 20, 0, 20)
                    l2.layoutParams = params
                    var t: TextView = TextView(this@MedicinasActivity)
                    t.setText(seleccion)
                    t.setPadding(0, 0, 50, 0)
                    l2.addView(t)
                    e3 = EditText(this@MedicinasActivity)
                    e3.setText(Controller.loadSavedDay(this, "e3"))
                    i++
                    e3.gravity = Gravity.CENTER
                    e3.setPadding(0, 0, 50, 0)
                    e3.setBackgroundColor(Color.TRANSPARENT)
                    l2.addView(e3)
                    var t2: TextView = TextView(this@MedicinasActivity)
                    t2.setText("/")
                    l2.addView(t2)
                    spinner4.gravity = Gravity.CENTER
                    spinner4.setSelection(Controller.loadMedicineInt(this, "s3"))
                    l2.addView(spinner4)
                    l.addView(l2)
                }
                else if (seleccion == "Antiinflamatorio"){
                    val l2: LinearLayout = LinearLayout(this@MedicinasActivity)
                    val params: LinearLayout.LayoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    params.setMargins(0, 20, 0, 20)
                    l2.layoutParams = params
                    var t: TextView = TextView(this@MedicinasActivity)
                    t.setText(seleccion)
                    t.setPadding(0, 0, 50, 0)
                    l2.addView(t)
                    e2 = EditText(this@MedicinasActivity)
                    e2.setText(Controller.loadSavedDay(this, "e2"))
                    i++
                    e2.gravity = Gravity.CENTER
                    e2.setPadding(0, 0, 50, 0)
                    e2.setBackgroundColor(Color.TRANSPARENT)
                    l2.addView(e2)
                    var t2: TextView = TextView(this@MedicinasActivity)
                    t2.setText("/")
                    l2.addView(t2)
                    spinner3.gravity = Gravity.CENTER
                    spinner3.setSelection(Controller.loadMedicineInt(this, "s2"))
                    l2.addView(spinner3)
                    l.addView(l2)
                }
                else if (seleccion == "Afavorecidor de circulacion"){
                    val l2: LinearLayout = LinearLayout(this@MedicinasActivity)
                    val params: LinearLayout.LayoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    params.setMargins(0, 20, 0, 20)
                    l2.layoutParams = params
                    var t: TextView = TextView(this@MedicinasActivity)
                    t.setText(seleccion)
                    t.setPadding(0, 0, 50, 0)
                    l2.addView(t)
                    e4 = EditText(this@MedicinasActivity)
                    e4.setText(Controller.loadSavedDay(this, "e4"))
                    i++
                    e4.gravity = Gravity.CENTER
                    e4.setPadding(0, 0, 50, 0)
                    e4.setBackgroundColor(Color.TRANSPARENT)
                    l2.addView(e4)
                    var t2: TextView = TextView(this@MedicinasActivity)
                    t2.setText("/")
                    l2.addView(t2)
                    spinner5.gravity = Gravity.CENTER
                    spinner5.setSelection(Controller.loadMedicineInt(this, "s4"))
                    l2.addView(spinner5)
                    l.addView(l2)
                }
                else if (seleccion == "Antitrombotico"){
                    val l2: LinearLayout = LinearLayout(this@MedicinasActivity)
                    val params: LinearLayout.LayoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    params.setMargins(0, 20, 0, 20)
                    l2.layoutParams = params
                    var t: TextView = TextView(this@MedicinasActivity)
                    t.setText(seleccion)
                    t.setPadding(0, 0, 50, 0)
                    l2.addView(t)
                    e5 = EditText(this@MedicinasActivity)
                    e5.setText(Controller.loadSavedDay(this, "e5"))
                    i++
                    e5.gravity = Gravity.CENTER
                    e5.setPadding(0, 0, 50, 0)
                    e5.setBackgroundColor(Color.TRANSPARENT)
                    l2.addView(e5)
                    var t2: TextView = TextView(this@MedicinasActivity)
                    t2.setText("/")
                    l2.addView(t2)
                    spinner6.gravity = Gravity.CENTER
                    spinner6.setSelection(Controller.loadMedicineInt(this, "s5"))
                    l2.addView(spinner6)
                    l.addView(l2)
                }
            }
        }
        if (spinner != null) {
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, medicinas)
            spinner.adapter = adapter
        }
        spinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                var seleccion : String = spinner.selectedItem.toString()
                if (Controller.contains(this@MedicinasActivity, seleccion)){

                }
                else if (seleccion == "Analgesico"){
                    Controller.addSavedMedicines(this@MedicinasActivity, "Analgesico")
                    val l2 : LinearLayout = LinearLayout(this@MedicinasActivity)
                    val params : LinearLayout.LayoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                    params.setMargins(0,20,0,20)
                    l2.layoutParams=params
                    var t : TextView = TextView(this@MedicinasActivity)
                    t.setText(seleccion)
                    t.setPadding(0,0,50,0)
                    l2.addView(t)
                    e = EditText(this@MedicinasActivity)
                    e.setText("1")
                    e.gravity=Gravity.CENTER
                    e.setPadding(0,0,50,0)
                    e.setBackgroundColor(Color.TRANSPARENT)
                    l2.addView(e)
                    var t2 : TextView = TextView(this@MedicinasActivity)
                    t2.setText("/")
                    l2.addView(t2)
                    spinner2.gravity=Gravity.CENTER
                    l2.addView(spinner2)
                    l.addView(l2)
                }
                else if (seleccion == "Antiinflamatorio"){
                    Controller.addSavedMedicines(this@MedicinasActivity, "Antiinflamatorio")
                    val l2 : LinearLayout = LinearLayout(this@MedicinasActivity)
                    val params : LinearLayout.LayoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                    params.setMargins(0,20,0,20)
                    l2.layoutParams=params
                    var t : TextView = TextView(this@MedicinasActivity)
                    t.setText(seleccion)
                    t.setPadding(0,0,50,0)
                    l2.addView(t)
                    e2.setText("1")
                    e2.gravity=Gravity.CENTER
                    e2.setPadding(0,0,50,0)
                    e2.setBackgroundColor(Color.TRANSPARENT)
                    l2.addView(e2)
                    var t2 : TextView = TextView(this@MedicinasActivity)
                    t2.setText("/")
                    l2.addView(t2)
                    spinner3.gravity=Gravity.CENTER
                    l2.addView(spinner3)
                    l.addView(l2)
                }
                else if (seleccion == "Relajante"){
                    Controller.addSavedMedicines(this@MedicinasActivity, "Relajante")
                    val l2 : LinearLayout = LinearLayout(this@MedicinasActivity)
                    val params : LinearLayout.LayoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                    params.setMargins(0,20,0,20)
                    l2.layoutParams=params
                    var t : TextView = TextView(this@MedicinasActivity)
                    t.setText(seleccion)
                    t.setPadding(0,0,50,0)
                    l2.addView(t)
                    e3 = EditText(this@MedicinasActivity)
                    e3.setText("1")
                    e3.gravity=Gravity.CENTER
                    e3.setPadding(0,0,50,0)
                    e3.setBackgroundColor(Color.TRANSPARENT)
                    l2.addView(e3)
                    var t2 : TextView = TextView(this@MedicinasActivity)
                    t2.setText("/")
                    l2.addView(t2)
                    spinner4.gravity=Gravity.CENTER
                    l2.addView(spinner4)
                    l.addView(l2)
                }
                else if (seleccion == "Afavorecidor de circulacion"){
                    Controller.addSavedMedicines(this@MedicinasActivity, "Afavorecidor de circulacion")
                    val l2 : LinearLayout = LinearLayout(this@MedicinasActivity)
                    val params : LinearLayout.LayoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                    params.setMargins(0,20,0,20)
                    l2.layoutParams=params
                    var t : TextView = TextView(this@MedicinasActivity)
                    t.setText(seleccion)
                    t.setPadding(0,0,50,0)
                    l2.addView(t)
                    e4 = EditText(this@MedicinasActivity)
                    e4.setText("1")
                    e4.gravity=Gravity.CENTER
                    e4.setPadding(0,0,50,0)
                    e4.setBackgroundColor(Color.TRANSPARENT)
                    l2.addView(e4)
                    var t2 : TextView = TextView(this@MedicinasActivity)
                    t2.setText("/")
                    l2.addView(t2)
                    spinner5.gravity=Gravity.CENTER
                    l2.addView(spinner5)
                    l.addView(l2)
                }
                else if (seleccion == "Antitrombotico"){
                    Controller.addSavedMedicines(this@MedicinasActivity, "Antitrombotico")
                    val l2 : LinearLayout = LinearLayout(this@MedicinasActivity)
                    val params : LinearLayout.LayoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                    params.setMargins(0,20,0,20)
                    l2.layoutParams=params
                    var t : TextView = TextView(this@MedicinasActivity)
                    t.setText(seleccion)
                    t.setPadding(0,0,50,0)
                    l2.addView(t)
                    e5 = EditText(this@MedicinasActivity)
                    e5.setText("1")
                    e5.gravity=Gravity.CENTER
                    e5.setPadding(0,0,50,0)
                    e5.setBackgroundColor(Color.TRANSPARENT)
                    l2.addView(e5)
                    var t2 : TextView = TextView(this@MedicinasActivity)
                    t2.setText("/")
                    l2.addView(t2)
                    spinner6.gravity=Gravity.CENTER
                    l2.addView(spinner6)
                    l.addView(l2)
                }
            }

        }

    }

    fun guardar(view: View) {
        if (e.text.toString()!="") Controller.addSavedDay(this, e.text.toString(), "e")
        if (e2.text.toString()!="")Controller.addSavedDay(this, e2.text.toString(), "e2")
        if (e3.text.toString()!="")Controller.addSavedDay(this, e3.text.toString(), "e3")
        if (e4.text.toString()!="")Controller.addSavedDay(this, e4.text.toString(),"e4")
        if (e5.text.toString()!="")Controller.addSavedDay(this, e5.text.toString(), "e5")
        Controller.addSavedMedicineInt(this, spinner2.selectedItemPosition, "s1")
        Controller.addSavedMedicineInt(this, spinner3.selectedItemPosition, "s2")
        Controller.addSavedMedicineInt(this, spinner4.selectedItemPosition, "s3")
        Controller.addSavedMedicineInt(this, spinner5.selectedItemPosition, "s4")
        Controller.addSavedMedicineInt(this, spinner6.selectedItemPosition, "s5")
    }
}
