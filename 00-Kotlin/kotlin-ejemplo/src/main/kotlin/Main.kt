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
    calcularSueldo(10.00)//solo el requerido
    calcularSueldo(10.00,15.00,20.00)//requerido y opcionales
    //Named parameters
    calcularSueldo(sueldo=10.00,bonoEspecial=20.00)// bono en 2do lugar
    //gracias a los parametros nombrados
    calcularSueldo(bonoEspecial=20.00,sueldo=10.00, tasa = 14.00)// bono en 1er lugar

    val sumaA=Suma(1,1)
    val sumaB=Suma(null,1)
    val sumaC=Suma(1,null)
    val sumaD=Suma(null,null)
    sumaA.sumar()
    sumaB.sumar()
    sumaC.sumar()
    sumaD.sumar()
    println(Suma.elevarAlCuadrado(2))
    println(Suma.historialSumas)

    //Arreglos
    //estaticos: "arrayOf" que es estatico (no puede cambiar los elementos)
    val arregloEstatico: Array<Int> = arrayOf<Int>(1,2,3)
    println(arregloEstatico)
    //dinamicos: "arrayListOf" que puede cambiar los elementos
    val arregloDinamico: ArrayList<Int> = arrayListOf<Int>(
        1,2,3,4,5,6,7,8,9,10
    )
    print(arregloDinamico)
    arregloDinamico.add(11)
    arregloDinamico.add(12)
    print(arregloDinamico)

    //FOR EACH=>Unit
    //itera sobre cada elemento del arreglo
    val respuestaForEach:Unit=arregloDinamico
        .forEach{ valorActual:Int->
            println("Valor actual: ${valorActual}");
        }

    //it, eso es el elemento iterado
    arregloDinamico.forEach{ println("Valor actual: ${it}")}

    //MAP->MUTA el arreglo
    //1) Enviemos el nuevo valor de la iteracion
    //2) Nos devuelve un NUEVO arreglo con los valores modificados
    val respuestaMap:List<Double> = arregloDinamico
        .map{valorActual:Int->
            return@map valorActual.toDouble()+100.00 //map es el nombre del operador
        }
    println(respuestaMap)
    val respuestaMapDos=arregloDinamico.map { it+15 }
    println(respuestaMapDos)

    //FILTER->FILTRA el arreglo
    //1) Enviemos una expresion (TRUE o FALSE)
    //2) NUEVO arreglo con los valores filtrados
    val respuestaFilter:List<Int> = arregloDinamico
        .filter{valorActual:Int->
            //expresion o condicion
            val mayoresACinco:Boolean=valorActual>5
            return@filter mayoresACinco
        }

    val respuestaFilterDos=arregloDinamico.filter { it<=5 }
    println(respuestaFilter)
    println(respuestaFilterDos)

    //ANY->OR (alguna condicion se cumple?)
    //ALL->AND (todas las condiciones se cumplen?)
    val respuestaAny:Boolean=arregloDinamico
        .any{valorActual:Int->
            return@any valorActual>5
        }
    println(respuestaAny)//true
    val respuestaAll:Boolean=arregloDinamico
        .all{valorActual:Int->
            return@all valorActual>5
        }
    println(respuestaAll)//false

    //REDUCE->valor acumulado
    //valorAcumulado=0 siempre empieza en 0 en kotlin
    //[1,2,3,4,5]->acumular SUMAR estos valores del arreglo
    //valor iterado=ValorEmpiezo +1=0+1=1->iteracion 1
    //valor iterado=ValorAcumuladoIteracion1+2=1+2=3->iteracion 2
    //valor iterado=ValorAcumuladoIteracion2+3=3+3=6->iteracion 3
    //valor iterado=ValorAcumuladoIteracion3+4=6+4=10->iteracion 4
    //valor iterado=ValorAcumuladoIteracion4+5=10+5=15->iteracion 5
    val respuestaReduce:Int=arregloDinamico
        .reduce{acumulado:Int, valorActual:Int->
            return@reduce acumulado+valorActual//->cambiar o usar la logica de negocio
        }
    println(respuestaReduce)
    //return@reduce acumulado+(itemCarrito.cantidad*itemCarrito.precio)
}

fun imprimirNombre(nombre:String):Unit{//no existe el void pero si el Unit, es la funcion que no devuelve nada
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

fun calcularSueldo(
    sueldo:Double, //parametro requerido
    tasa:Double = 12.00, //parametro opcional
    bonoEspecial:Double? = null //parametro opcional que puede ser nulo
    //si no se le pasa un valor, se le asigna null, ?->es nulable
):Double{
    //Int->Int? Nulable
    //String->String? Nulable
    //Date->Date? Nulable
    if(bonoEspecial==null){
        return sueldo*(100/tasa)
    }else{
        return sueldo*(100/tasa)+bonoEspecial
    }
}

abstract class NumerosJava{
    protected val numeroUno:Int
    private val numeroDos:Int
    constructor(
        uno:Int,
        dos:Int
    ){
        this.numeroUno=uno
        this.numeroDos=dos
        println("Inicializando")
    }
}
abstract class Numeros(//constructor primario ojo-parentesis() para cosntructor primario
    protected val numeroUno:Int,//instancia.numeroUno
    protected val numeroDos:Int//instancia.numeroDos //protected
    //parametroNoUsadoNoPropiedadDeLaClase:Int?=null
){
        init {//BLOQUE DE CODIGO DEL CONSTRUCTOR PRIMARIO
        this.numeroUno
        this.numeroDos
        println("Inicializando")
    }
}

class Suma(
    unoParametro:Int,
    dosParametro:Int
):Numeros(//constructor primario con clase padre
    unoParametro,
    dosParametro
){
    public val soyPublicoExplicito:String ="Publicar"
    val soyPublicoImplicito="Publico implicito"//el modificador de acceso es public por defecto
    init{//bloque constructor primario
        this.numeroUno
        this.numeroDos
        numeroUno //this es opcional (propiedades, metodos)
        numeroDos
        this.soyPublicoImplicito
        soyPublicoExplicito
    }
    constructor(//constructor secundario
        uno:Int?, //entero nulable
        dos:Int,
    ):this(
        if (uno==null) 0 else uno,
        dos
    ){
        //bloque de codigo del constructor secundario

    }

    constructor(//constructor secundario
        uno:Int, //entero nulable
        dos:Int?,
    ):this(
        uno,
        if (dos==null) 0 else dos
    ){

    }

    constructor(//constructor secundario
        uno:Int?, //entero nulable
        dos:Int?, //entero nulable
    ):this(
        if (uno==null) 0 else uno,
        if (dos==null) 0 else dos
    ){

    }

    fun sumar():Int{
        val total=numeroUno+numeroDos
        agregarHistorial(total)
        return total
    }
    //no hay propiedades ni metodos estaticos en kotlin.
    //companion object, es un objeto que acompaña a todas las instancias de la clase
    companion object{ //comparte entre todas las instancias, similar al static de java
        //funciones y variables
        //es accesible NombreClase.metodo, NombreClase.funcion=>
        //Suma.pi
        val pi=3.1416
        //Suma.elevadoAlCuadrado
        fun elevarAlCuadrado(num:Int):Int{ return num*num }
        val historialSumas= arrayListOf<Int>()
        fun agregarHistorial(valorTotalSuma:Int) {//Suma.agregarHistorial
            historialSumas.add(valorTotalSuma)
        }
    }


}










