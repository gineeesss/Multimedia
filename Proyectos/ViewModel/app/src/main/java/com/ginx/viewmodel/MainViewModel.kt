package com.ginx.viewmodel

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import com.ginx.viewmodel.data.Datasource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel : ViewModel() {
    private val _animal = MutableStateFlow<String>("")
    val animal: StateFlow<String> = _animal.asStateFlow()
    private val _animalSize = MutableStateFlow<Int>(0)
    val animalSize: StateFlow<Int> = _animalSize.asStateFlow()

    init {
        _animal.value = "Perro"
        _animalSize.value = 16
    }

    fun cambiarAnimal() {
        _animal.value = Datasource.animales.random()
    }

    fun aumentarSize() {
        _animalSize.value += 6
    }
}