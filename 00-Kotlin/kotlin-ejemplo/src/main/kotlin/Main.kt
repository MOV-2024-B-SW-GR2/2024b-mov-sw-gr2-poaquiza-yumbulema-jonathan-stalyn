package org.example

import java.util.*

fun main(args: Array<String>) {
    val inmutable: String ="Adrian";
    var mutable: String = "Vicente";
    mutable= "Adrian";
    //Kotlin es fuertemente tipado
    //Duck typing
        val ejemploVariable="Jonathan Poaquiza"
        ejemploVariable.trim()
        val edadEjemplo:Int=12

        val nombreProfesor:String="Jonathan Poaquiza";
        val sueldo:Double=1.2
        val estadoCivil:Char='C'
        val mayorEdad:Boolean=true

    //clases en java
        val fechaNacimiento: Date= Date()
    //en KOTLIN tenemos acceso a todas las clases y tipos de datos de java
    //string, char, int, boolean y date
    //when-es como switch
    val estadoCivilWhen='C'
    when(estadoCivilWhen){
        ('C')->{
            println("Casado")
        }
        'S'->{
            println("Soltero")
        }
        else->{
            println("No sabemos")
        }
    }
    val esSoltero=(estadoCivilWhen=='S')
    val coqueteo=if(esSoltero) "Si" else "No" //no se usa el operador ternario, se usa if else de una linea
    //cada bloque puede tener o no parentesis y empieza con flecha

    imprimirNombre("Jonathan")
}

fun imprimirNombre(nombre:String):Unit{//no existe el void pero si el Unit, es la funcion que no devueove nada
    fun otraFuncionAdentro(){ //funcion dentro de otra funcion
        println("Otra funcion adentro")//no es requerido poner unit
        //kotlin entiende que no devuelve nada
    }
    println("Nombre: $nombre")//template strings
    println("Nombre: ${nombre}")//se puede hacer operaciones dentro
    println("Nombre: ${nombre+nombre}")//llaves opcionales en ciertos casos
    println("Nombre: ${nombre.toString()}")
    println("Nombre: $nombre.toString()")
    otraFuncionAdentro()

}
