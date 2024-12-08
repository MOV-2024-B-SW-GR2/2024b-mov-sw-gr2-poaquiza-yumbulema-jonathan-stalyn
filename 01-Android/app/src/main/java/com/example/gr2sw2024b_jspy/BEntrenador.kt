package com.example.gr2sw2024b_jspy

class BEntrenador(
    val id: Int,
    val nombre: String,
    val descripcion: String?
) {
    override  fun toString(): String {
        return "$nombre $descripcion"
    }

}