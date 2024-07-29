package com.mati.tracker_presentation.Meal.addMealsScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mati.HealthyEating.R
import com.mati.core.util.UiEvent
import com.mati.coreui.LocalSpacing
import com.mati.tracker_domain.model.MealType
import com.mati.tracker_presentation.Meal.addMealsScreen.components.TrackableFoodItem
import com.mati.tracker_presentation.search.components.SearchTextField
import java.time.LocalDate

@Composable
fun AddMealsScreen(
    scaffoldState: ScaffoldState,
    dayOfMonth: Int,
    month: Int,
    year: Int,
    onNavigateUp: () -> Unit,
    viewModel: AddMealsViewModel = hiltViewModel()
) {

    val spacing = LocalSpacing.current
    val state = viewModel.state
    val context = LocalContext.current

    val keyboardController = LocalSoftwareKeyboardController.current

    val gridState = rememberLazyListState()
    val lastVisibleItemIndex = gridState.layoutInfo.visibleItemsInfo.lastOrNull()?.index

    LaunchedEffect(lastVisibleItemIndex) {
        if (lastVisibleItemIndex != null && lastVisibleItemIndex >= state.trackableFood.size - 1) {
            viewModel.onEvent(AddMealsEvent.OnFetchNextPage(page = state.trackableFood.size / 10 + 1))
        }
    }

    LaunchedEffect(key1 = keyboardController) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message.asString(context)
                    )
                    keyboardController?.hide()
                }

                is UiEvent.NavigateUp -> onNavigateUp()
                else -> Unit
            }
        }
    }

    Surface(
        color = Color(0xFFF5F5F5)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(spacing.spaceMedium)
                .background(Color(0xFFF5F5F5))
        ) {
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
            Text(
                text = stringResource(id = R.string.add_meals),
                style = TextStyle(
                    fontSize = 26.sp,
                    fontFamily = FontFamily(Font(R.font.lufga_black))
                )
            )
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
            SearchTextField(
                text = state.query,
                onValueChange = {
                    viewModel.onEvent(AddMealsEvent.OnQueryChange(it))
                },
                shouldShowHint = state.isHintVisible,
                onSearch = {
                    keyboardController?.hide()
                    viewModel.onEvent(AddMealsEvent.OnSearch)
                },
                onFocusChanged = {
                    viewModel.onEvent(AddMealsEvent.OnSearchFocusChange(it.isFocused))
                }
            )
            Spacer(modifier = Modifier.height(spacing.spaceMedium))

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                state = gridState
            ) {
                items(state.trackableFood) { food ->
                    TrackableFoodItem(
                        trackableFoodUiState = food,
                        onClick = {
                            viewModel.onEvent(AddMealsEvent.OnToggleTrackableFood(food.food))
                        },
                        onAmountChange = {
                            viewModel.onEvent(
                                AddMealsEvent.OnAmountForFoodChange(
                                    food.food, it
                                )
                            )
                        },
                        onTrack = {
                            keyboardController?.hide()
                            viewModel.onEvent(
                                AddMealsEvent.OnTrackFoodClick(
                                    food = food.food,
                                    mealType = MealType.fromString(it),
                                    date = LocalDate.of(year, month, dayOfMonth)
                                )
                            )
                        },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            when {
                state.isSearching -> CircularProgressIndicator()
                state.trackableFood.isEmpty() -> {
                    Text(
                        text = stringResource(id = R.string.no_results),
                        style = MaterialTheme.typography.body1,
                        textAlign = TextAlign.Center
                    )
                }

            }
        }
    }
}