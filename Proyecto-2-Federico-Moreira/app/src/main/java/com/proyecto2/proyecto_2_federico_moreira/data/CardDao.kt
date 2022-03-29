package com.proyecto2.proyecto_2_federico_moreira.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CardDao {

    // Agregar una Card //
    @Insert
    suspend fun insertCard(card:Card)

    // Obtener todas las Card que conicidan con cierto id //
    @Query("SELECT * FROM cards WHERE idCard = :idCard")
    fun getCard(idCard:Long):LiveData<Card>

    // Obtener todas las Card //
    @Query("SELECT * FROM cards")
    fun getCards(): LiveData<List<Card>>

    // Obtener una Card con Transfers //
    @Transaction
    @Query("SELECT * FROM cards WHERE idCard = :idCard")
    fun getCardWithTransfers(idCard: Long): List<CardWithTransfer>

    // Actualizar una Card //
    @Update
    suspend fun updateCard(card:Card)

    // Eliminar Card por ID //
    @Query("DELETE FROM cards WHERE idCard= :idCard")
    suspend fun deleteCardById(idCard:Long)

    // Eliminar Cards //
    @Delete
    suspend fun deleteCard(card:Card)
}