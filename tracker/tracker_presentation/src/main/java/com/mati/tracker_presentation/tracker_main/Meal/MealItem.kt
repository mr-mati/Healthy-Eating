package com.mati.tracker_presentation.tracker_main.Meal

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mati.HealthyEating.R
import com.mati.coreui.LocalSpacing
import com.mati.tracker_presentation.tracker_main.Meal.component.MealTrackerItem
import com.mati.tracker_presentation.tracker_main.Meals
import kotlinx.coroutines.delay

@Composable
fun MealItem(
    meal: Meals,
    title: String,
    icon: Int,
    crabs: Int,
    protein: Int,
    fat: Int,
    onToggleClick: () -> Unit,
    content: @Composable (String) -> Unit,
) {

    val spacing = LocalSpacing.current
    val context = LocalContext.current

    var startAnimation by remember {
        mutableStateOf(false)
    }

    val horizontalScrollState = rememberScrollState()

    LaunchedEffect(Unit) {
        delay(100)
        startAnimation = true
    }

    val alpha by animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(500), label = ""
    )
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 100.dp)
            .padding(16.dp)
            .alpha(alpha),
        onClick = { onToggleClick() },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5))
    ) {
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Image(
                    modifier = Modifier.size(48.dp),
                    painter = painterResource(id = icon),
                    contentDescription = null
                )
                Text(
                    modifier = Modifier.padding(16.dp),
                    text = "$title",
                    fontWeight = FontWeight.Bold,
                    style = TextStyle(
                        fontSize = 16.sp, fontFamily = FontFamily(Font(R.font.eriega))
                    )
                )
                Row(
                    modifier = Modifier
                        .horizontalScroll(horizontalScrollState)
                        .padding(end = 8.dp)
                        .fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End
                ) {
                    MealTrackerItem("crabs", crabs.toFloat())
                    MealTrackerItem("protein", protein.toFloat())
                    MealTrackerItem("fat", fat.toFloat())
                }
            }
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
            AnimatedVisibility(
                modifier = Modifier
                    .padding(bottom = 16.dp),
                visible = meal.isExpanded
            ) {
                content(meal.mealType.name)
            }
        }
    }
}