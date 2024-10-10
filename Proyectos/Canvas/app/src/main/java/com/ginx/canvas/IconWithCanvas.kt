package com.ginx.canvas

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Icon
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun IconWithCanvas() {
    var rotation by remember { mutableStateOf(0f) }
    LaunchedEffect(key1 = Unit) {
        while (true) {
            delay(16) // Update every 16ms (approximately 60fps)
            rotation += 2f // Adjust rotation speed as needed
        }
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Icon(
            imageVector = Icons.Filled.Done,
            contentDescription = "Android Icon",
            modifier = Modifier.size(12.dp)
        )

        Canvas(modifier = Modifier.fillMaxSize()) {
            // Red rectangle
            drawRect(
                color = Color.Red,
                topLeft = Offset(size.width / 4, size.height / 2),
                size = Size(size.width / 4, size.height / 4)
            )

            // Rotating blue rectangle
            withTransform({
                translate(size.width / 2, size.height / 2) // Move to center
                rotate(rotation) // Apply rotation
                translate(-size.width / 4, -size.height / 4) // Move back to top-left
            }) {
                drawRect(
                    color = Color.Blue,
                    topLeft = Offset(size.width / 4, size.height / 4),
                    size = Size(size.width / 4, size.height / 4)
                )
            }
        }
    }
}