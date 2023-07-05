package com.oguzhanturkmen.pixselectcasestudy.data.datasource

import com.google.gson.JsonObject
import com.oguzhanturkmen.pixselectcasestudy.data.entity.ImageResponse
import com.oguzhanturkmen.pixselectcasestudy.retrofit.DogAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PixDatasource(
    private val dogAPI: DogAPI
) {
    private lateinit var allBreed: JsonObject
    private lateinit var breedImage: ImageResponse  // A variable named ImageResponse representing
    // images of a breed.

    // It sends a request to the DogAPI to fetch all breeds and assigns the returned
    // result to the allBreed variable. The operation is performed asynchronously.
    suspend fun getAllBreed(): JsonObject {
        withContext(Dispatchers.IO) {
            allBreed = dogAPI.getDog()
        }
        return allBreed
    }

    // It sends a request to the DogAPI to fetch images of a specific breed and assigns the
    // returned result to the breedImage variable. The operation is performed asynchronously.
    suspend fun getBreedImages(breed: String): ImageResponse {
        withContext(Dispatchers.IO) {
            breedImage = dogAPI.getImageByBreed(breed)
        }
        return breedImage
    }

    suspend fun getSubBreedImages(breed: String, subKind: String): ImageResponse {
        withContext(Dispatchers.IO) {
            breedImage = dogAPI.getSubImageByBreed(breed, subKind)
        }
        return breedImage
    }
}