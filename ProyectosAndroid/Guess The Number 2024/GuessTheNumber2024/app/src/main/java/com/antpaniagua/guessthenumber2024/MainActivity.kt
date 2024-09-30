package com.antpaniagua.guessthenumber2024

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.antpaniagua.guessthenumber2024.data.SecretNumber
import com.antpaniagua.guessthenumber2024.ui.theme.GuessTheNumber2024Theme

/* Guess The Number 2024
Antonio Paniagua Septiembre 2024

Versión trabajada en clase de DAM2. Esta versión es similar a la que hemos hecho en clase con
algunas leves diferencias:

En lugar de declarar el contexto como applicacionContext usamos LocalContext.current, que puede
ser empleado dentro de cualquier composable, simplificando su uso.

El textfield se ha diseñado desde una función independiente para añadirle algunas características
comentadas en clase, como sacar el teclado numérico.

Se han traducido algunas partes al inglés, se usa una constante para las etiquetas de Log,
 y se ha limpiado un poco el código.

Nota: A la hora de copìar parte este código observa que en setContet de onCreate el nombre del tema siempre
es diferente, en función del nombre del proyecto.
 */

class MainActivity : ComponentActivity() {
    private val TAG: String? = "JUEGO" //KT: TAG se emplea como etiqueta en los logs
    val mySecretNumber = SecretNumber()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GuessTheNumber2024Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    //applicationContext es el contexto de la aplicación. No lo pasamos como párametro
                    MyApp(modifier = Modifier.padding(innerPadding), mySecretNumber)
                }
            }
        }
    }

    @Composable
    fun MyApp(
        modifier: Modifier = Modifier,
        mySecretNumber: SecretNumber = SecretNumber(),
    ) {
        /* Compose y Kotlin: ¿Por qué no usamos by en este lugar y declaramos number como un simple
        String? En este caso pasamos el elemento como MutableState<String para poder manipularlo
        más abajo en el método checkNumber. Esta técnica la abandonaremos más adelante para usar
        métodos callbacks (onxXXX) que es la técnica recomendada en Jetpack Compose y que previene
        determinados errores.
         */
        var number: MutableState<String> = remember { mutableStateOf("") }
        val keyboardController = LocalSoftwareKeyboardController.current

        Column(
            Modifier
                .padding(12.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround, //JC: Probar SpaceAround, SpaceBetween
        ) {
            Text(
                "Adivina el número",
                textAlign = TextAlign.Center,
                style = typography.titleLarge,
                modifier = Modifier.height(64.dp)
            )
            Text("Introduce un número del uno al 10 y haz clic en el botón Comprobar para ver si has acertado.")
            Spacer(modifier = Modifier.padding(6.dp))
            TextFieldUser(number, keyboardController)
            Spacer(modifier = Modifier.padding(24.dp))
            ButtonCheck(mySecretNumber, number, keyboardController)
            Spacer(modifier = Modifier.padding(24.dp))
        }
    }

    @Composable
    fun TextFieldUser(
        number: MutableState<String>,
        keyboardController: SoftwareKeyboardController?
    ) {
        //https://developer.android.com/jetpack/compose/text/user-input
        //val keyboardController = LocalSoftwareKeyboardController.current

        OutlinedTextField(
            label = { Text(text = "Número") },
            value = number.value,
            onValueChange = { number.value = it }, //Asigna el valor a number.value
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() }),
        )
    }

    @Composable
    fun ButtonCheck(
        mySecretNumber: SecretNumber,
        number: MutableState<String>,
        keyboardController: SoftwareKeyboardController?
    ) {
        //KT: Esta forma es más adecuada para usar un contexto
        //KT: El contexto es una referencia al estado actual de la aplicación
        var context: Context = LocalContext.current
        var isGuessed: MutableState<Boolean> = remember { mutableStateOf(false) }

        OutlinedButton(
            onClick = {
                checkNumber( mySecretNumber, number, context, isGuessed)
                keyboardController?.hide()
            },
            colors = ButtonDefaults.textButtonColors()
        ) {
            Text(if (isGuessed.value == false) "Comprobar número" else "Jugar otra vez")
        }
    }

    private fun checkNumber(
        mySecretNumber: SecretNumber,
        number: MutableState<String>,
        context: Context,
        numeroAcertado: MutableState<Boolean>
    ) {
        if (numeroAcertado.value == false) {
            try {
                if (mySecretNumber.secretNumber == number.value.toInt()) {
                    Log.d(TAG, "Son iguales")
                    Toast.makeText(context, "Número acertado", Toast.LENGTH_SHORT).show()
                    numeroAcertado.value = true
                } else {
                    /*Aquí podemos manipular el contenido de number porque lo hemos traído como
                    un MutableState<String>. Podemos cambiar su interior. Si hubiese llegado como un
                    parámetro String llegaría como un val, que no se puede cambiar dentro de la
                    función */

                    number.value = ""
                    Log.d(TAG, "Diferentes")
                    Toast.makeText(context, "Diferentes", Toast.LENGTH_SHORT).show()
                }
            } catch (nfe: NumberFormatException) {
                Toast.makeText(context, "Eso no es un número", Toast.LENGTH_SHORT).show()
            }
        } else {
            number.value = ""
            mySecretNumber.cambiar()
            //KT $ Hace que se interprete
            Log.i(TAG, "Nuevo Número: ${mySecretNumber.secretNumber}")
            numeroAcertado.value = false
        }
    }



    /* Previews ********************************************************/

    @Preview(showBackground = true)
    @Composable
    fun MyAppPreview() {
        GuessTheNumber2024Theme {
            MyApp()
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun ButtonComprobarPreview() {
        GuessTheNumber2024Theme {
            ButtonCheck(SecretNumber(), remember { mutableStateOf("") }, null)
        }
    }

} //Fin de la clase