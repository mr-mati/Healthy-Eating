package com.mati.onboarding_presentation.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mati.HealthyEating.R
import com.mati.onboarding_presentation.components.ActionButton
import com.mati.onboarding_presentation.components.WelcomeImage

@Composable
fun WelcomeScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Surface(
            modifier = Modifier
                .height(350.dp)
                .align(Alignment.TopCenter),
            color = MaterialTheme.colorScheme.primary,
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.image),
                    contentDescription = "",
                    contentScale = ContentScale.Crop
                )
                Text(
                    text = "Welcome!",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 36.sp,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .offset(y = (-64).dp),
                )
            }
        }
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(700.dp)
                .align(Alignment.BottomCenter),
            shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {

        }

        WelcomeImage(
            modifier = Modifier
                .align(Alignment.Center)
                .size(300.dp)
        )

        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(16.dp)
                .offset(y = (100).dp),
        ) {
            Text(
                text = stringResource(id = R.string.hello),
                textAlign = TextAlign.Start,
                color = Color.Black,
                style = TextStyle(
                    fontFamily = FontFamily.Default,
                    fontWeight = FontWeight.Black,
                    fontSize = 30.sp
                ),
            )
            Text(
                modifier = Modifier
                    .padding(top = 12.dp),
                text = stringResource(id = R.string.welcome_text),
                textAlign = TextAlign.Start,
                color = Color.Gray,
                style = TextStyle(
                    fontFamily = FontFamily.Default,
                    fontWeight = FontWeight.Normal,
                    fontSize = 22.sp
                ),
            )
        }

        ActionButton(
            text = stringResource(id = R.string.welcome_next),
            onClick = { },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
                .offset(y = (-64).dp),
        )

    }
}
