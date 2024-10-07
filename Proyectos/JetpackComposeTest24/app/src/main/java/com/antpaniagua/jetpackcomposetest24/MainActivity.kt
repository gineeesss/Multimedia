package com.antpaniagua.jetpackcomposetest24

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Face
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomStart
import androidx.compose.ui.Alignment.Companion.CenterEnd
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.antpaniagua.jetpackcomposetest24.ui.theme.JetpackComposeTest24Theme
import kotlinx.coroutines.flow.MutableStateFlow

/**
 * Jetpack Compose Test
 * Septiembre de 2024. Antonio Paniagua
 *
 * Contiene una pequeña prueba de concepto de varios elementos de interface, funciones de
 * componibilidad para probar su apariencia y funcionalidades
 *
 *
 */

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        //TODO: Esto es un problema en las rotaciones, ya que se reincia la lista
        var names =
            mutableStateListOf<String>(
                "Pepe",
                "María",
                "Manuel",
                "Ana",
                "Enrique",
                "Marina",
                "Jesús",
                "Lola"
            )

        for (i in (1..50)) {
            names.add(names[i])
        }

        setContent {
            JetpackComposeTest24Theme {
                MyApp(modifier = Modifier.fillMaxSize(), names)
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MyApp(modifier: Modifier = Modifier, names: MutableList<String>) {
    /*
    Remember hace que se recuerde en las diferentes recomposiciones que suceden cuando cambia algún valor.
    RememberSaveable hará que lo haga además si se reconstruye la Actividad (p.e. al girar el móvil)
    Kotlin dispone de delegación de propiedades (by), que se encarga de generar el get y el set automáticamente y llamarlos
    https://developer.android.com/reference/kotlin/androidx/compose/runtime/RememberObserver
     */
    var destino by rememberSaveable { mutableStateOf("") }

    Column(
        Modifier
            .padding(6.dp, 12.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        TextAppTitle()

        val planetas: Array<String> = stringArrayResource(id = R.array.planetas_array)
        planetas.forEach { Log.i("CADENAS", "Planeta: $it") }


        //FlowRow nos permite rellenar de forma fluida la lista de botones, generando nuevas líneas
        FlowRow() {

            val listTextoBotones = listOf<String>(
                "Imagen",
                "Imagen apilada",
                "Una lista",
                "Una lista lazy",
                "Un slider",
                "Elevación de estado"
            )
            val listAcciones = listOf<String>(
                "imageTest",
                "imageBoxedTest",
                "listaTest",
                "lazyTest",
                "sliderTest",
                "elevacion"
            )
            for (i in 0..listAcciones.size - 1) {
                //Pasamos una función (onChange) como parámetro que modifica el estado
                ButtonTest(
                    onChange = { s: String -> destino = s },
                    seleccionado = listAcciones[i],
                    textoBoton = listTextoBotones[i]
                )
            }
        }
        Spacer(modifier = Modifier.padding(24.dp))
        Column(Modifier.fillMaxWidth()) {
            when (destino) {
                "imageTest" -> GenericImage()
                "imageBoxedTest" -> GenericImageBoxed()
                "listaTest" -> Lista(names)
                "lazyTest" -> ListaLazy(names)
                "sliderTest" -> SliderTest()
                "elevacion" -> HoistingTest()

            }
        }
    }
}

/*
Función de componibilidad que genera un botón con los parámetros indicados para llevar la aplicación
a un punto u otro
 */

/**
 * Elevación de estado
 *
 * onChage actua aqui como un callback, elevando el estado. Es decir, en lugar de pasar
 * como parámetro el estado "destino", que sería más complejo modificarlo (tendría que ser un var y
 * tendría más efectos colaterales), símplemente al pulsar el botón, se llama a la función onChange
 * con el parámetro que nos interese (puede ser sin parámetro también). En la función superior
 * se toma el valor recibido en onChange y se actualiza el estado destino usando la expresión lambda
 * (s: String -> destino = s)
 */
@Composable
fun ButtonTest(
    onChange: (String) -> Unit, seleccionado: String, textoBoton: String
) {
    Button(
        onClick = { onChange(seleccionado) },
        modifier = Modifier.padding(2.dp, 0.dp)
    ) {
        Text(text = textoBoton)
    }
}

/***************************************************************************************/

@Composable
fun GenericImage() {
    Row(
        modifier = Modifier
            .padding(6.dp)
            .fillMaxWidth()
    ) {
        Text(
            modifier = Modifier
                .padding(12.dp, 0.dp)
                .align(CenterVertically), text = "Androide"
        )
        Image(
            painter = painterResource(id = R.drawable.androidretro),
            contentDescription = "A image"
        )
    }
}


@Composable
fun GenericImageBoxed() {
    Box(
        modifier = Modifier
            .padding(6.dp)
            .fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = R.drawable.androidretro),
            contentDescription = "A image",
            modifier = Modifier.align(Alignment.Center)
        )
        Text(
            modifier = Modifier
                .padding(32.dp, 0.dp)
                .align(BottomStart),
            text = "Androide",
            color = Color.White,
            fontSize = 36.sp
        )
    }
}

/***************************************************************************************/

@Composable
fun Lista(names: MutableList<String>) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(6.dp)
    ) {
        Text(
            text = "Lista\n${names.size}",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(0.dp, 0.dp, 24.dp, 0.dp)
                .align(CenterVertically)
        )
        Column(Modifier.fillMaxWidth()) {
            for (name in names) {
                Card(modifier = Modifier.fillMaxWidth()) {
                    ListItem(name, names)
                }
            }
        }

    }
}

