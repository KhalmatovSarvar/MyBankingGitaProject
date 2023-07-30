package com.shersar.mybankingapp.domain.impl

import com.shersar.mybankingapp.data.model.request.SignInRequest
import com.shersar.mybankingapp.data.model.request.SignUpRequest
import com.shersar.mybankingapp.data.sources.remote.AuthApi
import com.shersar.mybankingapp.domain.AuthRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepositoryImpl @Inject constructor(
    private val authApi: AuthApi
) : AuthRepository {

    override fun signIn(password: String, phone: String): Flow<Result<Unit>> = flow {
        val response = authApi.signIn(SignInRequest(password, phone))
        if (response.isSuccessful && response.body() != null)
            emit(Result.success(Unit))
        else emit(Result.failure(Exception("Failure")))
    }.catch { }.flowOn(Dispatchers.IO)

    override fun signUp(
        firstName: String,
        lastName: String,
        dateOfBirth: String,
        phoneNumber: String,
        password: String,
        gender: Int
    ): Flow<Result<Unit>> = flow{
        val response  = authApi.signUp(SignUpRequest(firstName,lastName,dateOfBirth, phoneNumber, password, gender))
        if (response.isSuccessful && response.body()!=null){
            emit(Result.success(Unit))
        }else{
            emit(Result.failure(Exception("Failure")))
        }
    }.catch {  }.flowOn(Dispatchers.IO)
}