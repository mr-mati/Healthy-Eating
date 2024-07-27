package com.mati.tracker_presentation.component

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CircularProgress(
    modifier: Modifier = Modifier,
    progress: Float,
    totalCalories: Float,
    color: Color,
    selectColor: Brush,
    visibleNumber: Boolean
) {

    val animatedProgress = remember { Animatable(0f) }

    LaunchedEffect(progress) {
        animatedProgress.animateTo(
            targetValue = progress / 100f,
            animationSpec = tween(durationMillis = 1000)
        )
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .size(200.dp)
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val strokeWidth = 20.dp.toPx()
            val radius = (size.minDimension - strokeWidth) / 2
            val topLeftOffset = Offset(
                (size.width - radius * 2) / 2,
                (size.height - radius * 2) / 2
            )
            drawArc(
                color = color,
                startAngle = -90f,
                sweepAngle = 360f,
                useCenter = false,
                topLeft = topLeftOffset,
                size = Size(radius * 2, radius * 2),
                style = Stroke(strokeWidth)
            )
            drawArc(
                startAngle = -90f,
                sweepAngle = 360 * animatedProgress.value,
                useCenter = false,
                topLeft = topLeftOffset,
                size = Size(radius * 2, radius * 2),
                style = Stroke(strokeWidth),
                brush = selectColor
            )
        }
        if (visibleNumber) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = String.format("%.2f kcal", totalCalories * animatedProgress.value * 100),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.onBackground
                )
                Spacer(modifier = Modifier.height(4.dp))
            }
        }
    }
}