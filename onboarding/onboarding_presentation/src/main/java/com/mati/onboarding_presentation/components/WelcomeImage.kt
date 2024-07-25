package com.mati.onboarding_presentation.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.mati.HealthyEating.R
import kotlinx.coroutines.delay

@Composable
fun WelcomeImage(modifier: Modifier = Modifier) {

    var startAnimationOffsetY by remember { mutableStateOf(false) }
    var startAnimationAlpha by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(100)
        startAnimationAlpha = true
    }

    LaunchedEffect(Unit) {
        delay(500)
        startAnimationOffsetY = true
    }

    LaunchedEffect(Unit) {
        delay(1500)
        startAnimationOffsetY = false
    }

    val offsetY by animateDpAsState(
        targetValue = if (startAnimationOffsetY) 100.dp else (0).dp,
        animationSpec = tween(1000)
    )

    val alpha by animateFloatAsState(
        targetValue = if (startAnimationAlpha) 1f else 0f,
        animationSpec = tween(500)
    )

    Image(
        modifier = modifier
            .alpha(alpha)
            .offset(y = offsetY)
            .offset(y = offsetY),
        painter = painterResource(id = R.drawable.welcome),
        contentDescription = null,
    )

}