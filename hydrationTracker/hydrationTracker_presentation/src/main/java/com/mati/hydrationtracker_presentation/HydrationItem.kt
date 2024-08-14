package com.mati.hydrationtracker_presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mati.HealthyEating.R

@SuppressLint("UnrememberedMutableState", "ResourceAsColor")
@Preview
@Composable
fun HydrationItem(modifier: Modifier = Modifier) {

    var waterRecord by mutableIntStateOf(0)

    Card(
        modifier = modifier
            .width(250.dp)
            .padding(bottom = 16.dp),
        colors = CardDefaults.cardColors(Color.White),
        shape = RoundedCornerShape(32.dp),
        elevation = CardDefaults.cardElevation(10.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Text(
                modifier = Modifier
                    .padding(top = 12.dp)
                    .align(Alignment.CenterHorizontally),
                text = "drink water record"
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Card(
                    modifier = Modifier
                        .width(250.dp)
                        .height(40.dp)
                        .align(Alignment.Center)
                        .padding(start = 16.dp, end = 16.dp),
                    colors = CardDefaults.cardColors(Color(R.color.water_record_box)),
                    shape = RoundedCornerShape(32.dp),
                    elevation = CardDefaults.cardElevation(10.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .padding(start = 72.dp, end = 8.dp)
                            .fillMaxSize(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Icon(
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .clickable(
                                    onClick = {
                                        if (waterRecord != 0) {
                                            waterRecord -= 1
                                        }
                                    },
                                    interactionSource = remember { MutableInteractionSource() },
                                    indication = rippleIndication()
                                ),
                            tint = Color(R.color.water_record_icon),
                            painter = painterResource(id = R.drawable.negative), contentDescription = null
                        )
                        Text(
                            modifier = Modifier
                                .align(Alignment.CenterVertically),
                            color = Color(R.color.water_record_icon),
                            fontWeight = FontWeight.Bold,
                            fontSize = 22.sp,
                            text = "$waterRecord/8"
                        )

                        Icon(
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .clickable(
                                    onClick = {
                                        if (waterRecord != 8) {
                                            waterRecord += 1
                                        }
                                    },
                                    interactionSource = remember { MutableInteractionSource() },
                                    indication = rippleIndication()
                                ),
                            tint = Color(R.color.water_record_icon),
                            painter = painterResource(id = R.drawable.plus),
                            contentDescription = null
                        )

                    }
                }
                Image(
                    modifier = Modifier
                        .size(96.dp)
                        .align(Alignment.CenterStart)
                        .padding(start = 32.dp, end = 8.dp),
                    painter = painterResource(id = R.drawable.bottle),
                    contentDescription = null
                )
            }
        }
    }

}

@Composable
fun rippleIndication() = rememberRipple(bounded = false, radius = 10.dp)
