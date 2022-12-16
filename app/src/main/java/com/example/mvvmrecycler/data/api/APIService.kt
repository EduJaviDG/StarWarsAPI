package com.example.mvvmrecycler.data.api

import com.example.mvvmrecycler.domain.model.CharacterResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APIService {

  @GET("people/")
  suspend fun getCharacterByName(@Query("search") search: String): Response<CharacterResponse>

}