package com.ginx.composetest

import android.os.Bundle
import android.util.Log
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TriStateCheckbox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomStart
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ginx.composetest.ui.theme.ComposeTestTheme
import org.w3c.dom.Text

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeTestTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MyApp(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MyApp(modifier: Modifier = Modifier) {

    var destino by remember { mutableStateOf(0) }
    var names = remember {
        mutableListOf("Ginés,Paco,Lucía,Antonio,Julio,Laura")
    }
    for (i in (1..50)) {
        names.add(names[i])
    }
    Column(
        modifier
            .padding(12.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        FlowRow() {
            //BotonImagen(onChange = {destino=1})
            //BotonImagenBox(onChange = {destino=3})
            //BotonContador(onChange = {destino=2})
            BotonGenerico("Una Imagen", onChange = { destino = 1 })
            BotonGenerico("Una Imagen Box", onChange = { destino = 2 })
            BotonGenerico("Una Contador", onChange = { destino = 3 })
            BotonGenerico("Una Lista", onChange = { destino = 4 })
        }
        Spacer(modifier.padding(48.dp))
        when (destino) {
            1 -> ImagenAndroid()
            2 -> ImagenBox()
            3 -> TextoContador()
            4 -> ListaNombre(names)
            else -> TextoBienvenue()
        }
    }
}

@Composable
fun ListaNombre(names: MutableList<String>) {
    Column() {
        for (item in names) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
            )
            {
                Text(item)
            }
        }
    }
}

@Composable
fun TextoContador() {
    var total by remember { mutableStateOf(0) }
    ContadorCompuesto(total = total,
        onDecrementar = { total-- },
        onIncrementar = { t -> total = t + 1 }
    )
}

@Composable
fun ContadorCompuesto(total: Int, onDecrementar: () -> Unit, onIncrementar: (Int) -> Unit) {
    Text(total.toString(), fontSize = 48.sp)
    OutlinedButton(onClick = onDecrementar) { Text("Decrementar") }
    OutlinedButton(onClick = { onIncrementar(total) }) { Text("Incrementar") }
}

@Composable
fun TextoBienvenue() {
    Text("Bienvenidos", fontSize = 48.sp)
}

@Composable
fun ImagenAndroid() {
    Image(
        painter = painterResource(R.drawable.prueba2),
        modifier = Modifier
            .size(200.dp)
            .clip(CircleShape), contentDescription = "Imagen"
    )
    Text(
        "Un Androirde", fontSize = 32.sp,
        color = Color.Transparent,
        modifier = Modifier.padding(4.dp, 6.dp)
    )
}

@Composable
fun BotonGenerico(caption: String, onChange: () -> Unit) {
    Button(onClick = onChange) {
        Text(text = caption)
    }
}

@Composable
fun ImagenBox() {
    Box() {
        Image(
            painter = painterResource(R.drawable.prueba2),
            modifier = Modifier
                .size(200.dp)
                .clip(CircleShape), contentDescription = "Imagen"
        )
        Text("Un Androirde", fontSize = 32.sp)
    }
}
//@Composable
//fun BotonContador(onChange: ()-> Unit) {
//    Button (onClick = {onChange()}) {
//        Text("Un Contador")
//    }
//}
//@Composable
//fun BotonImagen(onChange: ()-> Unit) {
//    Button (onClick = onChange ) {
//        Text("Una Imagen")
//    }
//}
//@Composable
//fun BotonImagenBox(onChange: ()-> Unit) {
//    Button (onClick = onChange ) {
//        Text("Una Imagen")
//    }
//}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeTestTheme {
        MyApp()
    }
}