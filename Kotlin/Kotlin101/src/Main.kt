fun main() {
    //asignaciones()
    //bucles()
    //val resultado = sumarNumeros(5,12)
    //println(resultado)
    //colecciones()
    val alumno1 = Alumno("Pepe","DAM1",20)
    val alumno2 = Alumno("Paco Porras","DAM2")
    println(alumno2.nombre)
}

fun asignaciones() {
    val a = 5 //con val no se puede cambiar el valor
    var aa: Int = 5 //no es necesario decir el tipo de variable
    val b = 0
    val e = 12.0
    val h = true
    val i = false
    val d = "Hola mundo"
    println(d)
    println(h == i)
    println(a)
    println(a + e)
    println((a + e).toInt())
    println("hola")
}

fun condiciones() {
    val a = 5
    val b = 0
    if (a == b) {
        println("Son iguales")
    } else print("$a y $b son diferentes")
    val e = "Hola mundo"
    val f = "Hola Badajoz"
    val g = "Hola Cáceres"
    val code = "06"
    when (code) {
        "06" -> println(e)
        "10" -> println(f)
        else -> println(e)
    }
}

fun bucles() {
    for (i in 1..9) { //rango
        println("$i gatetes")
    }
    var i = 0
    while (i < 10)
        println("${i++} mariquitas")

    repeat(10) { println("Hola 10 veces") }

    (0..10).forEach { println("10 times rango") }
    for (x in 2 until 12) {
        println("$x")
    }
}

fun sumarNumeros(num1: Int, num2: Int = 0): Int { //se puede dar un valor inicial
    return num1 + num2
}

fun colecciones() {
    val nombres = arrayOf("Pepe", "María")
    println(nombres.contentToString())
    val dias: MutableList<String> = emptyList<String>().toMutableList()
    dias.addAll(listOf("Lunes","Martes","miercoles"))
    println(dias)
    dias[2] = "Mercredi"
    println(dias)
}

