package com.proyecto2.proyecto_2_federico_moreira.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Card::class, Transfer::class], version = 1, exportSchema = false)

abstract class CardTransferSchema : RoomDatabase() {

    // Clase abstract para cardDao //
    abstract val cardDao: CardDao

    // Clase abstract para transferDao //
    abstract val transferDao: TransferDao

    // Companion Object para definir la instancia //
    companion object {
        @Volatile
        private var INSTANCE: CardTransferSchema? = null

        fun getInstance(context: Context): CardTransferSchema {
            var instance = INSTANCE
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    CardTransferSchema::class.java,
                    "card_transfer_database"
                ).fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
            }
            return instance
        }
    }
}