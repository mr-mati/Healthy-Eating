package com.mati.onboarding_presentation.components

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mati.HealthyEating.R
import com.mati.onboarding_presentation.fitnessGoals.FitnessGoalViewModel


@SuppressLint("DefaultLocale")
@Composable
fun FoodSlider(
    value: Float,
    onValueChange: (Float) -> Unit
) {
    val foodImage: Painter = painterResource(id = R.drawable.target)

    var startAnimation by remember {
        mutableStateOf(true)
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .padding(horizontal = 16.dp)
    ) {
        Slider(
            value = value,
            onValueChange = onValueChange,
            valueRange = 0f..100f,
            colors = SliderDefaults.colors(
                thumbColor = Color.Transparent,
                activeTrackColor = MaterialTheme.colorScheme.primary,
                inactiveTrackColor = Color.LightGray
            ),
            modifier = Modifier
                .fillMaxWidth()
        )

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .offset(x = (value - 50).dp * 3)
        ) {
            if (startAnimation) {
                Image(
                    painter = foodImage,
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier.size(38.dp)
                )
            }
        }

        Text(
            text = String.format("%.0f", value) + "%",
            fontSize = 16.sp,
            modifier = Modifier
                .offset(x = (value - 50).dp * 3, y = (-30).dp)
        )
    }
}