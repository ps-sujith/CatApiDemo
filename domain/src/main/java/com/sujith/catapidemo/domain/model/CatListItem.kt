package com.sujith.catapidemo.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Favourites")
data class CatListItem(
    @PrimaryKey(autoGenerate = false)
    var id: String,
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
    var isFavourite :Boolean = false
)