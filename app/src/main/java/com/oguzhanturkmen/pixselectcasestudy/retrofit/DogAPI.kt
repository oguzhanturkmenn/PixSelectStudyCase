package com.oguzhanturkmen.pixselectcasestudy.retrofit

import com.google.gson.JsonObject
import com.oguzhanturkmen.pixselectcasestudy.data.entity.ImageResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface DogAPI {
    @GET("api/breed/{breed}/{subbreed}/images")
    suspend fun getSubImageByBreed(@Path("breed")kind:String,@Path("subbreed")subkind:String):ImageResponse

    @GET("api/breed/{breed}/images")
    suspend fun getImageByBreed(@Path("breed") breed: String): ImageResponse

    @GET("api/breeds/list/all")
    suspend fun getDog(): JsonObject
}
