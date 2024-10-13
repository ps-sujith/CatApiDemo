package com.sujith.catapidemo.ui.navigation

import kotlinx.serialization.Serializable

//Graphs
@Serializable
object CatListGraph

@Serializable
object FavouriteGraph

//Screen
@Serializable
object Splash

@Serializable
object CatList

@Serializable
data class CatDetail(val id :String)

@Serializable
object FavouriteList

