package com.mati.tracker_presentation.component

import android.os.Build
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.mati.HealthyEating.R
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun parseDateText(date: LocalDate): String {
    val today = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        LocalDate.now()
    } else {
        TODO("VERSION.SDK_INT < O")
    }
    return when (date) {
        today -> stringResource(id = R.string.today)
        today.minusDays(1) -> stringResource(id = R.string.yesterday)
        today.plusDays(1) -> stringResource(id = R.string.tomorrow)
        else -> DateTimeFormatter.ofPattern("dd LLLL").format(date)
    }
}