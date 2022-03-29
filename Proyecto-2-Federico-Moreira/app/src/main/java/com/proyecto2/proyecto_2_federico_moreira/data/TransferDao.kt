package com.proyecto2.proyecto_2_federico_moreira.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TransferDao {

    // Ingresar una Transfer //
    @Insert
    suspend fun insertTransfer(transfer : Transfer)

    // Obtener todas las Transfers //
    @Query("SELECT * FROM transfers")
    fun getTransfers(): LiveData<List<Transfer>>

    // Obtener todas las Transfer con cierto id //
    @Query("SELECT * FROM transfers WHERE idTransfer= :idTransfer")
    fun getTransfer(idTransfer:Long): LiveData<Transfer>

    // Ontener todas las Transfers de cierto id de Card //
    @Query("SELECT * FROM transfers WHERE idOfCard = :idCard")
    fun getTransfersOfCard(idCard:Long) : LiveData<List<Transfer>>

    // Actualizar una Transfer //
    @Update
    suspend fun updateTransfer(transfer : Transfer)

    // Eliminar una Transfer //
    @Delete
    suspend fun deleteTransfer(transfer : Transfer)
}