package com.example.medicalapplicationadminapp.data.di

import com.example.medicalapplicationadminapp.data.remote.MedicalApi
import com.example.medicalapplicationadminapp.data.repository.RepositoryImp
import com.example.medicalapplicationadminapp.domain.repository.Repository
import com.example.medicalapplicationadminapp.utility.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMedicalApi(): MedicalApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MedicalApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRepo(medicalApi: MedicalApi): Repository {
        return RepositoryImp(medicalApi)
    }
}