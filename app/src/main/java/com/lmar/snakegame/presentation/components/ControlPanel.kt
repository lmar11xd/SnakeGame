package com.lmar.snakegame.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ControlPanel(
    modifier: Modifier = Modifier,
    onLeftClick: () -> Unit,
    onRightClick: () -> Unit,
    onDownClick: () -> Unit,
    onUpClick: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Button(
            onClick = { onUpClick() },
            shape = MaterialTheme.shapes.medium
        ) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowUp,
                contentDescription = "Arriba",
                tint = Color.White
            )
        }

        Row(
            Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = { onLeftClick() },
                shape = MaterialTheme.shapes.medium
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                    contentDescription = "Izquierda",
                    tint = Color.White
                )
            }

            Button(
                onClick = { onRightClick() },
                shape = MaterialTheme.shapes.medium
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = "Derecha",
                    tint = Color.White
                )
            }
        }

        Button(
            onClick = { onDownClick() },
            shape = MaterialTheme.shapes.medium
        ) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowDown,
                contentDescription = "Abajo",
                tint = Color.White
            )
        }

    }

}

@Composable
fun CircularButton(onClick: () -> Unit, modifier: Modifier = Modifier, content: @Composable () -> Unit) {
    Button(
        onClick = onClick,
        shape = CircleShape,
        modifier = modifier
            .size(60.dp),  // Tamaño del botón circular
        contentPadding = PaddingValues(0.dp)
    ) {
        content()
    }
}


@Preview(showBackground = true)
@Composable
private fun ControlPanelPreview() {
    ControlPanel(
        onLeftClick = {  },
        onRightClick = {  },
        onDownClick = {  },
        onUpClick = {  }
    )
}