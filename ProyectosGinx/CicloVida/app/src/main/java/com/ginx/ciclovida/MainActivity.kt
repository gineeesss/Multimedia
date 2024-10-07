package com.ginx.ciclovida

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.ginx.ciclovida.ui.theme.CicloVidaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("CICLO","La APP se ha creado")
        enableEdgeToEdge()
        setContent {
            CicloVidaTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
    override fun onStart(){
        super.onStart()
        Toast.makeText(applicationContext,"Comenzamos!!",Toast.LENGTH_SHORT).show()
        Log.d("TAG","La APP ha comenzado")
    }
    override fun onStop(){
        super.onStop()
        Log.d("TAG","La APP ha parado")
    }
    override fun onDestroy(){
        super.onDestroy()
        Log.d("TAG","La APP se ha destruido")
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CicloVidaTheme {
        Greeting("Android")
    }
}