package dev.virtualplanet.rehabapp.controller

import android.content.Context
import android.content.Intent
import com.google.firebase.firestore.FirebaseFirestore
import dev.virtualplanet.rehabapp.controller.data.*
import dev.virtualplanet.rehabapp.controller.shared.ExerciceSharedManager
import dev.virtualplanet.rehabapp.controller.shared.MedicineSharedManager
import dev.virtualplanet.rehabapp.controller.shared.UserSharedManager
import dev.virtualplanet.rehabapp.controller.utils.ProfileHelper
import dev.virtualplanet.rehabapp.model.ExerciceList
import dev.virtualplanet.rehabapp.model.MedicineList
import dev.virtualplanet.rehabapp.model.ModelFactory
import dev.virtualplanet.rehabapp.view.*
import kotlin.collections.ArrayList


object Controller {

    val data = FirebaseFirestore.getInstance()

    const val progressExerciceTable = "PROGRESS_EXERCICE"
    const val progressMovilityTable = "PROGRESS_MOV"

    const val notSetString = "Not Set"
    
    private val factory = ModelFactory

    fun validateLogin(user: String, pass: String, callback: Callback<String>) {
        UserDataManager.validateLogin(user, pass, object : Callback<String> {
            override fun onCallback(value: String) {
                callback.onCallback(value)
            }
        })
    }


    fun getSharedUser(context: Context) : String? {
        return UserSharedManager.getUser(context)
    }

    fun saveSharedUser(context: Context, user: String) {
        UserSharedManager.saveUser(context, user)
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
        val user = getSharedUser(context)
        if (user != "") {
            ProgressDataManager.generateData(user.toString(), progressExerciceTable)
            ProgressDataManager.generateData(user.toString(), progressMovilityTable)
            val intent = Intent (context, MainActivity::class.java)
            context.startActivity(intent)
        }

    }



    fun logOut(context: Context) {
        UserSharedManager.clear(context)
        ExerciceSharedManager.clear(context)
        MedicineSharedManager.clear(context)

        val intent = Intent(context, LoginActivity::class.java)
        context.startActivity(intent)

    }

    fun changeProfile(context: ProfileActivity, name: String, age: String, sex: String, height: String,
                      weight: String, wheelchair: Boolean) {
        ProfileHelper.changeProfile(context, name, age, sex, height, weight, wheelchair)
    }

    fun loadProfile(context: ProfileActivity){
        val user = getSharedUser(context)
        if (user != "") {
            UserDataManager.loadProfile(context, user.toString())
        }
    }





    /**
     * Method to save File Data
     */
    fun saveProfileData(context: Context, age: String, weight: String, height: String, wheelChair : Boolean) {
        val user = getSharedUser(context)
        if (user != "") {
            UserDataManager.saveProfileData(context, age, weight, height, wheelChair, user.toString())
        }

    }

    /**
     * Method to load the hints content from EditProfileActivity
     */
    fun loadProfileHints(context: EditProfileActivity) {
        val user = getSharedUser(context)
        if (user != "") {
            UserDataManager.loadProfileHints(context, user.toString())
        }
    }

    /**
     * Method to load All the chart data
     */
    fun loadExericeProgressData(context: LinearProgressActivity){
        val user = getSharedUser(context)
        if (user != "") {
            ProgressDataManager.load(context, user.toString(), progressExerciceTable)
        }
    }

    fun loadExericeProgressData(context: LinearProgressActivity, month : Int, year: Int){
        val user = getSharedUser(context)
        if (user != "") {
            ProgressDataManager.load(context, month, year, user.toString(), progressExerciceTable)
        }
    }

    fun loadExericeProgressData(context: BarProgressActivity){
        val user = getSharedUser(context)
        if (user != "") {
            ProgressDataManager.load(context, user.toString(), progressExerciceTable)
        }
    }

    fun loadExericeProgressData(context: BarProgressActivity, month : Int, year: Int){
        val user = getSharedUser(context)
        if (user != "") {
            ProgressDataManager.load(context, month, year, user.toString(), progressExerciceTable)
        }
    }


    fun loadMovData(context: LinearProgressActivity) {
        val user = getSharedUser(context)
        if (user != "") {
            ProgressDataManager.load(context, user.toString(), progressMovilityTable)
        }
    }

    fun loadMovData(context: BarProgressActivity) {
        val user = getSharedUser(context)
        if (user != "") {
            ProgressDataManager.load(context, user.toString(), progressMovilityTable)
        }
    }

    fun loadMovData(context: LinearProgressActivity, month: Int, year: Int) {
        val user = getSharedUser(context)
        if (user != "") {
            ProgressDataManager.load(context, month, year, user.toString(), progressMovilityTable)
        }
    }

    fun loadMovData(context: BarProgressActivity, month: Int, year: Int) {
        val user = getSharedUser(context)
        if (user != "") {
            ProgressDataManager.load(context, month, year, user.toString(), progressMovilityTable)
        }
    }



    fun addExerciceCount(user: String, num: Int) {
        ProgressDataManager.add(user, num, progressExerciceTable)
    }

    fun addMov(user: String, num: Int) {
        ProgressDataManager.add(user, num, progressMovilityTable)
    }

    fun addSavedExercices(context: Context, name : String) : Boolean {
        return ExerciceSharedManager.addSavedExercices(context,name)

    }

    fun addSavedDay(context: Context, name : String, key : String) : Boolean {
        return MedicineSharedManager.addSavedDay(context,name, key)
    }

    fun loadSavedDay(context: Context, key : String) : String {
        return MedicineSharedManager.loadSavedDay(context, key)
    }

    fun addSavedMedicineInt(context: Context, i : Int, key : String) : Boolean {
        return MedicineSharedManager.addSavedMedicineInt(context, i, key)
    }

    fun loadMedicineInt(context: Context, key : String) : Int {
        return MedicineSharedManager.loadMedicineInt(context, key)
    }

    fun contains(context: Context, name : String) : Boolean {
        return MedicineSharedManager.contains(context, name)
    }

    fun loadSavedMedicines(context: Context) : ArrayList<String> {
        return MedicineSharedManager.loadSavedMedicines(context)
    }

    fun addSavedMedicines(context: Context, name : String) : Boolean {
        return MedicineSharedManager.addSavedMedicines(context,name)

    }
    fun loadSavedExercices(context: Context) : ArrayList<String> {
        return ExerciceSharedManager.loadSavedExercices(context)
    }

    fun removeSavedExercice(context: Context, name: String) : Boolean {
        return ExerciceSharedManager.removeSavedExercice(context, name)
    }

    fun putBoleano(context: Context, checked : Boolean, key : String){
        return MedicineSharedManager.putBoleano(context, key, checked)
    }

    fun getBoleano(context: Context, key : String): Boolean {
        return MedicineSharedManager.getBoleano(context, key)
    }

    fun removeBoleano(context: Context, name: String) {
        return MedicineSharedManager.removeBoleano(context, name)
    }

    fun getList(name: String, packageName: String) : ExerciceList {
        val exerciceList = factory.makeExerciceList()
        val path = "android.resource://$packageName/"
        exerciceList.loadList(exerciceList, name, path)
        return exerciceList
    }

    fun removeSavedMedicine(context: Context, name: String) : Boolean {
        return MedicineSharedManager.removeSavedMedicine(context, name)
    }

    fun getMedicineList() : MedicineList {
        return ModelFactory.makeMedicineList()
    }

}