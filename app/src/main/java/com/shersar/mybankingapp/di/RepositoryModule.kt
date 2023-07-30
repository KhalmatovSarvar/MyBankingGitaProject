package com.shersar.mybankingapp.di

import com.shersar.mybankingapp.domain.AuthRepository
import com.shersar.mybankingapp.domain.impl.AuthRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @[Binds Singleton]
    fun bind(impl: AuthRepositoryImpl): AuthRepository
}