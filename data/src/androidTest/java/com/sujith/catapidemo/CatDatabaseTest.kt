package com.sujith.catapidemo

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.sujith.catapidemo.data.database.CatDao
import com.sujith.catapidemo.data.database.CatDatabase
import com.sujith.catapidemo.domain.model.CatListItem
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class CatDatabaseTest {

    private lateinit var dao: CatDao
    private lateinit var catDatabase: CatDatabase

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()

        catDatabase = Room.inMemoryDatabaseBuilder(
            context, CatDatabase::class.java
        ).build()

        dao = catDatabase.catDao()
    }

    @Test
    fun writeAndReadFavouriteCatItem() = runTest {
        dao.addFavourite(
            CatListItem(
                id = "ddsas",
                breedName = "Asian",
                imageUrl = "https://com",
                originCountry = "UK",
                description = "good cat",
                lifeSpan = "15 years",
                adaptabilityLevel = 5,
                affectionLevel = 2,
                childFriendlyLevel = 3,
                dogFriendlyLevel = 2,
                energyLevel = 2,
                intelligenceLevel = 3,
                isFavourite = false
            )
        )
        assertEquals(1, dao.getFavouriteCatList().first().size)
    }
}