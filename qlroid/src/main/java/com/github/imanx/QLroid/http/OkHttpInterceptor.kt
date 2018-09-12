package com.github.imanx.QLroid.http

import com.github.imanx.QLroid.request.Header
import okhttp3.OkHttpClient

object OkHttpInterceptor {

    fun interceptor(header: Header?): OkHttpClient.Builder {
        val okHttp = OkHttpClient.Builder()
        okHttp.addInterceptor { chain ->
            val original = chain.request()
            val request = original.newBuilder()

            if (header != null) {
                for ((key, value) in header.map) {
                    request.header(key, value)
                }
            }

            request.method(original.method(), original.body())

            chain.proceed(request.build())
        }
        return okHttp
    }
}
