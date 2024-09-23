package com.ginx.counter

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.RadioButtonColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ginx.counter.ui.theme.CounterTheme
import com.ginx.counter.ui.theme.Pink80
import com.ginx.counter.ui.theme.PurpleGrey80
import kotlinx.coroutines.flow.combineTransform
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        Toast.makeText(this, "Counter", Toast.LENGTH_SHORT).show();
        setContent {
            CounterTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MyApp(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@SuppressLint("UnrememberedMutableState")
@Composable
fun MyApp(modifier: Modifier = Modifier) {
    var counter by rememberSaveable { mutableStateOf(0) }
    var counter2 by rememberSaveable { mutableStateOf(0) }
    var counterSize by rememberSaveable { mutableStateOf(32) }
    Column(
        modifier = Modifier.fillMaxSize()
        .background(randomColor()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    )
    {
        Row(horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically) {
            CounterWidget(
                counterSize,
                counter,
                onIncrease = { counter++
                    counterSize= aumentarSize(counterSize) },
                onDecrease = { counter = decrementarContador(counter)})
            /*CounterWidget(
                counter2,
                onIncrease = { counter2++ },
                onDecrease = { counter2-- })*/
        }
    }
}



@Composable
fun CounterWidget(
    counterSize: Int,
    counter: Int,
    onIncrease: () -> Unit,
    onDecrease: () -> Unit
): Int {
    Column(
        modifier = Modifier.fillMaxSize()
            .background(randomColor()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "${counter}", modifier = Modifier
                .padding(16.dp)
                .clickable
                //.combinedClickable(onClick = onIncrease, onLongClick = onDecrease)
                { onIncrease() }, fontSize = counterSize.sp

        )
//        Spacer(Modifier.size(32.dp))
//        )
        Row (horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically){
            Button(
                onClick = { onIncrease()},
                colors = ButtonColors(Pink80, Color.Green, Pink80, Color.Green),
                modifier = Modifier
                    .height(32.dp)
                    .width(64.dp)
            ) { Text(text = "+", fontSize = 32.sp) }
            Button(
                onClick = { if (counter > 0) onDecrease() },
                colors = ButtonColors(Pink80, Color.Green, Pink80, Color.Green),
                modifier = Modifier
                    .height(32.dp)
                    .width(64.dp)
            ) { Text(text = "-", fontSize = 32.sp) }
        }
    }
    return counter
}

fun aumentarSize(counterSize: Int ):Int{
    return if (counterSize%3==0) return counterSize+10   else return counterSize
}
fun decrementarContador(counter: Int): Int {
    return if (counter < 0) return 0 else return counter-1
}
//@Composable
//fun Display2(modifier: Modifier = Modifier) {
//    var counter by rememberSaveable { mutableStateOf(0) }
//    var counter2 by rememberSaveable { mutableStateOf(0) }
//
//    Column(
//        modifier = Modifier.fillMaxSize(),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center
//    )
//    {
//        Row(horizontalArrangement = Arrangement.SpaceBetween,
//            verticalAlignment = Alignment.CenterVertically) {
//            CounterWidget(
//                counter,
//                onDecrease = { counter++ },
//                onDecrease = { counter = decrementarContador(counter)}
//            )
//
//        }
//    }
//}
fun randomColor(): Color{
    return Color(
        red = Random.nextFloat(),
        green = Random.nextFloat(),
        blue = Random.nextFloat(),
        alpha = Random.nextFloat()
    )
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CounterTheme {
        MyApp()
    }
}