package com.kabe.genshincharacters.data.base

import android.content.Context
import com.kabe.genshincharacters.util.NetworkUtil
import java.io.IOException
import okhttp3.Interceptor
import okhttp3.Response

object RetrofitBuilder {

    class NoInternetInterceptor(private val context: Context) : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            return if (!NetworkUtil.isNetworkAvailable(context)) {
                throw NoInternetException()
            } else {
                val builder = chain.request().newBuilder()
                chain.proceed(builder.build())
            }
        }

        inner class NoInternetException : IOException("No internet connection")

    }

}