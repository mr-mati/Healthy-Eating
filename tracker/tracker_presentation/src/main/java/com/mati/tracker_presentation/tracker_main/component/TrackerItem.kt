package com.mati.tracker_presentation.tracker_main.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mati.tracker_presentation.component.CircularProgress

@Composable
fun TrackerItem(
    title: String,
    data: Float,
    maxData: Float
) {

    val brush = if (data > maxData) {
        Brush.horizontalGradient(listOf(Color(0xFFE45757), Color(0xFFEF9A9A)))
    } else {
        Brush.horizontalGradient(listOf(Color(0xFF71C4C4), Color(0xFFEF9A9A)))
    }

    val color = if (data > maxData) {
        Color(0xFFEF9A9A)
    } else if (data == maxData) {
        Color(0xFF71C4C4)
    } else {
        Color.Black
    }

    val progress = (data / maxData) * 100
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        CircularProgress(
            modifier = Modifier
                .size(60.dp)
                .padding(start = 16.dp),
            progress = progress,
            calories = progress,
            totalCalories = data.toFloat(),
            color = Color(0xFFDBDBDB),
            selectColor = brush,
            visibleNumber = false
        )

        Column(
            modifier = Modifier
                .padding(start = 8.dp, end = 8.dp),
            verticalArrangement = Arrangement.Center,
        ) {
            Row(
                verticalAlignment = Alignment.Bottom
            ) {
                Text(
                    text = String.format("%.0f", data),
                    style = TextStyle(
                        //fontWeight = FontWeight.Bold,
                        color = color,
                        fontSize = 22.sp,
                        textAlign = TextAlign.Center
                    )
                )
                Text(
                    text = String.format("/%.0f", maxData),
                    style = TextStyle(
                        color = Color.LightGray,
                        fontWeight = FontWeight.Bold,
                        fontSize = 8.sp,
                        textAlign = TextAlign.Center
                    )
                )
            }
            Text(
                text = "$title",
                style = TextStyle(
                    color = Color.LightGray,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center
                )
            )
        }
    }

}