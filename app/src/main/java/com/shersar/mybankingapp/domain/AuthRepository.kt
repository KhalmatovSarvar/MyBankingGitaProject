package com.shersar.mybankingapp.domain

import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun signIn(password: String, phone: String): Flow<Result<Unit>>
    fun signUp(
        firstName: String,
        lastName: String,
        dateOfBirth: String,
        phoneNumber: String,
        password: String,
        gender: Int
    ): Flow<Result<Unit>>
}