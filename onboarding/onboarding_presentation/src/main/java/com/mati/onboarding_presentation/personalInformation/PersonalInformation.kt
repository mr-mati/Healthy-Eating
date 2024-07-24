package com.mati.onboarding_presentation.personalInformation

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
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
import androidx.hilt.navigation.compose.hiltViewModel
import com.mati.HealthyEating.R
import com.mati.core.domain.model.Gender
import com.mati.core.util.UiEvent
import com.mati.coreui.LocalSpacing
import com.mati.onboarding_presentation.age.AgeScreen
import com.mati.onboarding_presentation.components.ActionButton
import com.mati.onboarding_presentation.components.SelectableButton
import com.mati.onboarding_presentation.components.UnitTextField
import kotlinx.coroutines.delay

@Composable
fun PersonalInformation(
    onNextClick: () -> Unit,
    viewModel: GenderViewModel = hiltViewModel()
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

    val spacing = LocalSpacing.current
    val scrollState = rememberScrollState()

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.Success -> onNextClick()
                else -> Unit
            }
        }
    }

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
                    text = stringResource(id = R.string.personal_information),
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
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(750.dp)
                .align(Alignment.BottomCenter),
            shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .alpha(alpha)
                    .padding(top = 32.dp, bottom = 8.dp, start = 16.dp, end = 16.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = stringResource(id = R.string.gender),
                        textAlign = TextAlign.Start,
                        fontFamily = FontFamily(Font(R.font.eriega)),
                        color = Color.Black,
                        style = TextStyle(
                            fontFamily = FontFamily.Default,
                            fontWeight = FontWeight.Black,
                            fontSize = 22.sp
                        ),
                    )
                    Row {
                        SelectableButton(
                            text = stringResource(id = R.string.male),
                            isSelected = viewModel.selectedGender is Gender.Male,
                            color = MaterialTheme.colorScheme.primary,
                            selectedTextColor = Color.White,
                            onClick = {
                                viewModel.onGenderClick(Gender.Male)
                            },
                            textStyle = MaterialTheme.typography.labelLarge.copy(
                                fontWeight = FontWeight.Normal
                            )
                        )
                        Spacer(modifier = Modifier.width(spacing.spaceMedium))
                        SelectableButton(
                            text = stringResource(id = R.string.female),
                            isSelected = viewModel.selectedGender is Gender.Female,
                            color = MaterialTheme.colorScheme.primary,
                            selectedTextColor = Color.White,
                            onClick = {
                                viewModel.onGenderClick(Gender.Female)
                            },
                            textStyle = MaterialTheme.typography.labelLarge.copy(
                                fontWeight = FontWeight.Normal
                            )
                        )
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = stringResource(id = R.string.age),
                        textAlign = TextAlign.Start,
                        fontFamily = FontFamily(Font(R.font.eriega)),
                        color = Color.Black,
                        style = TextStyle(
                            fontFamily = FontFamily.Default,
                            fontWeight = FontWeight.Black,
                            fontSize = 22.sp
                        ),
                    )
                    AgeScreen()
                }

            }
        }

        ActionButton(
            text = stringResource(id = R.string.next),
            onClick = {},
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