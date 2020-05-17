package dev.virtualplanet.rehabapp.view

import android.app.ActionBar
import android.app.Notification.DEFAULT_ALL
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.IBinder
import android.print.PrintAttributes
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.NotificationCompat
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
    lateinit var checkBox: CheckBox
    lateinit var checkBox2: CheckBox
    lateinit var checkBox3: CheckBox
    lateinit var checkBox4: CheckBox
    lateinit var checkBox5: CheckBox
    lateinit var button : Button
    lateinit var button2 : Button
    lateinit var button3 : Button
    lateinit var button4 : Button
    lateinit var button5 : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_medicinas)
        val medicinas = resources.getStringArray(R.array.Medicinas)
        val datas = resources.getStringArray(R.array.Datas)
        val spinner = findViewById<Spinner>(R.id.med_spinner)
        val l : LinearLayout = findViewById(R.id.med_layout)
        val lista = Controller.loadSavedMedicines(this)
        var i = 0
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

        checkBox = CheckBox(this)
        checkBox.setChecked(false)
        checkBox.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked){
                Controller.putBoleano(this@MedicinasActivity, true, "ischecked")
                iniciarServicio()
            }
            else checkBox.setChecked(false)
        }
        checkBox2 = CheckBox(this)
        checkBox2.setChecked(false)
        checkBox2.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked) Controller.putBoleano(this@MedicinasActivity, true, "ischecked2")
            else Controller.putBoleano(this@MedicinasActivity, false, "ischecked2")
        }
        checkBox3 = CheckBox(this)
        checkBox3.setChecked(false)
        checkBox3.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked) Controller.putBoleano(this@MedicinasActivity, true, "ischecked3")
            else Controller.putBoleano(this@MedicinasActivity, false, "ischecked3")
        }
        checkBox4 = CheckBox(this)
        checkBox4.setChecked(false)
        checkBox4.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked) Controller.putBoleano(this@MedicinasActivity, true, "ischecked4")
            else Controller.putBoleano(this@MedicinasActivity, false, "ischecked4")
        }
        checkBox5 = CheckBox(this)
        checkBox5.setChecked(false)
        checkBox5.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked) Controller.putBoleano(this@MedicinasActivity, true, "ischecked5")
            else Controller.putBoleano(this@MedicinasActivity, false, "ischecked5")
        }
        button = Button(this)
        button.setText("X")
        button.setBackgroundColor(Color.TRANSPARENT)
        button2 = Button(this)
        button2.setText("X")
        button2.setBackgroundColor(Color.TRANSPARENT)
        button3 = Button(this)
        button3.setText("X")
        button3.setBackgroundColor(Color.TRANSPARENT)
        button4 = Button(this)
        button4.setText("X")
        button4.setBackgroundColor(Color.TRANSPARENT)
        button5 = Button(this)
        button5.setText("X")
        button5.setBackgroundColor(Color.TRANSPARENT)

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
                    if(Controller.loadSavedDay(this, "e")!="") e.setText(Controller.loadSavedDay(this, "e"))
                    else e.setText("1")
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
                    if (Controller.getBoleano(this, "ischecked") == true){
                        checkBox.setChecked(true)
                    }
                    else checkBox.setChecked(false)
                    l2.addView(checkBox)
                    button.setOnClickListener {
                        Controller.removeSavedMedicine(this, "Analgesico")
                        Controller.removeBoleano(this, "ischecked")
                        l2.removeAllViews()
                        l.removeView(l2)
                    }

                    l2.addView(button)
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
                    if(Controller.loadSavedDay(this, "e3")!="") e3.setText(Controller.loadSavedDay(this, "e3"))
                    else e3.setText("1")
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
                    if (Controller.getBoleano(this, "ischecked2") == true){
                        checkBox2.setChecked(true)
                    }
                    else checkBox2.setChecked(false)
                    l2.addView(checkBox2)
                    button2.setOnClickListener {
                        Controller.removeSavedMedicine(this, "Relajante")
                        l2.removeAllViews()
                        l.removeView(l2)
                    }
                    l2.addView(button2)
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
                    if(Controller.loadSavedDay(this, "e2")!="") e2.setText(Controller.loadSavedDay(this, "e2"))
                    else e2.setText("1")
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
                    if (Controller.getBoleano(this, "ischecked3") == true){
                        checkBox3.setChecked(true)
                    }
                    else checkBox3.setChecked(false)
                    l2.addView(checkBox3)
                    button3.setOnClickListener {
                        Controller.removeSavedMedicine(this, "Antiinflamatorio")
                        l2.removeAllViews()
                        l.removeView(l2)
                    }
                    l2.addView(button3)
                    l.addView(l2)
                }
                else if (seleccion == "Afavorecidor"){
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
                    if(Controller.loadSavedDay(this, "e4")!="") e4.setText(Controller.loadSavedDay(this, "e4"))
                    else e4.setText("1")
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
                    if (Controller.getBoleano(this, "ischecked4") == true){
                        checkBox4.setChecked(true)
                    }
                    else checkBox4.setChecked(false)
                    l2.addView(checkBox4)
                    button4.setOnClickListener {
                        Controller.removeSavedMedicine(this, "Afavorecidor")
                        l2.removeAllViews()
                        l.removeView(l2)
                    }
                    l2.addView(button4)
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
                    if(Controller.loadSavedDay(this, "e5")!="") e5.setText(Controller.loadSavedDay(this, "e5"))
                    else e5.setText("1")
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
                    if (Controller.getBoleano(this, "ischecked5") == true){
                        checkBox5.setChecked(true)
                    }
                    else checkBox5.setChecked(false)
                    l2.addView(checkBox5)
                    button5.setOnClickListener {
                        Controller.removeSavedMedicine(this, "Antitrombotico")
                        l2.removeAllViews()
                        l.removeView(l2)
                    }
                    l2.addView(button5)
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
                    spinner2.setPadding(0,0,50,0)
                    l2.addView(spinner2)

                    l2.addView(checkBox)
                    button.setOnClickListener {
                        Controller.removeSavedMedicine(this@MedicinasActivity, "Analgesico")
                        Controller.removeBoleano(this@MedicinasActivity, "ischecked")
                        l2.removeAllViews()
                        l.removeView(l2)
                    }
                    l2.addView(button)
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
                    l2.addView(checkBox3)
                    button3.setOnClickListener {
                        Controller.removeSavedMedicine(this@MedicinasActivity, "Antiinflamatorio")
                        Controller.removeBoleano(this@MedicinasActivity, "ischecked3")
                        l2.removeAllViews()
                        l.removeView(l2)
                    }
                    l2.addView(button3)
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
                    l2.addView(checkBox2)
                    button2.setOnClickListener {
                        Controller.removeSavedMedicine(this@MedicinasActivity, "Relajante")
                        Controller.removeBoleano(this@MedicinasActivity, "ischecked2")
                        l2.removeAllViews()
                        l.removeView(l2)
                    }
                    l2.addView(button2)
                    l.addView(l2)
                }
                else if (seleccion == "Afavorecidor"){
                    Controller.addSavedMedicines(this@MedicinasActivity, "Afavorecidor")
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
                    l2.addView(checkBox4)
                    button4.setOnClickListener {
                        Controller.removeSavedMedicine(this@MedicinasActivity, "Afavorecidor")
                        Controller.removeBoleano(this@MedicinasActivity, "ischecked4")
                        l2.removeAllViews()
                        l.removeView(l2)
                    }
                    l2.addView(button4)
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
                    l2.addView(checkBox5)
                    button5.setOnClickListener {
                        Controller.removeSavedMedicine(this@MedicinasActivity, "Antitrombotico")
                        Controller.removeBoleano(this@MedicinasActivity, "ischecked5")
                        l2.removeAllViews()
                        l.removeView(l2)
                    }
                    l2.addView(button5)
                    l.addView(l2)
                }
            }

        }

    }

    fun iniciarServicio(){
        var segundos : Long = 5
        var i : Intent = Intent(this, ServicioNotis::class.java)
        i.putExtra("segundos", segundos)
        startService(intent)
    }

    inner class ServicioNotis : Service() {

        override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
            val i : Intent? = intent
            var segundos : Long = i!!.getLongExtra("segundos", 0)
            var countdown = object : CountDownTimer(segundos * 1000, 100){
                override fun onFinish() {
                    lanzarNotificaciones()
                    applicationContext.stopService(i)
                }

                override fun onTick(p0: Long) {

                }

            }
            countdown.start()
            return super.onStartCommand(intent, flags, startId)
        }
        override fun onBind(p0: Intent?): IBinder? {
            return null
        }
        fun lanzarNotificaciones(){
            var id : String = "Notis"
            var nombre : String = "Temporizador"
            var importancia : Int = NotificationManager.IMPORTANCE_HIGH
            var notificationChannel = NotificationChannel(id, nombre, importancia)
            notificationChannel.enableVibration(true)
            notificationChannel.lightColor=Color.RED
            notificationChannel.enableLights(true)
            var notificationManager  = applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(notificationChannel)
            var builder : NotificationCompat.Builder = NotificationCompat.Builder(applicationContext, id).setDefaults(DEFAULT_ALL)
                .setChannelId(id).setSmallIcon(R.drawable.ic_arrow_drop_down_circle_black_24dp).setContentText("Recuerda tomar la medicación!")
            notificationManager.notify(1, builder.build())
        }

    }

    fun guardar(view: View) {
        iniciarServicio()
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

    fun goBack(view: View) {
        val intent = Intent(view.context, SelectStaticActivity::class.java)
        startActivity(intent)
    }

}
