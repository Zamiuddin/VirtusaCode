package com.app.virtusatest.data.local.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.app.virtusatest.domain.model.DogBreed
import com.app.virtusatest.domain.model.DogBreedImages
import com.app.virtusatest.utils.Converters


@Database(version = 1, entities = [DogBreed::class, DogBreedImages::class], exportSchema = false)
@TypeConverters(Converters::class)
abstract class DogBreedsDatabase : RoomDatabase() {

    abstract fun dogBreedsDao(): DogBreedsDao
}