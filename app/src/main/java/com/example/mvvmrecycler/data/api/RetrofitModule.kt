package com.example.mvvmrecycler.data.api

import com.example.mvvmrecycler.R
import com.example.mvvmrecycler.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

   @Provides
   @Singleton
   fun provideRetrofit(): Retrofit =

       Retrofit.Builder()
           .baseUrl(Constants.BASE_URL.toString())
           .addConverterFactory(GsonConverterFactory.create())
           .build()

    @Provides
    @Singleton
    fun provideApi() = provideRetrofit().create(APIService::class.java)


}