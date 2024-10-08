package com.mati.onboarding_presentation.welcome

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mati.HealthyEating.R
import com.mati.onboarding_presentation.components.ActionButton
import com.mati.onboarding_presentation.components.WelcomeImage
import kotlinx.coroutines.delay

@Composable
fun WelcomeScreen(
    onClick: () -> Unit
) {

    var startAnimation by remember {
        mutableStateOf(false)
    }

    var startAnimationOffsetY by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        delay(1300)
        startAnimationOffsetY = true
    }

    val offsetY by animateDpAsState(
        targetValue = if (startAnimationOffsetY) 0.dp else (1000).dp,
        animationSpec = tween(1000)
    )

    LaunchedEffect(Unit) {
        delay(2000)
        startAnimation = true
    }

    val alpha by animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(1000), label = ""
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary)
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
                Text(
                    text = "Welcome!",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily(Font(R.font.lufga_bold)),
                    fontSize = 36.sp,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .offset(y = (-120).dp),
                )
            }
        }

        Image(
            painter = painterResource(id = R.drawable.image), contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 100.dp)
        ) {
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.BottomCenter)
                    .offset(y = offsetY)
                    .padding(top = 160.dp),
                shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Box(modifier = Modifier.fillMaxSize()) {
                    Column(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(16.dp)
                            .offset(y = (100).dp),
                    ) {
                        Text(
                            modifier = Modifier
                                .alpha(alpha),
                            text = stringResource(id = R.string.hello),
                            textAlign = TextAlign.Start,
                            fontFamily = FontFamily(Font(R.font.eriega)),
                            color = Color.Black,
                            style = TextStyle(
                                fontFamily = FontFamily.Default,
                                fontWeight = FontWeight.Black,
                                fontSize = 30.sp
                            ),
                        )
                        Text(
                            modifier = Modifier
                                .alpha(alpha)
                                .padding(top = 12.dp),
                            text = stringResource(id = R.string.welcome_text),
                            textAlign = TextAlign.Start,
                            fontFamily = FontFamily(Font(R.font.eriega)),
                            color = Color.Gray,
                            style = TextStyle(
                                fontFamily = FontFamily.Default,
                                fontWeight = FontWeight.Normal,
                                fontSize = 18.sp
                            ),
                        )
                    }

                    ActionButton(
                        text = stringResource(id = R.string.welcome_next),
                        onClick = { onClick() },
                        textStyle = TextStyle(
                            fontFamily = FontFamily(Font(R.font.eriega)),
                        ),
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(16.dp)
                            .offset(y = (-26).dp)
                            .alpha(alpha),
                    )
                }
            }

            WelcomeImage(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .size(300.dp)
            )
        }
    }
}
