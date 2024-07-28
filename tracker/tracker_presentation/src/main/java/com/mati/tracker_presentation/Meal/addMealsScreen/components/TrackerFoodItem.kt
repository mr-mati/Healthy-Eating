package com.mati.tracker_presentation.Meal.addMealsScreen.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.LastBaseline
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.mati.HealthyEating.R
import com.mati.coreui.LocalSpacing
import com.mati.tracker_presentation.Meal.addMealsScreen.TrackableFoodUiState
import com.mati.tracker_presentation.tracker_main.Meal.component.MealTrackerItem
import kotlinx.coroutines.delay

@Composable
fun TrackableFoodItem(
    trackableFoodUiState: TrackableFoodUiState,
    onClick: () -> Unit,
    onAmountChange: (String) -> Unit,
    onTrack: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val food = trackableFoodUiState.food
    val spacing = LocalSpacing.current

    val mealName = remember { mutableStateOf("Select Meals") }

    var startAnimation by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(Unit) {
        delay(10)
        startAnimation = true
    }

    val alpha by animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f, animationSpec = tween(200), label = ""
    )
    Column(modifier = modifier
        .clip(RoundedCornerShape(16.dp))
        .padding(top = 8.dp, bottom = 8.dp)
        .shadow(
            elevation = 1.dp, shape = RoundedCornerShape(16.dp)
        )
        .alpha(alpha)
        .background(MaterialTheme.colors.surface)
        .clickable { onClick() }
        .padding(end = spacing.spaceMedium)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberImagePainter(data = food.imageUrl, builder = {
                    crossfade(true)
                    error(R.drawable.ic_burger)
                    fallback(R.drawable.ic_burger)
                }),
                contentDescription = food.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxHeight()
                    .size(100.dp)
                    .clip(RoundedCornerShape(topStart = 5.dp))
            )
            Spacer(modifier = Modifier.width(spacing.spaceMedium))
            Column(
                modifier = Modifier.align(CenterVertically)
            ) {
                Text(
                    text = food.name,
                    style = MaterialTheme.typography.body1,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(spacing.spaceSmall))
                Text(
                    text = stringResource(
                        id = R.string.kcal_per_100g, food.caloriesPer100g
                    ), style = MaterialTheme.typography.body2
                )
                Spacer(modifier = Modifier.height(spacing.spaceLarge))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    MealTrackerItem("crabs", food.carbsPer100g.toFloat())
                    MealTrackerItem("protein", food.proteinPer100g.toFloat())
                    MealTrackerItem("fat", food.fatPer100g.toFloat())
                }
            }
        }

        AnimatedVisibility(visible = trackableFoodUiState.isExpanded) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(spacing.spaceMedium),
                verticalArrangement = Arrangement.SpaceAround,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    BasicTextField(value = trackableFoodUiState.amount,
                        onValueChange = onAmountChange,
                        keyboardOptions = KeyboardOptions(
                            imeAction = if (trackableFoodUiState.amount.isNotBlank()) {
                                ImeAction.Done
                            } else ImeAction.Default, keyboardType = KeyboardType.Number
                        ),
                        keyboardActions = KeyboardActions(onDone = {
                            if (mealName.value != "Select Meals") {
                                onTrack(mealName.value)
                            }
                            defaultKeyboardAction(ImeAction.Done)
                        }),
                        singleLine = true,
                        modifier = Modifier
                            .border(
                                shape = RoundedCornerShape(5.dp),
                                width = 0.5.dp,
                                color = MaterialTheme.colors.onSurface
                            )
                            .alignBy(LastBaseline)
                            .padding(spacing.spaceMedium)
                            .semantics {
                                contentDescription = "Amount"
                            })
                    Spacer(modifier = Modifier.width(spacing.spaceExtraSmall))
                    Text(
                        text = stringResource(id = R.string.grams),
                        style = MaterialTheme.typography.body1,
                        modifier = Modifier.alignBy(LastBaseline)
                    )
                    Spacer(modifier = Modifier.width(spacing.spaceExtraSmall))
                    ContextMenu(mealName)
                }
                Spacer(modifier = Modifier.height(spacing.spaceLarge))
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 32.dp, end = 32.dp),
                    onClick = {
                        if (mealName.value != "Select Meals") {
                            onTrack(mealName.value)
                        }
                    },
                ) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "")

                    androidx.compose.material3.Text(
                        modifier = Modifier.padding(top = 8.dp, bottom = 8.dp),
                        text = if (mealName.value != "Select Meals") {
                            stringResource(id = R.string.add_meal, mealName.value)
                        } else {
                            mealName.value
                        },
                        textAlign = TextAlign.Center
                    )
                }
            }
        }

    }

}


@Composable
fun ContextMenu(text: MutableState<String>) {
    var expanded by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.TopEnd)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = text.value)
            IconButton(onClick = { expanded = !expanded }) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "More"
                )
            }
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                text = { androidx.compose.material3.Text("Breakfast") },
                onClick = { text.value = "breakfast" }
            )
            Divider()
            DropdownMenuItem(
                text = { androidx.compose.material3.Text("Lunch") },
                onClick = { text.value = "lunch" }
            )
            Divider()
            DropdownMenuItem(
                text = { androidx.compose.material3.Text("Dinner") },
                onClick = { text.value = "dinner" }
            )
            Divider()
            DropdownMenuItem(
                text = { androidx.compose.material3.Text("Snack") },
                onClick = { text.value = "snack" }
            )
        }
    }
}