/***************************************************************************************/


//Asegúrate de importar androidx.compose.foundation.lazy.items, ya que Android Studio puede elegir otro import.
@Composable
fun ListaLazy(names: MutableList<String>) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(6.dp)
    ) {
        Text(
            "Lista\n${names.size}",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(0.dp, 0.dp, 24.dp, 0.dp).align(CenterVertically)
        )
        LazyColumn(Modifier.fillMaxWidth()) {
            items(items = names) { name ->
                Card(
                    modifier = Modifier.fillMaxWidth().padding(4.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                    border = BorderStroke(1.dp, Color.LightGray),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),


                    ) {
                    ListItem(name, names)
                }
            }
        }
    }
}


//pasamos la lista names para poder eliminar de ella
@Composable
fun ListItem(name: String, names: MutableList<String>) {
    // Biblioteca de iconos
    // https://developer.android.com/reference/kotlin/androidx/compose/material/icons/package-summary
    var expanded by remember { mutableStateOf(false) }

    Row {
        Icon(
            imageVector = if ((0..10).random() != 5) Icons.Rounded.Person else Icons.Rounded.Face,
            contentDescription = ("Icono genérico"),
            tint = Color((0..160).random(), (0..160).random(), (100..200).random(), 255),
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .size(size = 64.dp)
                .clickable { Log.d("CLICKED", "Click on $name") },
        )
        Text(text = name,
            Modifier
                .padding(6.dp, 12.dp)
                .clickable { names.remove(name) }
                .align(Alignment.CenterVertically)
        )

        /* La alineación heredada que hacemos desde el botón sólo funciona si está dentro de un box,
        no desde un Row o Column
         */
        Box(
            Modifier
                .fillMaxWidth()
                .align(CenterVertically)
        ) {
            Button(
                onClick = { expanded = !expanded },
                modifier = Modifier.align(CenterEnd)
            )
            {
                Text(if (expanded) "Reducir" else "Más datos")

            }
        }
    }
}

/***************************************************************************************/

/* Versión final del slider, empleando MutableState, sin Flow*/
@Composable
fun SliderTest() {
    var sliderPosition by remember { mutableStateOf(0f)}
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Yellow)

    ) {
        TextBoldSimple(message = "Arrastra el slider", fontSize = sliderPosition)
        Spacer(modifier = Modifier.height(150.dp))
        DemoSlider(
            sliderPosition = sliderPosition,
            onPositionChange = { position: Float -> sliderPosition = position }
        )
        Text(
            style = MaterialTheme.typography.headlineMedium,
            text = sliderPosition.toInt().toString() + "sp"
        )
    }
}

@Composable
fun DemoSlider(sliderPosition: Float, onPositionChange: (Float) -> Unit) {
    Slider(
        modifier = Modifier.padding(10.dp),
        valueRange = 20f..38f,
        value = sliderPosition,
        onValueChange = { onPositionChange(it) }
    )
}


/***************************************************************************************/


/**
 * Usamos elevación de estado de dos formas posibles:
 * Corta: onIncrement, sólo la llamada. Al detectar la pulsación actualiza el valor
 * Larga: onDecrement. Hace lo mismo, pero mostramos la función lambda completa con una variable propia
 */
@Composable
fun HoistingTest() {
    var total by rememberSaveable() { mutableStateOf(0) }
    ContadorSinEstado(
        total = total,
        onIncrement = { total++ },
        onDecrement = { t: Int -> total = t - 1 })
}


@Composable
fun ContadorSinEstado(total: Int, onIncrement: () -> Unit, onDecrement: (Int) -> Unit) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(6.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedButton(onClick = onIncrement, enabled = total < 10) {
            Text(text = stringResource(R.string.aumentar_contador))
        }
        OutlinedButton(onClick = { onDecrement(total) }, enabled = total > 0) {
            Text(text = "Reducir contador")
        }
        if (total > 0) {
            Text(
                text = "Contador $total", fontSize = 24.sp, modifier = Modifier.padding(0.dp, 24.dp)

            )
        }

    }
}


@Preview(showBackground = true)
@Composable
fun MyAppPreview() {
    JetpackComposeTest24Theme {
        MyApp(modifier = Modifier.fillMaxSize(), emptyList<String>().toMutableList())
    }
}

@Preview(showBackground = true)
@Composable
fun GenericImageBoxedPreview() {
    JetpackComposeTest24Theme {
        GenericImageBoxed()
    }
}


@Preview(showBackground = true)
@Composable
fun SliderTestPreview() {
    JetpackComposeTest24Theme {
        SliderTest()
    }
}