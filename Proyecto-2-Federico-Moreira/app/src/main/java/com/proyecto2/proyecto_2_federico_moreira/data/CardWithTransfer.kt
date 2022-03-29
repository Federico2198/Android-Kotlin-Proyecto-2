package com.proyecto2.proyecto_2_federico_moreira.data

import androidx.room.Embedded
import androidx.room.Relation

data class CardWithTransfer(
    @Embedded val card: Card,
    @Relation(
        parentColumn = "idCard",
        entityColumn = "idOfCard"
    )
    val listOfTransfers: List<Transfer>
)