package com.hanzeel.iptvandroidstudio

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Channel(
    var id: Int,
    var name: String,
    var category: String,
    var link: String,
    var image: String
) : Parcelable

