package com.sujith.catapidemo.ui.feature_catDetail.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.sujith.catapidemo.domain.model.CatListItem
import com.sujith.catapidemo.ui.R
import com.sujith.catapidemo.ui.utils.ItemUtil
import com.sujith.catapidemo.ui.utils.TitleProgressBarComponent

@Composable
fun DetailsBottomViewComponent(catItem: CatListItem) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TitleProgressBarComponent(stringResource(R.string.adaptability_level), catItem.adaptabilityLevel)
        TitleProgressBarComponent(stringResource(R.string.affection_level), catItem.affectionLevel)
        TitleProgressBarComponent(stringResource(R.string.intelligence_level), catItem.intelligenceLevel)
        TitleProgressBarComponent(stringResource(R.string.energy_level), catItem.energyLevel)
        TitleProgressBarComponent(stringResource(R.string.child_friendly_level), catItem.childFriendlyLevel)
        TitleProgressBarComponent(stringResource(R.string.dog_friendly_level), catItem.dogFriendlyLevel)
        Spacer(Modifier.height(dimensionResource(R.dimen.padding_large)))
    }
}

@Preview(showBackground = true)
@Composable
fun DetailsBottomViewComponentPreview() {
    Surface {
        DetailsBottomViewComponent(catItem = ItemUtil.getDummyCatItem())
    }
}
