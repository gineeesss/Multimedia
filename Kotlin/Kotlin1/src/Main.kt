

fun main(){
    asignaciones()
}
fun asignaciones(){
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
    println( (a + e).toInt() )
}
fun condiciones() {
    val a = 5
    val b = 0
    if (a == b) {
        println("Son iguales")
    }else print("$a y $b son diferentes")
}