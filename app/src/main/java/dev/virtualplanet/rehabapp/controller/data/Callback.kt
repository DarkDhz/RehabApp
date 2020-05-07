package dev.virtualplanet.rehabapp.controller.data

interface Callback<T> {
    fun onCallback(value : T)
}