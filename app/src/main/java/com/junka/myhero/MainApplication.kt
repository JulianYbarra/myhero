package com.junka.myhero

import android.app.Application
import com.junka.myhero.character.service.CharacterService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MainApplication : Application() {

}

object RetrofitInstance {

    private val okHttpClient = HttpLoggingInterceptor().run {
        level = HttpLoggingInterceptor.Level.BODY
        OkHttpClient.Builder().addInterceptor(this).build()
    }
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://gateway.marvel.com")
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    val characterService : CharacterService = retrofit.create(CharacterService::class.java)
}