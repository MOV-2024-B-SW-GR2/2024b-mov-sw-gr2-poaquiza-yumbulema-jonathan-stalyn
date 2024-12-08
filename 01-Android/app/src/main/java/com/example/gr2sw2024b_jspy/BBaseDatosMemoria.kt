package com.example.gr2sw2024b_jspy

class BBaseDatosMemoria {
    companion object{
        val arregloBEntrenador = arrayListOf<BEntrenador>()
        init {
            arregloBEntrenador.add(BEntrenador(1, "Jonathan", "a@a.com"))
            arregloBEntrenador.add(BEntrenador(2, "Paul", "b@b.com"))
            arregloBEntrenador.add(BEntrenador(3, "Michael", "c@c.com"))
        }
    }
}