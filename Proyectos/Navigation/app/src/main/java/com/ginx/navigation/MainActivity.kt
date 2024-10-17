package com.ginx.navigation

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.TestModifierUpdaterLayout
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ginx.navigation.ui.theme.NavigationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NavigationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainApp(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun MainApp(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home"){
        composable("home") {
            /* TODO Esta Pantalla*/
            MiHomeScreen(onNavSelected = {dest -> navController.navigate(dest)}) // como se devuelve solo un parámetro, se puede usar it
        }
        composable("secondScreen") {
            /* TODO la Segunda Pantalla*/
            SecondScreen { navController.popBackStack() }
            //SecondScreen(onNavSelected = {dest -> navController.navigate(dest)})
        }
        composable("thirdScreen") {
            /* TODO la Tercera Pantalla*/
            ThirdScreen(onNavSelected = {dest -> navController.navigate(dest)})
            //ThirdScreen ({navController.popBackStack()})
        }

    }
}// Fin del Main



@Composable
fun ThirdScreen(onNavSelected: (String) -> Unit) {
    Column(
        Modifier
            .background(Color.DarkGray)
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(text = "Navegación con JetPackCompose")
        Spacer(Modifier.padding(10.dp))
        Text(text = "Bienvenido a la Tercera Pantalla")
        Spacer(Modifier.padding(10.dp))
        Row {
            ElevatedButton(onClick = {onNavSelected("home")}) {
                Text(text = "Pantalla Home")
            }
            ElevatedButton(onClick = {onNavSelected("secondScreen")}) {
                Text(text = "Segunda Pantalla")
            }
        }
    }
}
@Composable
fun SecondScreen(onGoBack:() -> Unit) {
    Column(
        Modifier
            .background(Color.LightGray)
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(text = "Navegación con JetPackCompose")
        Spacer(Modifier.padding(10.dp))
        Text(text = "Bienvenido a la Segunda Pantalla")
        Spacer(Modifier.padding(10.dp))
        Row {
            ElevatedButton(onClick = onGoBack) {
                Text("Volver a la Anterior")
            }

//            ElevatedButton(onClick = {onNavSelected("home")}) {
//                Text(text = "Pantalla Home")
//            }
//            ElevatedButton(onClick = {onNavSelected("thirdScreen")}) {
//                Text(text = "Tercera Pantalla")
//            }
        }
    }
}
//@Composable
//fun SecondScreen(navController: NavHostController, onNavSelected: (String) -> Unit) {
//    Column(
//        Modifier.background(Color.LightGray).fillMaxSize().padding(16.dp),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center,
//    ) {
//        Text(text = "Navegación con JetPackCompose")
//        Spacer(Modifier.padding(10.dp))
//        Text(text = "Bienvenido a la Segunda Pantalla")
//        Spacer(Modifier.padding(10.dp))
//        Row {
//            ElevatedButton(onClick = {navController.popBackStack()}) { }
////            ElevatedButton(onClick = {onNavSelected("home")}) {
////                Text(text = "Pantalla Home")
////            }
//            ElevatedButton(onClick = {onNavSelected("thirdScreen")}) {
//                Text(text = "Tercera Pantalla")
//            }
//        }
//    }
//}
@Composable
fun MiHomeScreen(onNavSelected: (String) -> Unit){
    val diasSemana = stringArrayResource(id=R.array.dias)
    diasSemana.forEach { Log.d("diasSemana",it) }
    Log.d("diasSemana",diasSemana[1])
    val context = LocalContext.current
    Column (
        Modifier
            .background(Color.Gray)
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        ){
        Text(text="Navegación con JetPackCompose")
        Spacer(Modifier.padding(10.dp))
        Text(text= stringResource(R.string.bienvenido2))
        Spacer(Modifier.padding(40.dp))

        Row {
            ElevatedButton(onClick = {onNavSelected("secondScreen")}) {
                Text(text= stringResource(R.string.segunda_pantalla))

            }
            Spacer(Modifier.padding(5.dp))
            ElevatedButton(onClick = {onNavSelected("thirdScreen")}) {
                Text(text="Tercera Pantalla")
            }
        }
        Text(text= stringResource(R.string.txt_bienvenida))
        ElevatedButton(onClick = {openBrowser(context,"https://developer.android.com/")}) {
            Text("Abrir el Navegdor") }
        ElevatedButton(onClick = { openMap(context,"Badajoz") }) {
             Text("Abrir el Mapa")}
        ElevatedButton(onClick = { llamar(context,"666666666") }) {
            Text("Llamar al diablo") }
        ElevatedButton(onClick = { lanzarSegundaActividad(context) }) {
            Text("Lanzas otra actividad propia") }
        Text(stringResource(R.string.txt_adios))
    }
}
fun lanzarSegundaActividad(context: Context){
    val intent = Intent(context, SegundaActividad::class.java)
    context.startActivity(intent)
}

fun llamar(context: Context, numero: String) {
    val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel: $numero"))
    context.startActivity(intent)
}

fun openBrowser(context: Context, url: String) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    context.startActivity(intent)
}
fun openMap(context: Context, geo: String) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=$geo"))
    context.startActivity(intent)
}


@Preview(showBackground = true)
@Composable
fun MainAppPreview() {
    NavigationTheme {
        MainApp()
    }
}

@Preview(showBackground = true)
@Composable
fun SecondPreview() {
}