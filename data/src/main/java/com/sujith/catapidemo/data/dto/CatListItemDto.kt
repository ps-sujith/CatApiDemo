package com.sujith.catapidemo.data.dto


import com.google.gson.annotations.SerializedName

data class CatListItemDto(
    @SerializedName("breeds")
    val breeds: List<BreedDto>,
    @SerializedName("id")
    val id: String,
    @SerializedName("url")
    val url: String,
)
data class BreedDto(
    @SerializedName("adaptability")
    val adaptability: Int,
    @SerializedName("affection_level")
    val affectionLevel: Int,
    @SerializedName("child_friendly")
    val childFriendly: Int,
    @SerializedName("description")
    val description: String,
    @SerializedName("dog_friendly")
    val dogFriendly: Int,
    @SerializedName("energy_level")
    val energyLevel: Int,
    @SerializedName("intelligence")
    val intelligence: Int,
    @SerializedName("life_span")
    val lifeSpan: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("origin")
    val origin: String,
)
