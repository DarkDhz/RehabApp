package dev.virtualplanet.rehabapp.model

interface CustomList<T> {
    fun getSize() : Int
    fun getByName(name: String) : T?
    fun add(item: T)
}