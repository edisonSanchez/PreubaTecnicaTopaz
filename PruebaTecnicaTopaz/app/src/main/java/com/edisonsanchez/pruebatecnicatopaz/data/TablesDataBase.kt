package com.edisonsanchez.pruebatecnicatopaz.data

import androidx.room.Dao
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query

@Entity(tableName = NAME_TABLE_FAVORITE_PRODUCTS)
data class FavoriteProduct(
    @PrimaryKey
    val id: Int,
    val title: String,
    val price: Double,
    val description: String,
    val thumbnail: String
)

@Dao
interface FavoriteProductsDao {

    @Query("SELECT * FROM FavoriteProducts")
    fun getAll(): List<FavoriteProduct>

    @Query("SELECT * FROM FavoriteProducts WHERE id = :id")
    fun getById(id: Int): FavoriteProduct?

    @Query("DELETE FROM FavoriteProducts WHERE id = :id")
    fun deleteById(id: Int) : Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertFavoriteProduct(favoriteProduct: FavoriteProduct) : Long

}
