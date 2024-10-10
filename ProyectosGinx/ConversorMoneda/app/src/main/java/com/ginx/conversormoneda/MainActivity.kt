package com.ginx.conversormoneda
import android.os.Bundle
import android.provider.CalendarContract.Colors
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ginx.conversormoneda.ui.theme.ConversorMonedaTheme


/*1. Muestra un valor fijo numérico en un Text. Debajo dos botones, uno para convertir
la cantidad a dólares y otra para hacerlo a euros.

2. Genera dos composables para cada botón, de forma que mediante elevación de estado se
modifque la cantidad.

3. Convierte el texto en un TextField para poder indicar la cantidad a convertir.

4. Al hacer la conversión a una moneda, el resultado se mostrará seguido del signo $ o €,
al tiempo que el botón que se ha pulsado se desactivará.

5. Extra. Añade una barra de título con un nombre para la aplicación y algún icono.*/

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTitle("Conversor EUR-USD");
        //getActionBar().setIcon(R.drawable.my_icon);
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ConversorMonedaTheme {
                Scaffold(modifier = Modifier.fillMaxSize(),
                    { MyTopBar() }) { innerPadding ->
                    //LLAMO AL CON MYTOP APP DENTRO DEL SCAFFOLD PARA LUEGO HACER EL INNGERPADING
                    MyApp(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }


}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopBar() {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Gray,
            titleContentColor = Color.Magenta,
        ),
        title ={ Text("Conversor EUR-USD") }
        , navigationIcon = {
<<<<<<< HEAD
        IconButton(onClick = { /* do something */ }) {
            Icon(
                imageVector = Icons.Rounded.ShoppingCart,
                contentDescription = "Icono",
                modifier = Modifier.size(64.dp).clickable {  },
                tint = Color((0..225).random(),(0..225).random(),(0..225).random())
            )

        }
    }
=======
            IconButton(onClick = { /* do something */ }) {
                Icon(
                    imageVector = Icons.Rounded.ShoppingCart,
                    contentDescription = "Icono",
                    modifier = Modifier.size(64.dp).clickable {  },
                    tint = Color((0..225).random(),(0..225).random(),(0..225).random())
                )

            }
        }
>>>>>>> f3cc6f4a9b6f5b533740bcba5cfc32512936e0c6
    )
}

@Composable
fun MyApp(modifier: Modifier = Modifier) {
    val amount: MutableState<String> = remember { mutableStateOf("") }
    val amountConverted: MutableState<Float> = remember { mutableStateOf(0.0f) }
    Column (
        modifier
            .padding(12.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center){
        /*Row (){
        Boton("Convertir", onConvert = {convert(amount.value)});
        }
        Row (modifier = Modifier, horizontalArrangement = Arrangement.Start){
            TextoSalida(amount)
        }*/
        Conversor(amount, amountConverted, onConvert = { amountConverted.value = convert(amount.value.toFloatOrNull()?: 0.0f)})
        //Text(text = "Conversion ${amountConverted.value} $")
        Text(text = "Conversion ${String.format("%.2f", amountConverted.value)} $")

    }
}

@Composable
fun Conversor(
    amount: MutableState<String>,
    amountConverted: MutableState<Float>,
    onConvert: () -> Unit
) {
    OutlinedTextField(
        label = { Text(text = "Precio") },
        value = amount.value,
        onValueChange = { amount.value = it}, //Asigna el valor a number.value
        placeholder = { Text(text = "€") }  // Muestra "10" como placeholder
    )
    OutlinedButton(onClick = onConvert) {
        Text(text = "Convertir")
    }
    /*LazyColumn(
        modifier = Modifier.fillMaxWidth().wrapContentHeight()
    ) {

    }*/
}

// EUR - USD  1,0939197
var conversion = 1.0939197f;
fun convert(amount: Float): Float{
    return amount* conversion
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ConversorMonedaTheme {
        MyApp(modifier = Modifier)
    }
}
<<<<<<< HEAD
=======
















>>>>>>> f3cc6f4a9b6f5b533740bcba5cfc32512936e0c6
@Composable
fun TextoSalida(amount: MutableState<Float>) {
    Text("${amount.value}")
}
@Composable
fun Boton(s: String, onConvert: () -> Unit) {
    OutlinedButton(onClick = onConvert/*, enabled = amount.value > 0.0*/) {
        Text(text = s)
    }
}

@Composable
fun TextFieldUser(
    number: MutableState<String>
) {
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



<<<<<<< HEAD

=======
>>>>>>> f3cc6f4a9b6f5b533740bcba5cfc32512936e0c6
