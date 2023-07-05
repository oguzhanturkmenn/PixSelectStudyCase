package com.oguzhanturkmen.pixselectcasestudy.data.entity

import java.io.Serializable

data class ImageResponse(
    val message: ArrayList<String>,
    val status: String
) : Serializable
