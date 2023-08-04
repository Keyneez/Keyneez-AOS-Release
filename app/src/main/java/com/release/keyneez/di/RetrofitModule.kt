package com.release.keyneez.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.release.keyneez.BuildConfig.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {
    private const val CONTENT_TYPE = "Content-Type"
    private const val APPLICATION_JSON = "application/json"
    private const val AUTHORIZATION = "Authorization"

    @Provides
    @Singleton
    fun providesKeyneezInterceptor(): Interceptor = Interceptor { chain ->
        with(chain) {
            val requestBuilder = request().newBuilder()
                .addHeader(CONTENT_TYPE, APPLICATION_JSON)
                .addHeader(
                    AUTHORIZATION,
                    "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJLZWVuemUiLCJzdWIiOiIyIiwiaWF0IjoxNjkxMTQ3MjA3LCJleHAiOjE2OTExNTQ0MDd9.80aY7H3MdIMl8ir7selmL5esLJi1ISaLz7KRLWvRDv8"
                ) // 엑세스 토큰 값을 직접 설정합니다.
                .build()
            proceed(requestBuilder)
        }
    }

    @Provides
    @Singleton
    fun providesKeyneezOkHttpClient(
        interceptor: Interceptor
    ): OkHttpClient = OkHttpClient.Builder()
//            .connectTimeout(10, TimeUnit.SECONDS)
//            .writeTimeout(10, TimeUnit.SECONDS)
//            .readTimeout(10, TimeUnit.SECONDS)
        .addInterceptor(interceptor).addInterceptor(
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        ).build()

    @Provides
    @Singleton
    fun providesKeyneezRetrofit(
        client: OkHttpClient
    ): Retrofit = Retrofit.Builder().baseUrl(BASE_URL)
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .client(client).build()
}
