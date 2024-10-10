package com.antpaniagua.androides

import android.os.Bundle
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.antpaniagua.androides.ui.theme.AndroidesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidesTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyApp()
                }
            }
        }
    }
}

@Composable
fun MyApp(modifier: Modifier = Modifier) {
    AndroideScreen()
}


@Composable
fun AndroideScreen() {
    Column (horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(12.dp)){
        Text(
            text = "Androides de colores",
            fontSize=32.sp,
        )
        Row (horizontalArrangement = Arrangement.SpaceBetween){
            Image(painterResource(R.drawable.androidretro), contentDescription = "Androide Retro",Modifier.size(100.dp))
            Image(painterResource(R.drawable.androidretro), contentDescription = "Androide Retro",Modifier.size(100.dp))
            Image(painterResource(R.drawable.androidretro), contentDescription = "Androide Retro",Modifier.size(100.dp))
            Image(painterResource(R.drawable.androidretro), contentDescription = "Androide Retro",Modifier.size(100.dp))

        }
        Spacer(Modifier.padding(24.dp))
        Image(painter = CambiarImagen(""), contentDescription = "Androide Verde", Modifier.size(200.dp))
        Row{
            Text(text="Rojo",Modifier.padding(6.dp,12.dp).align(Alignment.CenterVertically))
            CambiarImagen(false, onCambio = {true})
        }
        Row{
            Text(text="Azul",Modifier.padding(6.dp,12.dp).align(Alignment.CenterVertically))
            CambiarImagen(false, onCambio = {true})
        }
        Row{
            Text(text="Verde",Modifier.padding(6.dp,12.dp).align(Alignment.CenterVertically))
            Switch(false, onCheckedChange = {CambiarImagen("green")})
        }
        //Switch(checked=state, onCheckedChange = onCambio)

    }
}
@Composable
fun CambiarImagen(state: Boolean, onCambio: (Boolean) -> Unit){
    Switch(checked=state,onCheckedChange={})

}
@Composable
fun CambiarImagen(color: String): Painter{
    when(color){
        "red" ->  return painterResource(R.drawable.androidred)
        "blue" ->  return painterResource(R.drawable.androidblue)
        "green" ->  return painterResource(R.drawable.androidgreen)
        else -> return painterResource(R.drawable.androidgreen)
    }

}


/*@Composable
fun AndroideScreen() {
    Column (horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(12.dp)){
        Text(
            text = "Androides de colores",
            fontSize=32.sp,
        )
        Row (horizontalArrangement = Arrangement.SpaceBetween){
            Image(painterResource(R.drawable.androidretro), contentDescription = "Androide Retro",Modifier.size(100.dp))
            Image(painterResource(R.drawable.androidretro), contentDescription = "Androide Retro",Modifier.size(100.dp))
            Image(painterResource(R.drawable.androidretro), contentDescription = "Androide Retro",Modifier.size(100.dp))
            Image(painterResource(R.drawable.androidretro), contentDescription = "Androide Retro",Modifier.size(100.dp))

        }
        Spacer(Modifier.padding(24.dp))
        Image(painter = painterResource(R.drawable.androidgreen), contentDescription = "Androide Verde", Modifier.size(200.dp))
        Row{
            Text(text="Rojo",Modifier.padding(6.dp,12.dp).align(Alignment.CenterVertically))
            CambiarImagen(false, onCambio = { (painterResource(R.drawable.androidred))})
        }
        Row{
            Text(text="Azul",Modifier.padding(6.dp,12.dp).align(Alignment.CenterVertically))
            CambiarImagen(false, onCambio = {painterResource(R.drawable.androidgreen)})
        }
        Row{
            Text(text="Verde",Modifier.padding(6.dp,12.dp).align(Alignment.CenterVertically))
            CambiarImagen(false, onCambio = {painterResource(R.drawable.androidgreen)})
        }
        //Switch(checked=state, onCheckedChange = onCambio)

    }
}
@Composable
fun CambiarImagen(state: Boolean, onCambio: @Composable (Boolean) -> Painter): Painter {
    Switch(checked=state,onCheckedChange={})
    return painterResource(R.drawable.androidgreen)
}
*/

@Preview(showBackground = true)
@Composable
fun MyAppPreview() {
    AndroidesTheme {
        MyApp()
    }
}
/**

 * Tareas a resolver
 *
 * 1. Refactoriza la aplicación para que desde MyApp se llame a una función llamada AndroideScreen que muestre la columna y el texto
 *
 * 2. En AndroideScreen genera un espacio con cuatro columas donde mostremos en cada una la imagen androideretro contenida en res, con un tamaño de 100.dp
 *
 * 3. Para distribuir el espacio en la fila prueba horizontalArrangement con los valores Arrangement.SpaceBetween o Arrangement.SpaceEvenly. Observa la diferencia
 *
 * 4. Inserta un espaciador (Spacer) de 24.dp
 *
 * 5. Inserta nuevamente la imagen del androide con un tamaño de 200.dp
 *
 * 6. A continuación inserta un Texto que ponga la palabra Rojo, seguido de un Switch apagado.
 * (Switch sólo requiere el valor checked booleano y el valor onCheckedChange  con la acción a realizar.
 *
 * 7. Repite lo mismo con otros dos switchs con la palabra azul y verde. Los tres valores comienzan en false.
 *
 * 8. Modifica la aplicación para que cada vez que se pulse un switch, el cambio sea visible en pantalla,
 * empleando para ello las expresiones lambda y la elevación de estado que hemos visto. Deberás, por tanto,
 * crear una pequeña función para el switch que mediante un callback devuelva el valor booleando del switch
 *
 * 9. Modifica la aplicación para que sólo se emplee una función de Switch, en el caso de que hayas realizado 3.
 * Asegúrate de que es una función sin estado (que actúa mediante elevación).
 *
 * 10. Modifica la aplicación para que cuando se seleccione un color, la imagen del Androide central se
 * reemplace por la del color seleccionado, empleando los patrones de estados y elevación.
 * Recuerda que los recursos de imágenes (como R.drawable.androideretro) son en realidad numeros enteros.
 *
 * FINAL: Añade todas las mejoras que consideres, tanto de apariencia como en el código.
 */