package com.oguzhanturkmen.pixselectcasestudy.data.entity

import java.io.Serializable

data class Response(
    val message: Map<String, List<String>>,
    val status: String
) : Serializable
