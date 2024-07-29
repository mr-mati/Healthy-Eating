package com.mati.tracker_presentation.tracker_main.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.mati.HealthyEating.R
import com.mati.coreui.LocalSpacing
import com.mati.tracker_domain.model.TrackedFood
import com.mati.tracker_presentation.tracker_main.Meal.component.MealTrackerItem

@Composable
fun FoodItem(
    trackedFood: TrackedFood,
    onDeleteClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val spacing = LocalSpacing.current
    Card(
        modifier = Modifier
            .padding(top = 8.dp, bottom = 8.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.secondary)
    ) {
        Icon(imageVector = Icons.Default.Close,
            contentDescription = stringResource(id = R.string.delete),
            modifier = Modifier
                .padding(end = 16.dp, top = 8.dp)
                .align(Alignment.End)
                .clickable { onDeleteClick() })
        Row(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Image(
                painter = rememberImagePainter(data = trackedFood.imageUrl, builder = {
                    crossfade(true)
                    error(R.drawable.ic_burger)
                    fallback(R.drawable.ic_burger)
                }),
                contentDescription = trackedFood.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(80.dp)
                    .padding(start = 8.dp)
                    .fillMaxHeight()
                    .aspectRatio(1f)
                    .clip(
                        RoundedCornerShape(
                            topStart = 5.dp, bottomStart = 5.dp
                        )
                    )
            )
            Column(
                modifier = Modifier
                    .padding(start = 8.dp)
            ) {
                Text(
                    text = if (trackedFood.name.length > 30) {
                        trackedFood.name.substring(0..30)
                    } else {
                        trackedFood.name
                    },
                    style = TextStyle(
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontSize = 10.sp,
                        textAlign = TextAlign.Start
                    ),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 2
                )
                Spacer(modifier = Modifier.height(spacing.spaceMedium))
                Text(
                    text = stringResource(
                        id = R.string.nutrient_info, trackedFood.amount, trackedFood.calories
                    ), style = TextStyle(
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontSize = 10.sp,
                        textAlign = TextAlign.Start
                    )
                )
                Spacer(modifier = Modifier.height(spacing.spaceMedium))
                Row(
                    modifier = Modifier
                        .padding(bottom = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    MealTrackerItem("crabs", trackedFood.carbs.toFloat())
                    MealTrackerItem("protein", trackedFood.protein.toFloat())
                    MealTrackerItem("fat", trackedFood.fat.toFloat())
                }
            }
        }
    }
}