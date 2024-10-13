package com.sujith.catapidemo.ui.utils

import com.sujith.catapidemo.domain.model.CatListItem

object ItemUtil {
    fun getDummyCatItem() = CatListItem(
            id = "matrix720",
            breedName = "Persian",
            imageUrl = "https://cdn2.thecatapi.com/images/vVF7hE-Py.jpg",
            originCountry = "United States",
            description = "The Exotic Shorthair is a gentle friendly cat that has the same personality as the Persian",
            lifeSpan = "15 years",
            adaptabilityLevel = 5,
            affectionLevel = 6,
            childFriendlyLevel = 8,
            dogFriendlyLevel = 7,
            energyLevel = 5,
            intelligenceLevel = 3,
            isFavourite = true
        )
}