package com.shersar.mybankingapp.data.sources.remote

import com.shersar.mybankingapp.data.model.request.SignInRequest
import com.shersar.mybankingapp.data.model.request.SignUpRequest
import com.shersar.mybankingapp.data.model.response.SignInResponse
import com.shersar.mybankingapp.data.model.response.SignUpResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("auth/sign-in")
    suspend fun signIn(@Body request: SignInRequest): Response<SignInResponse>

    @POST("auth/sign-up")
    suspend fun signUp(@Body request: SignUpRequest): Response<SignUpResponse>


}