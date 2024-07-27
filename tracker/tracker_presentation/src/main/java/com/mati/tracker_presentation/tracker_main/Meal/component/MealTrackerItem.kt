package com.mati.tracker_presentation.tracker_main.Meal.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MealTrackerItem(
    title: String,
    data: Float,
) {
    Column(
        modifier = Modifier
            .padding(start = 8.dp, end = 8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "$data g",
            style = TextStyle(
                //fontWeight = FontWeight.Bold,
                fontSize = 12.sp
            )
        )
        Text(
            text = "$title",
            style = TextStyle(
                color = Color.LightGray,
                fontWeight = FontWeight.Bold,
                fontSize = 10.sp,
                textAlign = TextAlign.Start
            )
        )
    }

}