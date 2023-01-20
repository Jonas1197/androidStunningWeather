package com.example.stunningweather.presentation.main_screen.modules

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun <T> ScrollComponent(
    title: String = "",
    items: List<T>,
    isHorizontal: Boolean = true,
    fillsMaxHeight: Boolean = false,
    height: Dp = 150.dp,
    content: @Composable (Int, T) -> Unit,
) {

    Box(modifier = Modifier
        .fillMaxWidth()
        .apply {
            if(!fillsMaxHeight) {
                height(height)
            }
        }
    ) {

        Column {

            if(isHorizontal) {
                Divider(
                    modifier = Modifier
                        .padding(
                            top = 16.dp,
                            bottom = 16.dp
                        )
                        .alpha(0.7f),
                    color = Color.White,
                    thickness = 0.5.dp
                )
            }


            BoxWithConstraints(
                modifier = Modifier
                    .fillMaxWidth()
                    .apply {
                        if(fillsMaxHeight) {
                            fillMaxHeight()
                        }
                    }
            ) {

                if(isHorizontal) {
                    LazyRow(
                        modifier = Modifier
                            .fillMaxWidth(),
                        state = rememberLazyListState()
                    ) {
                        itemsIndexed(items) { index, item ->
                            content(index, item)
                        }
                    }


                } else {

                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .apply {
                                   if(fillsMaxHeight) {
                                       fillMaxHeight()
                                   }
                            },
                        state = rememberLazyListState(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        itemsIndexed(items) { index, item ->
                            content(index, item)
                        }
                    }
                }
            }

            if(isHorizontal) {
                Divider(
                    modifier = Modifier
                        .padding(
                            top = 16.dp,
                            bottom = 16.dp
                        )
                        .alpha(0.7f),
                    color = Color.White,
                    thickness = 0.5.dp
                )
            }
        }
    }
}