package com.staygrateful.core.network.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class ApiResponse<T>(
    val results: @RawValue List<T>,
    val count: Int,
    val next: String?,
    val previous: String?
): Parcelable
