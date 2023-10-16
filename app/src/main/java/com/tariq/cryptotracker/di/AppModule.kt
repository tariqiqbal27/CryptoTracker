package com.tariq.cryptotracker.di

import com.tariq.cryptotracker.data.remote.CoinStatsApi
import com.tariq.cryptotracker.data.repository.CoinRepositoryImpl
import com.tariq.cryptotracker.domain.repository.CoinRepository
import com.tariq.cryptotracker.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideCoinStatsApi(): CoinStatsApi {
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(Interceptor { chain ->
            val original = chain.request()
            val request = original.newBuilder().header("X-API-KEY", Constants.API_KEY)
                .method(original.method, original.body).build()
            chain.proceed(request)
        })

        val client = httpClient.build()

        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(CoinStatsApi::class.java)

    }

    @Provides
    @Singleton
    fun provideCoinRepository(api: CoinStatsApi):CoinRepository {
        return CoinRepositoryImpl(api)
    }

}