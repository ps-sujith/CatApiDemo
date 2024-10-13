package com.sujith.catapidemo.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sujith.catapidemo.domain.model.CatListItem
import kotlinx.coroutines.flow.Flow

@Dao
interface CatDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavourite(catListItem: CatListItem)

    @Delete
    suspend fun removeFavourite(catListItem: CatListItem)

    @Query("SELECT * from favourites")
    fun getFavouriteCatList(): Flow<List<CatListItem>>
}