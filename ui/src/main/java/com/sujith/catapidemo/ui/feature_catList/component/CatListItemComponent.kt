package com.sujith.catapidemo.ui.feature_catList.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.sp
import com.sujith.catapidemo.domain.model.CatListItem
import com.sujith.catapidemo.ui.R
import com.sujith.catapidemo.ui.utils.CoilImage
import com.sujith.catapidemo.ui.utils.FavouriteButtonComponent
import com.sujith.catapidemo.ui.utils.ItemUtil


@Composable
fun CatListItemComponent(
    catItem: CatListItem,
    onFavouriteClicked: (isFavourite: Boolean, catListItem: CatListItem) -> Unit,
    onItemClick: (id: String) -> Unit
) {

    val context = LocalContext.current
    Card(modifier = Modifier
        .fillMaxWidth()
        .height(dimensionResource(R.dimen.list_item_height))
        .clickable { onItemClick(catItem.id) }
        .padding(dimensionResource(id = R.dimen.padding_medium)),
        elevation = CardDefaults.elevatedCardElevation(dimensionResource(id = (R.dimen.card_view_card_elevation))),
        colors = CardDefaults.cardColors(contentColor = MaterialTheme.colorScheme.surfaceContainer)
    ) {
        var size by remember { mutableStateOf(IntSize.Zero) }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .onSizeChanged {
                    size = it
                },
            contentAlignment = Alignment.BottomStart,
        ) {
            CoilImage(
                context = context, imageUri = catItem.imageUrl, modifier = Modifier
                    .fillMaxSize()
            )

            Box(
                modifier = Modifier
                    .matchParentSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black
                            ),
                            startY = (size.height / 3).toFloat(), endY = size.height.toFloat(),
                            tileMode = TileMode.Decal

                        )
                    )
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = catItem.breedName,
                    modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium)),
                    fontSize = dimensionResource(R.dimen.large_font_size).value.sp,
                    color = Color.White,
                    fontWeight = FontWeight.ExtraBold,
                    fontFamily = FontFamily(
                        Font(R.font.title_regular, FontWeight.Light)
                    )
                )
                FavouriteButtonComponent(incomingState = catItem.isFavourite) { isFavourite ->
                    onFavouriteClicked(isFavourite, catItem)
                }
            }

        }

    }
}

@Preview(showBackground = true)
@Composable
fun CatListItemComponentPreview() {
    Surface {
        CatListItemComponent(
            catItem = ItemUtil.getDummyCatItem(),
            onFavouriteClicked = { fav: Boolean, catItem: CatListItem ->
            },
            onItemClick = {})
    }
}