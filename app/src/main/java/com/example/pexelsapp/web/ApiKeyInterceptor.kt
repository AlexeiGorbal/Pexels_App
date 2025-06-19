package com.example.pexelsapp.web

import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest = chain.request()
            .newBuilder()
            .header("Authorization", "PkLWia7vYKaf05ayK0DyplKJhz9RQcazur45ktLL2cHKjy6wec8L0yUQ")
            .build()
        return chain.proceed(newRequest)
    }
}