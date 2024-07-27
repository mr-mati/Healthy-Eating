package com.mati.onboarding_presentation.fitnessGoals

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
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
import com.mati.onboarding_presentation.components.FoodSlider
import com.mati.onboarding_presentation.fitnessGoals.Goals.GoalsItem
import kotlinx.coroutines.delay

@Composable
fun FitnessGoals(
    onNextClick: () -> Unit,
) {

    var startAnimation by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(Unit) {
        delay(500)
        startAnimation = true
    }

    val alpha by animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(1000), label = ""
    )

    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .scrollable(scrollState, orientation = Orientation.Vertical)
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
                    text = stringResource(id = R.string.fitness_goals),
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
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 100.dp)
        ) {
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.BottomCenter)
                    .padding(top = 50.dp),
                shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .alpha(alpha)
                        .padding(bottom = 64.dp, start = 16.dp, end = 16.dp),
                    verticalArrangement = Arrangement.Center
                ) {

                    GoalsItem()

                    Spacer(modifier = Modifier.height(64.dp))

                    Text(
                        text = stringResource(id = R.string.what_are_your_nutrient_goals),
                        textAlign = TextAlign.Start,
                        fontFamily = FontFamily(Font(R.font.eriega)),
                        color = Color.Black,
                        style = TextStyle(
                            fontFamily = FontFamily.Default,
                            fontWeight = FontWeight.Black,
                            fontSize = 22.sp
                        ),
                    )

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = stringResource(id = R.string.carbs),
                            textAlign = TextAlign.Start,
                            fontFamily = FontFamily(Font(R.font.eriega)),
                            color = Color.Gray,
                            style = TextStyle(
                                fontFamily = FontFamily.Default,
                                fontWeight = FontWeight.Black,
                                fontSize = 18.sp
                            ),
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        FoodSlider()
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = stringResource(id = R.string.proteins),
                            textAlign = TextAlign.Start,
                            fontFamily = FontFamily(Font(R.font.eriega)),
                            color = Color.Gray,
                            style = TextStyle(
                                fontFamily = FontFamily.Default,
                                fontWeight = FontWeight.Black,
                                fontSize = 18.sp
                            ),
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        FoodSlider()
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = stringResource(id = R.string.fats),
                            textAlign = TextAlign.Start,
                            fontFamily = FontFamily(Font(R.font.eriega)),
                            color = Color.Gray,
                            style = TextStyle(
                                fontFamily = FontFamily.Default,
                                fontWeight = FontWeight.Black,
                                fontSize = 18.sp
                            ),
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        FoodSlider()
                    }

                }
            }

            Box(
                modifier = Modifier
                    .size(100.dp)
                    .align(Alignment.TopCenter)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.onPrimary)
            ) {
                Image(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(12.dp),
                    contentScale = ContentScale.FillBounds,
                    painter = painterResource(id = R.drawable.goals),
                    colorFilter = ColorFilter.tint(Color.White),
                    contentDescription = ""
                )
            }
        }

        ActionButton(
            text = stringResource(id = R.string.complete),
            onClick = { onNextClick() },
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