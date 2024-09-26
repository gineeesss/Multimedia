package com.ginx.numberguesser.model

class SecretNumber {
    val range = 1 .. 10
    var secretNumber = range.random()

    fun changeNumber(){
        val preNumber = secretNumber
        do {
            this.secretNumber = range.random()
        }while (preNumber==secretNumber)
    }
}