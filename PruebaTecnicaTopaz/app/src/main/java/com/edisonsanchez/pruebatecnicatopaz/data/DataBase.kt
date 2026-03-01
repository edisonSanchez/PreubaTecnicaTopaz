package com.edisonsanchez.pruebatecnicatopaz.data

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    entities = [FavoriteProduct::class],
    version = 1
)
abstract class DataBase : RoomDatabase() {

    abstract fun favoriteProductsDao(): FavoriteProductsDao

}
