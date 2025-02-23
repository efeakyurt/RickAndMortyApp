package com.example.rickandmortyapp

import retrofit2.http.GET

interface APIService {
    @GET("character")
    suspend fun getCharacters(): APIResponse
}
