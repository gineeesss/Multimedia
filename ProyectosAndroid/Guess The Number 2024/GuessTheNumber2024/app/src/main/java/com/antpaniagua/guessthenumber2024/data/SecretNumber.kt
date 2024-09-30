package com.antpaniagua.guessthenumber2024.data

class SecretNumber {
    //KT: val range: IntRange  = 1..10; aunque generalmente no es necesario indicar el rango
    val range = 1..10

    var secretNumber = range.random() //KT: Genera un aelatorio del rango

    fun cambiar() {
        var newNumber = range.random() //KT: Valor aleatorio
        while (newNumber == secretNumber) {
            newNumber = range.random()
        }
        secretNumber = newNumber
    }
}