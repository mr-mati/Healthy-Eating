package com.mati.onboarding_presentation.welcome

import androidx.compose.foundation.Image
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.mati.coreui.primary
import com.mati.HealthyEating.R

@Composable
fun WelcomeScreen(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier,
        color = primary
    ) {
        Image(
            painter = painterResource(id = R.drawable.image),
            contentDescription = "",
            contentScale = ContentScale.FillBounds
        )
    }
    Text("test")
}