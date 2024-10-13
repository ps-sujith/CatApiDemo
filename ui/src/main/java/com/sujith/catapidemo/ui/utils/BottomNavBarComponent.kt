package com.sujith.catapidemo.ui.utils

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.sujith.catapidemo.ui.R
import com.sujith.catapidemo.ui.navigation.CatListGraph
import com.sujith.catapidemo.ui.navigation.FavouriteGraph
import com.sujith.catapidemo.ui.navigation.TopLevelRoute


@Composable
fun BottomNavBarComponent(navController: NavController) {
    val routes = listOf(
        TopLevelRoute(
            "Cats",
            CatListGraph,
            ImageVector.vectorResource(id = R.drawable.ic_cat_head_vect)
        ),
        TopLevelRoute(
            "Favourite",
            FavouriteGraph,
            ImageVector.vectorResource(id = R.drawable.ic_heart_vect)
        )
    )
    BottomNavigation(backgroundColor = MaterialTheme.colorScheme.surface) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        routes.forEach { topLevelRoute ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        tint = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier
                            .size(36.dp)
                            .padding(dimensionResource(R.dimen.padding_small)),
                        imageVector = topLevelRoute.icon,
                        contentDescription = topLevelRoute.name
                    )
                },
                label = {
                    Text(
                        modifier = Modifier.padding(2.dp),
                        text = topLevelRoute.name,
                        fontSize = dimensionResource(id = R.dimen.small_font_size).value.sp,
                        color = MaterialTheme.colorScheme.onBackground,
                        fontFamily = FontFamily.Serif,
                    )
                },
                selected = currentDestination?.hierarchy?.any {
                    it.hasRoute(
                        topLevelRoute.route.toString(),
                        null
                    )
                } == true,
                onClick = {
                    navController.navigate(topLevelRoute.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                selectedContentColor = Color.Red

            )
        }

    }
}