package com.sujith.catapidemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.sujith.catapidemo.ui.feature_catList.theme.CatApiDemoTheme
import com.sujith.catapidemo.ui.navigation.AppNavigation

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CatApiDemoTheme {
                AppNavigation()
            }
        }
    }
}