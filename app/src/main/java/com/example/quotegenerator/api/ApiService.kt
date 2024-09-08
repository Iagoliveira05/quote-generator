package com.example.quotegenerator.api

import com.example.quotegenerator.model.Quote
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


private val retrofit = Retrofit.Builder().baseUrl("https://zenquotes.io/api/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val quoteService = retrofit.create(ApiService::class.java)

interface ApiService {
    @GET ("random")
    suspend fun getQuote(): List<Quote>
}

// Error fetching java.lang.IllegalStateException:
// Expected BEGIN_OBJECT but was BEGIN_ARRAY at line 1 column 2 path $
