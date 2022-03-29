package com.proyecto2.proyecto_2_federico_moreira.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "cards")
data class Card(

    @PrimaryKey(autoGenerate = true)
    var idCard: Long = 0L,

    @ColumnInfo(name = "money")
    var money: Double = 0.0,

    @ColumnInfo(name = "card_number")
    var cardNumber: String="",

    @ColumnInfo(name = "full_name")
    var fullName: String="",

    @ColumnInfo(name = "image")
    var image: Int = 0,

    @ColumnInfo(name = "pin_code")
    var pinCode: String = "",

    @ColumnInfo(name = "account_type")
    var accountType: Boolean = true,

    @ColumnInfo(name = "transfer")
    var transfer: String = ""

) {
    var imgTemp = 0

    val availableMoney: String
        get() = "$" + String.format("%.2f", money)

}