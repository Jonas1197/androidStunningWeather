package com.example.stunningweather.presentation.main_screen.elements

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.stunningweather.ui.ColorConstants

@Preview
@Composable
fun ButtonWithSymbol(
    modifier: Modifier = Modifier
        .padding(32.dp)
        .size(65.dp),
    onClick: () -> Unit = {}
) {

    Button(
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = ColorConstants.DeepBlue
        ),
        shape = CircleShape,
        onClick = { onClick() }) {
        Icon(
            Icons.Rounded.Add,
            tint = Color.White,
            contentDescription = null
        )
    }
}