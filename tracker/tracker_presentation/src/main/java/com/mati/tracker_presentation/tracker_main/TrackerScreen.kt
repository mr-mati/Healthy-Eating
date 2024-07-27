package com.mati.tracker_presentation.tracker_main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mati.HealthyEating.R
import com.mati.tracker_presentation.component.CircularProgress
import com.mati.tracker_presentation.tracker_main.Meal.MealItem
import com.mati.tracker_presentation.tracker_main.component.TrackerItem

@Composable
fun TrackerScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 32.dp)
            .background(Color(0xFFF5F5F5))
    ) {

        Card(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .height(250.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {

            Text(
                modifier = Modifier
                    .padding(16.dp),
                text = "Your Goals",
                fontWeight = FontWeight.Bold,
                style = TextStyle(
                    fontSize = 22.sp,
                    fontFamily = FontFamily(Font(R.font.eriega))
                )
            )

            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 32.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                val brush = Brush.horizontalGradient(listOf(Color(0xFF71C4C4), Color(0xFFEF9A9A)))
                CircularProgress(
                    modifier = Modifier
                        .size(180.dp)
                        .padding(start = 16.dp),
                    progress = 40f,
                    totalCalories = 100f,
                    color = Color(0xFFDBDBDB),
                    selectColor = brush,
                    visibleNumber = true
                )

                Column(
                    verticalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .padding(end = 32.dp)
                ) {
                    TrackerItem("crabs", 10f, 12f, brush)
                    TrackerItem("protein", 80f, 400f, brush)
                    TrackerItem("fat", 40f, 100f, brush)
                }

            }

        }

        Text(
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.CenterHorizontally),
            text = "Today",
            fontWeight = FontWeight.Bold,
            style = TextStyle(
                fontSize = 22.sp,
                fontFamily = FontFamily(Font(R.font.eriega))
            )
        )

        Card(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceAround
            ) {
                MealItem("Breakfast", R.drawable.ic_breakfast)
                MealItem("Lunch", R.drawable.ic_burger)
                MealItem("Dinner", R.drawable.ic_dinner)
                MealItem("Snacks", R.drawable.ic_snack)
            }
        }

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 32.dp, end = 32.dp),
            onClick = { }) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "")
            Text(
                modifier = Modifier
                    .padding(top = 8.dp, bottom = 8.dp),
                text = "Add Breakfast",
                textAlign = TextAlign.Center
            )
        }

    }

}