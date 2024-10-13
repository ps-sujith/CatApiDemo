package com.sujith.catapidemo.ui.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sujith.catapidemo.ui.R


@Composable
fun TitleProgressBarComponent(title: String, incomingProgress: Int) {
    val configuration = LocalConfiguration.current
    Card(
        modifier = Modifier
            .wrapContentSize()
            .fillMaxWidth()
            .padding(
                horizontal = dimensionResource(id = R.dimen.padding_large),
                vertical = dimensionResource(id = R.dimen.padding_small)
            ),
        elevation = CardDefaults.elevatedCardElevation(dimensionResource(id = (R.dimen.card_view_card_elevation))),

        ) {
        Row(
            modifier = Modifier
                .padding(dimensionResource(R.dimen.padding_large))
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = title,
                fontSize = dimensionResource(id = R.dimen.small_font_size).value.sp,
                color = MaterialTheme.colorScheme.onBackground,
                maxLines = 2,
                fontFamily = FontFamily.Serif,
            )

            CustomLinearProgressIndicator(
                modifier = Modifier.width((configuration.screenWidthDp / 2).dp),
                progress = incomingProgress
            )

        }
    }
}

@Composable
fun CustomLinearProgressIndicator(
    modifier: Modifier = Modifier,
    progress: Int,
    clipShape: RoundedCornerShape = RoundedCornerShape(10.dp)
) {
    Box(
        modifier = modifier
            .clip(clipShape)
            .background(Color.Gray)
            .height(8.dp)

    ) {
        Box(
            modifier = Modifier
                .background(
                    Brush.linearGradient(
                        colors = listOf(
                            Color.Blue,
                            Color.Magenta
                        ),
                        start = Offset(0f, 0f),
                        end = Offset(10f, 10f),
                        tileMode = TileMode.Mirror
                    )
                )
                .fillMaxHeight()
                .fillMaxWidth(progress / 10f)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TitleProgressBarComponentPreview() {
    Surface {
        TitleProgressBarComponent("Age", 5)
    }
}