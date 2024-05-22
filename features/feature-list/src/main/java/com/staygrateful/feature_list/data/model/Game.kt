package com.staygrateful.feature_list.data.model
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Game(
    val id: Int,
    val name: String,
    val released: String,
    val backgroundImage: String
) : Parcelable
