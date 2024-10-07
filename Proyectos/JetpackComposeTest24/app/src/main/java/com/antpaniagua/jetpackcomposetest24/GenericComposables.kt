package com.antpaniagua.jetpackcomposetest24

import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * GenericComposables
 *
 * Una pequeña biblioteca de funciones de uso común en la clase principal
 */
@Composable
fun TextAppTitle() {
    Text(
        stringResource(R.string.app_title),
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.titleLarge,
        modifier = Modifier.height(64.dp)
    )
}

@Composable
fun TextBoldSimple(message: String, fontSize: Float) {
    Text(
        text = message,
        fontSize = fontSize.sp,
        fontWeight = FontWeight.Bold
    )
}