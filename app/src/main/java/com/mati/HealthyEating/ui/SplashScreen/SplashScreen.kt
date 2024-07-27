package com.mati.HealthyEating.ui.SplashScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mati.HealthyEating.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(onNextPage: () -> Unit) {

    LaunchedEffect(Unit) {
        delay(3000)
        onNextPage()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary)
    ) {

        Image(
            painter = painterResource(id = R.drawable.image), contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )


        Text(
            text = "Healthy Eating",
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .align(Alignment.Center),
            style = TextStyle(
                color = Color.White,
                fontSize = 32.sp,
                textAlign = TextAlign.Center,
                fontFamily = FontFamily(Font(R.font.lufga_bold)),
            )
        )

        Text(
            text = "Mr Mati",
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .offset(y = -30.dp)
                .align(Alignment.BottomCenter),
            style = TextStyle(
                color = Color.Gray,
                fontSize = 26.sp,
                textAlign = TextAlign.Center,
                fontFamily = FontFamily(Font(R.font.eriega)),
            )
        )

    }

}