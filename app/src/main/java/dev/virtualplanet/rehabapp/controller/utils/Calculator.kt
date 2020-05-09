package dev.virtualplanet.rehabapp.controller.utils

object Calculator {

    /**
     * Method to calculate de IMC of a person
     */
    fun calculateIMC(weight: Double, height: Double) : Long {
        //kg/m^2
        //DOUBLE TO GET ALL DECIMALS
        return Math.round(weight/(height*height))
    }

    fun calculateAverage(list: List<Int>) : Int {
        if (list.isEmpty()) {
            return 0
        }
        var toReturn = 0
        for (x in list) {
            toReturn += x
        }
        return toReturn/list.size
    }



}