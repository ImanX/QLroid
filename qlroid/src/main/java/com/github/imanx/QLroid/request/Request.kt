package com.github.imanx.QLroid.request

import android.content.Context
import android.net.Uri
import android.util.Log

import com.github.imanx.QLroid.GraphCore
import com.github.imanx.QLroid.GraphModel
import com.github.imanx.QLroid.Mutation
import com.github.imanx.QLroid.Query
import com.github.imanx.QLroid.callback.Callback
import com.github.imanx.QLroid.http.ApiInterface
import com.github.imanx.QLroid.utility.Utility
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.zarinpal.libs.httpRequest.HttpRequest
import com.zarinpal.libs.httpRequest.OnCallbackRequestListener
import org.json.JSONArray

import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response


/**
 * Created by ImanX.
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

        val api = ApiInterface.create(this.builder.getHeader()!!)
        val call = api.getRequest(jsonObject.toString())
        call.enqueue(object : retrofit2.Callback<String> {
            override fun onResponse(call: Call<String>?, response: Response<String>?) {

                if (!response!!.isSuccessful) {
                    if (callback != null) callback!!.onFailure(response.code(), response.errorBody().toString())
                    return
                }

                Log.i("AA", "A : ${response.body()}")

                if (1 == 1) {
                    return
                }

                val model = builder.graphCore!!.model
                val masterKey = if (model == null) builder.graphCore!!.operationName else model.responseModelName
                val wrappedJson = Utility.getWrappedJson(JSONObject(response.body()), masterKey)


                if (callback == null) {
                    return
                }

                if (wrappedJson == null) {
                    callback!!.onResponse(response.body().toString())
                    return
                }

                if (model == null) {
                    callback!!.onResponse(wrappedJson)
                    return
                }

                val result = Utility.refactor(model.javaClass, wrappedJson)
                callback!!.onResponse(result)
            }


            override fun onFailure(call: Call<String>?, t: Throwable?) {
                if (t == null) {
                    return
                }
                if (callback != null) callback!!.onFailure(0, t.message)
            }

        })

//            val jsonObject = JSONObject()
//            try {
//
//                jsonObject.put("operationName", null)
//                jsonObject.put("query", builder.graphCore!!.query)
//
//                if (builder.graphCore!!.variables != null) {
//                    jsonObject.put("variables", builder.graphCore!!.variables)
//                }
//
//            } catch (e: JSONException) {
//                e.printStackTrace()
//            }
//
//            Log.i("AAAAAA", "run: " + jsonObject.toString())
//
//            HttpRequest(builder.context)
//                    .setRequestMethod(HttpRequest.POST)
//                    .setHeaders(builder.getHeader()!!.map)
//                    .setTimeOut(builder.getTimeout())
//                    .setURL(builder.uri!!.toString())
//                    .setJson(jsonObject)
//                    .get(object : OnCallbackRequestListener() {
//                        override fun onSuccessResponse(jsonObject: JSONObject, content: String) {
//
//                            val model = builder.graphCore!!.model
//                            val masterKey = if (model == null) builder.graphCore!!.operationName else model.responseModelName
//                            val wrappedJson = Utility.getWrappedJson(jsonObject, masterKey)
//
//
//                            if (callback == null) {
//                                return
//                            }
//
//                            if (wrappedJson == null) {
//                                callback!!.onResponse(content)
//                                return
//                            }
//
//
//                            if (model == null) {
//                                callback!!.onResponse(wrappedJson)
//                                return
//                            }
//
//                            val result = Utility.refactor(model.javaClass, wrappedJson)
//                            callback!!.onResponse(result)
//
//                        }
//
//                        override fun onFailureResponse(httpCode: Int, dataError: String) {
//                            Log.i("TAG", "onFailureResponse: $httpCode || $dataError")
//                            if (callback != null) callback!!.onFailure(httpCode, dataError)
//                        }
//                    })

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

    companion object {

        var TAG = Request::class.java.simpleName
    }
}
