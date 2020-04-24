package dev.virtualplanet.rehabapp.controller

interface Callback<T> {
    fun onCallback(value : T)
}