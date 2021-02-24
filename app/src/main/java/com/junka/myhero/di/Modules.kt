package com.junka.myhero.di

import com.junka.myhero.character.CharacterViewModel
import com.junka.myhero.character.repository.CharacterRepository
import com.junka.myhero.character.service.CharacterService
import com.junka.myhero.common.API_KEY
import com.junka.myhero.common.BASE_URL
import com.junka.myhero.common.HASH
import com.junka.myhero.common.TS
import com.junka.myhero.event.EventViewModel
import com.junka.myhero.event.repository.EventRepository
import com.junka.myhero.event.service.EventService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val appModule = module {

    single<Interceptor>(named("header")){
            Interceptor { chain ->
                val request = chain.request()
                val newUrl = request.url.newBuilder()
                    .addQueryParameter("apikey", API_KEY)
                    .addQueryParameter("hash", HASH)
                    .addQueryParameter("ts", TS)
                    .build()
                val newRequest = request.newBuilder()
                    .url(newUrl)
                    .method(request.method, request.body)
                    .build()
                chain.proceed(newRequest)
            }
    }

    single<OkHttpClient> {
        HttpLoggingInterceptor().run {
            level = HttpLoggingInterceptor.Level.BODY
            OkHttpClient.Builder()
                .addInterceptor(this)
                .addInterceptor(get<Interceptor>(named("header")))
                .build()
        }
    }

    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(get())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }
}

val characterModule = module {

    single<CharacterRepository>{ CharacterRepository(get()) }
    single<CharacterService> { get<Retrofit>().create(CharacterService::class.java) }

    viewModel { CharacterViewModel(get())}
}

val eventModule = module {
    single<EventRepository>{ EventRepository(get()) }
    single<EventService> { get<Retrofit>().create(EventService::class.java) }
    viewModel { EventViewModel(get())}
}

val listModules = listOf(appModule,characterModule,eventModule)