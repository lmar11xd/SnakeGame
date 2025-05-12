package com.lmar.snakegame.presentation.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.lmar.snakegame.domain.model.Direction

enum class VisualSegmentType {
    HEAD, STRAIGHT, TURN, TAIL
}

@Composable
fun CurvedSnakeSegment(
    x: Int,
    y: Int,
    cellSize: Dp,
    direction: Direction,
    type: VisualSegmentType,
    cornerRadius: Dp = 6.dp
) {
    val color = when (type) {
        VisualSegmentType.HEAD -> Color(0xFF00FF00)
        VisualSegmentType.TAIL -> Color(0xFF004d00)
        VisualSegmentType.TURN -> Color(0xFF008000)
        VisualSegmentType.STRAIGHT -> Color(0xFF006400)
    }

    Box(
        modifier = Modifier
            .offset(x = cellSize * x, y = cellSize * y)
            .size(cellSize)
            .clip(RoundedCornerShape(cornerRadius))
            .background(color)
    ) {
        if (type == VisualSegmentType.HEAD) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                val eyeRadius = size.minDimension * 0.1f
                val offset = size.minDimension * 0.2f

                val (leftEye, rightEye) = when (direction) {
                    Direction.UP -> Pair(
                        Offset(offset, offset),
                        Offset(size.width - offset, offset)
                    )
                    Direction.DOWN -> Pair(
                        Offset(offset, size.height - offset),
                        Offset(size.width - offset, size.height - offset)
                    )
                    Direction.LEFT -> Pair(
                        Offset(offset, offset),
                        Offset(offset, size.height - offset)
                    )
                    Direction.RIGHT -> Pair(
                        Offset(size.width - offset, offset),
                        Offset(size.width - offset, size.height - offset)
                    )
                }

                drawCircle(Color.DarkGray, eyeRadius, center = leftEye)
                drawCircle(Color.DarkGray, eyeRadius, center = rightEye)
            }
        }
    }
}