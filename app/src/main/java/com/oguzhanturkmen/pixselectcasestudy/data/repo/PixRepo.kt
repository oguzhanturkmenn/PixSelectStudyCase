package com.oguzhanturkmen.pixselectcasestudy.data.repo

import com.google.gson.JsonObject
import com.oguzhanturkmen.pixselectcasestudy.data.datasource.PixDatasource
import com.oguzhanturkmen.pixselectcasestudy.data.entity.ImageResponse

class PixRepo(val datasource: PixDatasource) {
    suspend fun getAllBreed(): JsonObject = datasource.getAllBreed()
    suspend fun getBreedImages(breed: String): ImageResponse = datasource.getBreedImages(breed)
    suspend fun getSubBreedImages(breed: String, subBreed: String): ImageResponse =
        datasource.getSubBreedImages(breed, subBreed)
}

