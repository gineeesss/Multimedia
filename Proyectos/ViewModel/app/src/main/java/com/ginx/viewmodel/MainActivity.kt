package com.ginx.viewmodel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.ginx.viewmodel.ui.theme.ViewModelTheme
import com.ginx.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ViewModelTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainApp(
                        modifier = Modifier.padding(innerPadding),
                        viewModel = MainViewModel()
                    )
                }
            }
        }
    }
}

@Composable
fun MainApp(modifier: Modifier = Modifier, viewModel: MainViewModel = MainViewModel()) {
    val animal by viewModel.animal.collectAsState()
    val animalSize by viewModel.animalSize.collectAsState()
    Column (Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceAround){
        Text(text = "Ejemplo de ViewModel")
        Text(text = "Caballo", fontSize = animalSize.sp
            ,
            modifier = Modifier.clickable { viewModel.cambiarAnimal(); viewModel.aumentarSize()} )


    }
}

@Preview(showBackground = true)
@Composable
fun MainAppPreview() {
    ViewModelTheme {
        MainApp()
    }
}