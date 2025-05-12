package com.lmar.snakegame.presentation.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.Dp
import com.lmar.snakegame.domain.model.Direction

enum class SegmentType { HEAD, BODY, TAIL }

@Composable
fun SnakeSegment(
    x: Int,
    y: Int,
    direction: Direction,
    cellSize: Dp,
    type: SegmentType
) {
    val baseColor = when (type) {
        SegmentType.HEAD -> Color(0xFF00FF00)
        SegmentType.BODY -> Color(0xFF006400)
        SegmentType.TAIL -> Color(0xFF004d00)
    }

    val shape = when (type) {
        SegmentType.HEAD -> CircleShape
        else -> RectangleShape
    }

    Box(
        modifier = Modifier
            .offset(x = cellSize * x, y = cellSize * y)
            .size(cellSize)
            .clip(shape)
            .background(baseColor)
    ) {
        if (type == SegmentType.HEAD) {
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