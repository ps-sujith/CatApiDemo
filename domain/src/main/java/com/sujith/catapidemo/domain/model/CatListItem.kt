package com.sujith.catapidemo.domain.model

data class CatListItem(
    val id: String,
    val breedName: String,
    val imageUrl: String,
    val originCountry: String,
    val description: String,
    val lifeSpan: String,
    val adaptabilityLevel: Int,
    val affectionLevel: Int,
    val childFriendlyLevel: Int,
    val dogFriendlyLevel: Int,
    val energyLevel: Int,
    val intelligenceLevel: Int,
)