package com.mati.onboarding_presentation.activity

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.mati.core.domain.model.ActivityLevel
import com.mati.coreui.LocalSpacing
import com.mati.onboarding_presentation.components.SelectableButton

@Composable
fun ActivityItem(
    viewModel: ActivityViewModel = hiltViewModel()
) {
    val spacing = LocalSpacing.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.whats_your_activity_level),
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
                text = stringResource(id = R.string.low),
                isSelected = viewModel.selectedActivityLevel is ActivityLevel.Low,
                color = MaterialTheme.colorScheme.primary,
                selectedTextColor = Color.White,
                onClick = {
                    viewModel.onActivityLevelSelect(ActivityLevel.Low)
                },
                textStyle = MaterialTheme.typography.labelMedium.copy(
                    fontWeight = FontWeight.Normal
                )
            )
            Spacer(modifier = Modifier.width(spacing.spaceMedium))
            SelectableButton(
                text = stringResource(id = R.string.medium),
                isSelected = viewModel.selectedActivityLevel is ActivityLevel.Medium,
                color = MaterialTheme.colorScheme.primary,
                selectedTextColor = Color.White,
                onClick = {
                    viewModel.onActivityLevelSelect(ActivityLevel.Medium)
                },
                textStyle = MaterialTheme.typography.labelMedium.copy(
                    fontWeight = FontWeight.Normal
                )
            )
            Spacer(modifier = Modifier.width(spacing.spaceMedium))
            SelectableButton(
                text = stringResource(id = R.string.high),
                isSelected = viewModel.selectedActivityLevel is ActivityLevel.High,
                color = MaterialTheme.colorScheme.primary,
                selectedTextColor = Color.White,
                onClick = {
                    viewModel.onActivityLevelSelect(ActivityLevel.High)
                },
                textStyle = MaterialTheme.typography.labelMedium.copy(
                    fontWeight = FontWeight.Normal
                )
            )
        }
    }
}