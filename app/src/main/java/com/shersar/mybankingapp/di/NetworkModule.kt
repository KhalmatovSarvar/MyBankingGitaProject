package com.shersar.mybankingapp.di

import android.content.Context
import com.google.gson.Gson
import com.shersar.mybankingapp.data.sources.remote.AuthApi
import com.shersar.mybankingapp.util.addLoggingInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @[Provides Singleton]
    fun getGson(): Gson = Gson()

    @[Provides Singleton]
    fun getOkHTTPClient(
        @ApplicationContext context: Context
    ) = OkHttpClient.Builder()
        .addLoggingInterceptor(context)
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()

    @[Provides Singleton]
    fun retrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl("http://143.198.48.222:84/v1/mobile-bank/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    //http://143.198.48.222:84/v1/mobile-bank/auth/sign-in

    @[Provides Singleton]
    fun provideAuthApi(retrofit: Retrofit): AuthApi = retrofit.create(AuthApi::class.java)
}