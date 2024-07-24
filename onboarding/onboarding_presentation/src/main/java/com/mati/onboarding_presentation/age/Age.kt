package com.mati.onboarding_presentation.age

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mati.HealthyEating.R
import com.mati.onboarding_presentation.components.UnitTextField

@Composable
fun AgeScreen(
    viewModel: AgeViewModel = hiltViewModel()
) {

    UnitTextField(
        value = viewModel.age,
        onValueChange = viewModel::onAgeEnter,
        unit = stringResource(id = R.string.years),
        textStyle = TextStyle(
            fontSize = 30.sp,
            color = MaterialTheme.colorScheme.primary,
        )
    )

}