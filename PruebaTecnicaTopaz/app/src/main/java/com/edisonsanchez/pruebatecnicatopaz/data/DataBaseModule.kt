package com.edisonsanchez.pruebatecnicatopaz.data

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {

    @Provides
    @Singleton
    fun providesRoomDataBase(@ApplicationContext context: Context): DataBase {
        return Room.databaseBuilder(context,DataBase::class.java, NAME_DATA_BASE).build()
    }

    @Provides
    @Singleton
    fun providesFavoriteProductsDao(dataBase: DataBase): FavoriteProductsDao {
        return dataBase.favoriteProductsDao()
    }


}
