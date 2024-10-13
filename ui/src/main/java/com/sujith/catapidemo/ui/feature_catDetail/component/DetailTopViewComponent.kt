package com.sujith.catapidemo.ui.feature_catDetail.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Home
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sujith.catapidemo.domain.model.CatListItem
import com.sujith.catapidemo.ui.R
import com.sujith.catapidemo.ui.utils.CoilImage
import com.sujith.catapidemo.ui.utils.ItemUtil

@Composable
fun DetailsTopViewComponent(catItem: CatListItem) {
    val context = LocalContext.current
    val configuration = LocalConfiguration.current
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier
                .height((configuration.screenHeightDp / 2.5).dp)
                .fillMaxWidth()
                .padding(
                    horizontal = dimensionResource(id = R.dimen.padding_large),
                    vertical = dimensionResource(id = R.dimen.padding_small)
                ),
            elevation = CardDefaults.elevatedCardElevation(dimensionResource(id = (R.dimen.card_view_card_elevation))),

            ) {
            CoilImage(
                context = context,
                imageUri = catItem.imageUrl,
                modifier = Modifier.fillMaxWidth()
            )
        }
        Text(
            text = catItem.breedName,
            modifier = Modifier.padding(dimensionResource(R.dimen.padding_extra_small)),
            fontSize = dimensionResource(R.dimen.extra_large_font_size).value.sp,
            color = MaterialTheme.colorScheme.onBackground,
            fontWeight = FontWeight.ExtraBold,
            fontFamily = FontFamily(
                Font(R.font.title_regular, FontWeight.Light)
            ),
            style = TextStyle(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color.Blue,
                        Color.Magenta,
                        Color.Blue,
                        Color.Cyan,
                    )
                )
            )
        )
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.padding_large)),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Absolute.SpaceBetween
        ) {
            if (catItem.originCountry.isNotEmpty()) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        ImageVector.vectorResource(R.drawable.baseline_flag_circle_24),
                        tint = MaterialTheme.colorScheme.onBackground,
                        contentDescription = "Country of Origin"
                    )
                    Text(
                        text = catItem.originCountry,
                        fontSize = dimensionResource(id = R.dimen.small_font_size).value.sp,
                        color = MaterialTheme.colorScheme.onBackground,
                        fontFamily = FontFamily.Serif,
                        style = TextStyle(
                            shadow = Shadow(
                                color = MaterialTheme.colorScheme.onBackground,
                                offset = Offset(10f, 10f),
                                blurRadius = 10f
                            )
                        )
                    )
                }
            }
            if (catItem.lifeSpan.isNotEmpty()) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        Icons.Sharp.Home,
                        tint = MaterialTheme.colorScheme.onBackground,
                        contentDescription = "Country of Origin"
                    )
                    Text(
                        text = catItem.lifeSpan,
                        fontSize = dimensionResource(id = R.dimen.small_font_size).value.sp,
                        color = MaterialTheme.colorScheme.onBackground,
                        fontFamily = FontFamily.Serif,
                        style = TextStyle(
                            shadow = Shadow(
                                color = MaterialTheme.colorScheme.onBackground,
                                offset = Offset(10f, 10f),
                                blurRadius = 10f
                            )
                        )
                    )
                }
            }

        }

        Text(
            modifier = Modifier.padding(dimensionResource(R.dimen.padding_large)),
            text = catItem.description,
            fontSize = dimensionResource(id = R.dimen.small_medium_font_size).value.sp,
            color = MaterialTheme.colorScheme.onBackground,
            fontFamily = FontFamily.Serif,

            )

    }


}


@Preview(showBackground = true)
@Composable
fun DetailsTopViewComponentPreview() {
    Surface {
        DetailsTopViewComponent(catItem = ItemUtil.getDummyCatItem())
    }
}
