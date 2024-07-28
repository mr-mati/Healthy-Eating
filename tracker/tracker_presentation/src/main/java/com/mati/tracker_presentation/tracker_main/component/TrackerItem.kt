package com.mati.tracker_presentation.tracker_main.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
    maxData: Float,
    brush: Brush
) {

    val progress = (data / data) * 100f

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        CircularProgress(
            modifier = Modifier
                .size(60.dp)
                .padding(start = 16.dp),
            progress = progress - maxData,
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
            Text(
                text = String.format("%.0f", data),
                style = TextStyle(
                    //fontWeight = FontWeight.Bold,
                    fontSize = 22.sp,
                    textAlign = TextAlign.Center
                )
            )
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