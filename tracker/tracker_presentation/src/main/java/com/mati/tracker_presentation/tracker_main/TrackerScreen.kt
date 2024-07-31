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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mati.HealthyEating.R
import com.mati.coreui.LocalSpacing
import com.mati.tracker_presentation.component.CircularProgress
import com.mati.tracker_presentation.component.DaySelector
import com.mati.tracker_presentation.tracker_main.Meal.MealItem
import com.mati.tracker_presentation.tracker_main.component.FoodItem
import com.mati.tracker_presentation.tracker_main.component.TrackerItem

@Composable
fun TrackerScreen(
    onNavigateToSearch: (Int, Int, Int) -> Unit,
    viewModel: TrackerViewModel = hiltViewModel()
) {

    val response = viewModel.state
    val context = LocalContext.current
    val spacing = LocalSpacing.current

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
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
                val brush = if (response.totalCalories.toFloat() > response.caloriesGoal.toFloat()) {
                    Brush.horizontalGradient(listOf(Color(0xFFE45757), Color(0xFFEF9A9A)))
                } else {
                    Brush.horizontalGradient(listOf(Color(0xFF71C4C4), Color(0xFFEF9A9A)))
                }
                if (response.caloriesGoal != 0) {
                    val progress =
                        (response.totalCalories.toDouble() / response.caloriesGoal.toDouble()) * 100
                    CircularProgress(
                        modifier = Modifier
                            .size(180.dp)
                            .padding(start = 16.dp),
                        progress = progress.toFloat(),
                        calories = response.totalCalories.toFloat(),
                        totalCalories = response.caloriesGoal.toFloat(),
                        color = Color(0xFFDBDBDB),
                        selectColor = brush,
                        visibleNumber = true
                    )
                    Column(
                        verticalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .padding(end = 32.dp)
                    ) {
                        TrackerItem(
                            "carbs",
                            response.totalCarbs.toFloat(),
                            response.carbsGoal.toFloat() / 100f,
                        )
                        TrackerItem(
                            "protein",
                            response.totalProtein.toFloat(),
                            response.proteinGoal.toFloat() / 100f,
                        )
                        TrackerItem(
                            "fat",
                            response.totalFat.toFloat(),
                            response.fatGoal.toFloat() / 100f
                        )
                    }

                }
            }
        }

        DaySelector(
            date = response.date,
            onPreviousDayClick = {
                viewModel.onEvent(TrackerEvent.OnPreviousDayClick)
            },
            onNextDayClick = {
                viewModel.onEvent(TrackerEvent.OnNextDayClick)
            },
        )

        Card(
            modifier = Modifier
                .padding(top = 8.dp, bottom = 16.dp, start = 16.dp, end = 16.dp)
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

                Column(
                    modifier = Modifier
                        .padding(bottom = 4.dp, top = 8.dp)
                ) {
                    repeat(response.meals.size) { index ->
                        MealItem(
                            response.meals[index],
                            response.meals[index].name.asString(context),
                            response.meals[index].drawableRes,
                            response.meals[index].carbs,
                            response.meals[index].protein,
                            response.meals[index].fat,
                            onToggleClick = {
                                viewModel.onEvent(TrackerEvent.OnToggleMealClick(response.meals[index]))
                            },
                            content = {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = spacing.spaceSmall)
                                ) {
                                    Text(
                                        modifier = Modifier
                                            .padding(8.dp),
                                        text = "Your Foods",
                                        fontWeight = FontWeight.Bold,
                                        style = TextStyle(
                                            fontSize = 18.sp,
                                            fontFamily = FontFamily(Font(R.font.eriega))
                                        )
                                    )
                                    response.trackedFoods.forEach { food ->
                                        if (food.mealType.name == it) {
                                            FoodItem(
                                                trackedFood = food,
                                                onDeleteClick = {
                                                    viewModel.onEvent(
                                                        TrackerEvent
                                                            .OnDeleteTrackedFoodClick(food)
                                                    )
                                                }
                                            )
                                        }
                                    }
                                }
                            }
                        )
                    }
                }
            }
        }
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 32.dp, end = 32.dp, bottom = 32.dp),
            onClick = {
                onNavigateToSearch(
                    response.date.dayOfMonth,
                    response.date.monthValue,
                    response.date.year
                )
            },
        ) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "")
            Text(
                modifier = Modifier
                    .padding(top = 8.dp, bottom = 8.dp),
                text = "Add Meals",
                textAlign = TextAlign.Center
            )
        }
    }

}