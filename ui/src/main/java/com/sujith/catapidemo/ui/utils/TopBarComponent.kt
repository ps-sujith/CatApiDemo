package com.sujith.catapidemo.ui.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import com.sujith.catapidemo.ui.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarComponent(
    scrollBehavior: TopAppBarScrollBehavior,
    addNavigateBack: Boolean,
    onNavigateBackClick: () -> Unit
) {
    TopAppBar(
        scrollBehavior = scrollBehavior,
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        title = {
            AppTitle()
        },

        navigationIcon = {
            if (addNavigateBack) {
                IconButton(onClick = { onNavigateBackClick() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "BACK"
                    )
                }
            }
        }
    )
}

@Composable
fun AppTitle() {
    val annotatedString = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = dimensionResource(id = R.dimen.app_bar_title_size).value.sp,
                fontWeight = FontWeight.W300,
                fontStyle = FontStyle.Italic
            )
        ) {
            append("My, ")
        }
        withStyle(
            style = SpanStyle(
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.Bold,
                fontSize = dimensionResource(id = R.dimen.app_bar_title_size).value.sp,
            )
        ) {
            append(stringResource(R.string.my_cat_app))
        }

    }
    Text(
        text = annotatedString,
        fontWeight = FontWeight.ExtraBold,
        color = MaterialTheme.colorScheme.onBackground
    )
}
