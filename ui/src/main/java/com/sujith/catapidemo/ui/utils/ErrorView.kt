package com.sujith.catapidemo.ui.utils

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.sp
import com.sujith.catapidemo.ui.R
import com.sujith.ui.utils.Lottie

@Composable
fun ErrorView(error: String) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Lottie(
            rawFile = R.raw.error_anim,
            isPlaying = true,
            iterations = Int.MAX_VALUE,
            modifier = Modifier.size(dimensionResource(id = R.dimen.loader_size_small))
        )
        Text(text = error, fontSize = dimensionResource(id = R.dimen.large_font_size).value.sp)
    }
}
