package com.ginx.counter

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
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

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        Toast.makeText(this, "Hola Muriqi", Toast.LENGTH_SHORT).show();
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
    val simbolo = '+'
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Red),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "${counter}", modifier = modifier, fontSize = 128.sp)
        Spacer(Modifier.size(32.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {
                    counter++
                    Log.d("MYAPP", "Contador: ${counter}")
                },
                colors = ButtonColors(Pink80, Color.Green, Pink80, Color.Green),
                modifier = Modifier
                    .height(64.dp)
                    .width(128.dp)
            ) { Text(text = "+", fontSize = 64.sp) }
            Button(
                onClick = {
                    if (counter > 0) {
                        counter--
                    }
                    Log.d("MYAPP", "Contador: ${counter}")
                },
                colors = ButtonColors(Pink80, Color.Green, Pink80, Color.Green),
                modifier = Modifier
                    .height(64.dp)
                    .width(128.dp),
            ) { Text(text = "-", fontSize = 64.sp) }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CounterTheme {
        MyApp()
    }
}