package com.mati.onboarding_presentation.gemder

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.mati.core.domain.model.Gender
import com.mati.core.util.UiEvent
import com.mati.coreui.LocalSpacing
import com.mati.onboarding_presentation.components.SelectableButton
import com.mati.onboarding_presentation.components.UnitTextField

@Composable
fun GenderItem(
    viewModel: GenderViewModel = hiltViewModel()
) {
    val spacing = LocalSpacing.current

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
                textStyle = MaterialTheme.typography.labelMedium.copy(
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
                textStyle = MaterialTheme.typography.labelMedium.copy(
                    fontWeight = FontWeight.Normal
                )
            )
        }
    }
}