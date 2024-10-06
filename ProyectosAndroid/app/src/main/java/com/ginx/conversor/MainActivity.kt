package com.ginx.conversor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ginx.conversor.ui.theme.ConversorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ConversorTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MyApp(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}


/*1. Muestra un valor fijo numérico en un Text. Debajo dos botones, uno para convertir
la cantidad a dólares y otra para hacerlo a euros.

2. Genera dos composables para cada botón, de forma que mediante elevación de estado se
modifque la cantidad.

3. Convierte el texto en un TextField para poder indicar la cantidad a convertir.

4. Al hacer la conversión a una moneda, el resultado se mostrará seguido del signo $ o €,
al tiempo que el botón que se ha pulsado se desactivará.

5. Extra. Añade una barra de título con un nombre para la aplicación y algún icono.*/

@Composable
fun MyApp(modifier: Modifier = Modifier) {
    var number: MutableState<String> = remember { mutableStateOf("0") }
    //val keyboardController = LocalSoftwareKeyboardController.current
    Column (modifier
        .padding(12.dp)
        .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center){
        TextFieldUser(number/*, keyboardController*/)
    }
}

@Composable
fun TextFieldUser(
    number: MutableState<String>
    //keyboardController: SoftwareKeyboardController?
) {
    //https://developer.android.com/jetpack/compose/text/user-input
    //val keyboardController = LocalSoftwareKeyboardController.current

    OutlinedTextField(
        label = { Text(text = "Precio") },
        value = number.value,
        onValueChange = { number.value = it }, //Asigna el valor a number.value
        placeholder = { Text(text = "€") }  // Muestra "10" como placeholder
            /*keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Done*/
        )
        //keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() }),

}

/*@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ConversorTheme {
       // MyApp()
    }
}*/