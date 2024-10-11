package com.sujith.catapidemo.data.mapper

import com.sujith.catapidemo.data.dto.CatListItemDto
import com.sujith.catapidemo.domain.model.CatListItem

fun CatListItemDto.toDomain() = CatListItem(
    id = id,
    breedName = breeds[0].name,
    imageUrl = url,
    originCountry = breeds[0].origin,
    description = breeds[0].description,
    lifeSpan = breeds[0].lifeSpan,
    adaptabilityLevel = breeds[0].adaptability,
    affectionLevel = breeds[0].affectionLevel,
    childFriendlyLevel = breeds[0].childFriendly,
    dogFriendlyLevel = breeds[0].dogFriendly,
    energyLevel = breeds[0].energyLevel,
    intelligenceLevel = breeds[0].intelligence
)