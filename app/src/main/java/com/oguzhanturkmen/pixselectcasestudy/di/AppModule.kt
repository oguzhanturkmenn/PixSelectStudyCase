package com.oguzhanturkmen.pixselectcasestudy.di

import com.oguzhanturkmen.pixselectcasestudy.data.datasource.PixDatasource
import com.oguzhanturkmen.pixselectcasestudy.data.repo.PixRepo
import com.oguzhanturkmen.pixselectcasestudy.retrofit.APIUtils
import com.oguzhanturkmen.pixselectcasestudy.retrofit.DogAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun providePixRepo(pds: PixDatasource): PixRepo {
        return PixRepo(pds)
    }

    @Provides
    @Singleton
    fun providePixDatasource(
        dogAPI: DogAPI
    ): PixDatasource {
        return PixDatasource(
            dogAPI
        )
    }

    @Provides
    @Singleton
    fun provideDogAPI(): DogAPI {
        return APIUtils.plantAPIGet()
    }
}