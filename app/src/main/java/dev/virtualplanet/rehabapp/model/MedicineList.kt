package dev.virtualplanet.rehabapp.model

import android.view.Display

class MedicineList : CustomList<Medicine> {
    var content : ArrayList<Medicine>

    constructor() {
        content = ArrayList()
        load()
    }

    constructor(list: ArrayList<Medicine>) {
        content = ArrayList()
    }

    private fun load() {
        content.add(ModelFactory.makeMedicine("Analgesico"))
        content.add(ModelFactory.makeMedicine("Relajante"))
        content.add(ModelFactory.makeMedicine("Antiinflamatorio"))
        content.add(ModelFactory.makeMedicine("Afavorecido"))
        content.add(ModelFactory.makeMedicine("Antitrombotico"))
    }

    override fun getSize() : Int {
        return content.size
    }

    override fun getByName(name: String) : Medicine? {
        for (exercice in content) {
            if (exercice.name == name) {
                return exercice
            }
        }
        return null
    }

    override fun add(item: Medicine) {
        if (validate(item)) {
            content.add(item)
        }
    }

    private fun validate(item: Medicine) : Boolean {
        return true
    }



}