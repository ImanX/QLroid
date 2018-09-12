package com.github.imanx.QLroid.http

import com.github.imanx.QLroid.request.Header
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import org.jetbrains.annotations.Nullable
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.*

interface ApiInterface {

    @HTTP(method = "POST", path = "/api/graphql/my", hasBody = true)
    fun getRequest(@Body body: String): Call<String>

    companion object RETROFIT {

        fun create(@Nullable header: Header): ApiInterface {
            val okHttpClient = OkHttpInterceptor.interceptor(header)

            val retrofit = Retrofit.Builder()
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient.build())
                    .baseUrl("http://91.239.55.205:8080/")
                    .build()

            return retrofit.create(ApiInterface::class.java)
        }
    }
}