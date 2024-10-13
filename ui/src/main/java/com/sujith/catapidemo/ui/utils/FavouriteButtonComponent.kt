package com.sujith.catapidemo.ui.utils

import androidx.compose.material.IconToggleButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer


@Composable
fun FavouriteButtonComponent(
    modifier: Modifier = Modifier,
    incomingState :Boolean,
    onFavouriteClicked: (isFavourite: Boolean) -> Unit,
) {
    var isFavorite by remember { mutableStateOf(incomingState) }
    IconToggleButton(
        checked = isFavorite,
        onCheckedChange = {
            onFavouriteClicked(!isFavorite)
            isFavorite = !isFavorite
        }
    ) {
        Icon(
            tint = Color.Red,
            modifier = modifier.graphicsLayer {
                scaleX = 1.3f
                scaleY = 1.3f
            },
            imageVector = if (isFavorite) {
                Icons.Filled.Favorite
            } else {
                Icons.Default.FavoriteBorder
            },
            contentDescription = "Favourites Icon"
        )
    }
}