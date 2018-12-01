package com.github.imanx.QLroid.request


import android.content.Context
import android.net.Uri
import android.os.Looper
import android.util.Log
import com.github.imanx.QLroid.GraphCore
import com.github.imanx.QLroid.Mutation
import com.github.imanx.QLroid.Query
import com.github.imanx.QLroid.callback.Callback
import com.github.imanx.QLroid.utility.Utility
import okhttp3.*
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException


/**
 * Created by Farshid Roohi.
 * QLroid | Copyrights 2018 ZarinPal Crop.
 */

class Request private constructor(private val builder: Builder) {
    private var callback: Callback? = null


    fun enqueue(callback: Callback) {
        this.callback = callback
        enqueue()
    }

    private fun enqueue() {

        if (this.builder.graphCore == null) {
            return
        }

        val jsonObject = JSONObject()

        try {

            jsonObject.put("operationName", null)
            jsonObject.put("query", builder.graphCore!!.query)

            builder.graphCore.let {
                jsonObject.put("variables", builder.graphCore!!.variables)
            }

        } catch (e: JSONException) {
            e.printStackTrace()
        }

        val okHttpClient = OkHttpClient()
        val request = okhttp3.Request.Builder()
        for ((key, value) in this.builder.getHeader()!!.map) {
            request.addHeader(key, value)
        }
        request.header("User-Agent", "android-user-agent")


        request.url(this.builder.uri!!.toString())

        val type = MediaType.parse("application/json; charset=utf-8")
        val requestBody = RequestBody.create(type, jsonObject.toString())
        request.post(requestBody)


        okHttpClient.newCall(request.build()).enqueue(object : okhttp3.Callback {
            override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {

                val responseRequest = response.body()!!.string()

                if (!response.isSuccessful) {
                    if (callback != null) callback!!.onFailure(response.code(), responseRequest)
                    return
                }

                val model = builder.graphCore!!.model
                val masterKey = if (model == null) builder.graphCore!!.operationName else model.responseModelName
                val wrappedJson = Utility.getWrappedJson(JSONObject(responseRequest), masterKey)

                if (callback == null) {
                    return
                }
                when {
                    wrappedJson == null -> callback!!.onResponse(responseRequest)
                    model == null -> callback!!.onResponse(wrappedJson)
                    else -> {
                        val result = Utility.refactor(model.javaClass, wrappedJson)
                        callback!!.onResponse(result)
                    }
                }


            }

            override fun onFailure(call: okhttp3.Call, e: IOException) {
                Log.i("LOG:::", "ERROR : ${e.message}")
                val handler = android.os.Handler(Looper.getMainLooper())
                handler.post {
                    if (callback != null) callback!!.onFailure(e.hashCode(), e.message)
                }
            }
        })

    }

    /* This interceptor adds a custom User-Agent. */
    inner class UserAgentInterceptor(private val userAgent: String) : Interceptor {

        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
            val originalRequest = chain.request()

            val requestWithUserAgent = originalRequest.newBuilder()
            requestWithUserAgent.header("User-Agent", userAgent)
//            requestWithUserAgent.header()
            return chain.proceed(requestWithUserAgent.build())
        }
    }

    // Builder segment

    class Builder {

        var context: Context? = null
            private set
        var uri: Uri? = null
            private set
        var graphCore: GraphCore? = null
            private set
        private var header: Header? = null
        private var timeout: Int = 0

        constructor(context: Context, uri: Uri, query: Query) {
            this.uri = uri
            this.graphCore = query
            this.context = context
        }

        constructor(context: Context, uri: Uri, mutation: Mutation) {
            this.uri = uri
            this.graphCore = mutation
            this.context = context
        }

        constructor(context: Context, uri: Uri) {
            this.uri = uri
            this.context = context
        }

        fun setGraph(graph: GraphCore): Builder {
            this.graphCore = graph
            return this
        }

        fun setHeader(header: Header): Builder {
            this.header = header
            return this
        }

        fun setTimeout(sec: Int): Builder {
            this.timeout = sec
            return this
        }

        fun getHeader(): Header? {
            return this.header
        }

        fun build(): Request {
            return Request(this)
        }

        fun getTimeout(): Int {
            return this.timeout
        }
    }
}
