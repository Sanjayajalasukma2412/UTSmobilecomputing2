package com.example.contactapp

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

//Untuk Model User
@Parcelize
data class User(
    var firstName: String? = "",
    var lastName: String? = "",
    var age: Int? = 0
): Parcelable