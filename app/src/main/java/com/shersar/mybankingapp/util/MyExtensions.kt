package com.shersar.mybankingapp.util

import android.content.Context
import android.widget.Toast
import com.chuckerteam.chucker.api.ChuckerInterceptor
import okhttp3.OkHttpClient

fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun OkHttpClient.Builder.addLoggingInterceptor(context: Context): OkHttpClient.Builder {
    addInterceptor(ChuckerInterceptor.Builder(context).build())
    return this
}