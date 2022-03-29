package com.proyecto2.proyecto_2_federico_moreira.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.proyecto2.proyecto_2_federico_moreira.R
import java.util.*

@Entity(tableName = "transfers")
data class Transfer(

    @PrimaryKey(autoGenerate = true)
    var idTransfer: Long = 0L,

    @ColumnInfo(name = "amount_transfer")
    var amountTransfer: Double = 0.0,

    @ColumnInfo(name = "idOfCard")
    var idOfCard: Long = 0L,

    @ColumnInfo(name="destination")
    var destination: String =""
){

    fun getReadableMoney()= "$ $amountTransfer"

    fun setImageMoney(): Int{
        return if(destination == "user") R.drawable.visainfinite
        else R.drawable.visainfinite
    }
}