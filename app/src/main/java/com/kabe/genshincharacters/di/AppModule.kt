package com.kabe.genshincharacters.di

import android.content.Context
import com.kabe.genshincharacters.BuildConfig
import com.kabe.genshincharacters.BuildConfig.DEBUG
import com.kabe.genshincharacters.constant.AppConstants
import com.kabe.genshincharacters.data.base.RetrofitBuilder
import com.kabe.genshincharacters.network.CharactersService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private const val TIMEOUT = 20000 //20 seconds
    private const val DATABASE_NAME = AppConstants.DATABASE_NAME

    // Retrofit Builder
    @Provides
    @Singleton
    fun provideClient(@ApplicationContext appContext: Context): OkHttpClient {
        return OkHttpClient.Builder().apply {

            connectTimeout(TIMEOUT.toLong(), TimeUnit.MILLISECONDS)
            readTimeout(TIMEOUT.toLong(), TimeUnit.MILLISECONDS)

            addInterceptor(HttpLoggingInterceptor().apply {
                setLevel(if (DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE)
            })

            addInterceptor(RetrofitBuilder.NoInternetInterceptor(appContext))

        }.build()
    }

    @Provides
    @Singleton
    fun createRetrofitInstance(client: OkHttpClient): Retrofit = Retrofit.Builder().apply {
        addConverterFactory(GsonConverterFactory.create())
        baseUrl(BuildConfig.BASE_URL)
        client(client)
    }.build()

    @Provides
    @Singleton
    fun provideCharactersService(retrofit: Retrofit): CharactersService {
        return retrofit.create(CharactersService::class.java)
    }

}