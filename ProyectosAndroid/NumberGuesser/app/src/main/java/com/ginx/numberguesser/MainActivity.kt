package com.ginx.numberguesser

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ginx.numberguesser.model.SecretNumber
import com.ginx.numberguesser.ui.theme.NumberGuesserTheme

class MainActivity : ComponentActivity() {
    val mySecretNumber = SecretNumber()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("Guess", mySecretNumber.secretNumber.toString())
        enableEdgeToEdge()
        setContent {
            NumberGuesserTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MyApp(
                        mySecretNumber,
                        modifier = Modifier.padding(innerPadding),
                        context = applicationContext
                    )
                }
            }
        }
    }
}

@Composable
fun Explicacion() {
    Column(
        modifier = Modifier
            .padding(24.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BasicText(
            text = "Intentar adivinar el número que ha sido generado de forma aleatoria",
            style = TextStyle(color = Color.White, fontSize = 24.sp),
        )
    }
}

@Composable
fun MyApp(
    mySecretNumber: SecretNumber = SecretNumber(),
    modifier: Modifier = Modifier,
    context: Context
) {
    var userNumber by remember { mutableStateOf("0") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Magenta)
    ) {
        Text(
            text = "Hello Holita",
            modifier = modifier
        )
        Spacer(modifier = Modifier.padding(6.dp))
        Explicacion()
        TextField(
            label = { Text("Número") },
            value = userNumber,
            onValueChange = { userNumber = it },
            modifier = Modifier.padding(8.dp)
        )
        ButtonComprobar(mySecretNumber, userNumber, context)
    }
}

fun comprobar(
    mySecretNumber: SecretNumber,
    userNumber: String,
    context: Context,
    acertado: MutableState<Boolean>
) {
    if (acertado.value!=true) {
        try {
            if (mySecretNumber.secretNumber == userNumber.toInt()) {
                Log.i("JUEGO", "Numero correcto: ${mySecretNumber.secretNumber}")
                Toast.makeText(context, "Has acertado", Toast.LENGTH_LONG).show()
                acertado.value = true
            } else {
                Log.i("JUEGO", "Error")
            }
        } catch (exception: NumberFormatException) {
            Toast.makeText(context, "INTRODUCE UN NÚMERO", Toast.LENGTH_LONG).show()
        }
    }else{
        mySecretNumber.changeNumber()
        Log.i("JUEGO","Nuevo numero: ${mySecretNumber.secretNumber}")
        acertado.value=false
    }
}

@Composable
fun ButtonComprobar(mySecretNumber: SecretNumber, userNumber: String, context: Context) {
    var acertado: MutableState<Boolean> = remember { mutableStateOf(false) }
    OutlinedButton(onClick = { comprobar(mySecretNumber, userNumber, context, acertado) }) {
        Icon(imageVector = Icons.Filled.FavoriteBorder, "comprobar")
        Text(if (acertado.value==false)"Ding Dong" else "Jugar Again")
        Icon(imageVector = Icons.Filled.FavoriteBorder, "comprobar")
    }
}


/*fun numberGenerator():Int{
    return Random.nextInt()
}*/

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NumberGuesserTheme {
    }
}

@Preview(showBackground = true)
@Composable
fun ButtonComprobarPreview() {
}