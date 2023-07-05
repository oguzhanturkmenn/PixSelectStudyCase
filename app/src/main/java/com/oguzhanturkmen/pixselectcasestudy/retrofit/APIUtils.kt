package com.oguzhanturkmen.pixselectcasestudy.retrofit

import com.oguzhanturkmen.pixselectcasestudy.util.Constants

class APIUtils {
    companion object {
        private const val BASE_URL = Constants.BASE_URL
        fun plantAPIGet(): DogAPI {
            return RetrofitBuilder
                .getClient(BASE_URL)
                .create(DogAPI::class.java)
        }
    }
}