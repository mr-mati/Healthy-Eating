package com.mati.onboarding_presentation.personalInformation.height

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import com.mati.onboarding_presentation.components.UnitTextField
import com.mati.onboarding_presentation.personalInformation.PersonalViewModel

@Composable
fun HeightItem(viewModel: PersonalViewModel = hiltViewModel()) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = stringResource(id = R.string.your_height),
            textAlign = TextAlign.Start,
            fontFamily = FontFamily(Font(R.font.eriega)),
            color = Color.Black,
            style = TextStyle(
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.Black,
                fontSize = 22.sp
            ),
        )
        UnitTextField(
            value = viewModel.height,
            onValueChange = viewModel::onHeightEnter,
            unit = stringResource(id = R.string.cm),
            textStyle = TextStyle(
                fontSize = 30.sp,
                color = MaterialTheme.colorScheme.primary,
            )
        )
    }
}