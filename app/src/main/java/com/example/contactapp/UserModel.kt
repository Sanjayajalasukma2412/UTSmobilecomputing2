package com.example.contactapp

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class UserModel(
    var data: ArrayList<UserData>
)

@Parcelize
data class UserData(
    var url: String,
    var first: String,
    var last: String,
    var age: Int
): Parcelable