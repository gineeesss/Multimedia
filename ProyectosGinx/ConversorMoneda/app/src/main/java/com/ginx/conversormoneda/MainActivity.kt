import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
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
import kotlin.random.Random

@Composable
fun IconWithCanvasAndCircles() {
    var redRectangleColor by remember { mutableStateOf(Color.Red) }
    var blueRectangleOffset by remember { mutableStateOf(Offset.Zero) }
    var rotation by remember { mutableStateOf(0f) }

    LaunchedEffect(key1 = Unit) {
        while (true) {
            delay(16)
            rotation += 2f
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.Filled.AddCircle,
            contentDescription = "Android Icon",
            modifier = Modifier.size(64.dp)
        )

        Canvas(modifier = Modifier.fillMaxSize()) {
            drawRect(
                color = redRectangleColor,
                topLeft = Offset(size.width / 4, size.height / 2),
                size = Size(size.width / 4, size.height / 4)
            )

            withTransform({
                translate(blueRectangleOffset.x, blueRectangleOffset.y)
                rotate(rotation, pivot = Offset(size.width / 8, size.height / 8))
            }) {
                drawRect(
                    color = Color.Blue,
                    topLeft = Offset.Zero,
                    size = Size(size.width / 4, size.height / 4)
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            CircleButton(onClick = {
                redRectangleColor = Color(
                    Random.nextInt(256),
                    Random.nextInt(256),
                    Random.nextInt(256)
                )
            })
            CircleButton(onClick = {
                blueRectangleOffset = Offset(
                    Random.nextFloat() * (1 / 4),
                    Random.nextFloat() * (1 / 4)
                )
            })
            CircleButton(onClick = { /* Add your action here */ })
        }
    }
}

@Composable
fun CircleButton(onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(48.dp)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawCircle(color = Color.LightGray, radius = size.minDimension / 2)
        }
    }
}