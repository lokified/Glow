package com.loki.glow.di

import com.loki.glow.BuildConfig
import com.loki.glow.data.remote.CoinRankingApi
import com.loki.glow.data.repository.CoinRepositoryImpl
import com.loki.glow.domain.repository.CoinRepository
import com.loki.glow.domain.use_cases.CoinUseCase
import com.loki.glow.domain.use_cases.get_coin.GetCoinUseCase
import com.loki.glow.domain.use_cases.get_coins.GetCoinsUseCase
import com.loki.glow.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): CoinRankingApi {

        val okHttpClient = OkHttpClient().newBuilder()
            .addInterceptor {
                val request = it.request().newBuilder()
                    .apply {
                        addHeader("x-access-token", BuildConfig.API_KEY)
                    }.build()
                it.proceed(request)
            }.build()

        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(CoinRankingApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCoinRankingApi(api: CoinRankingApi): CoinRepository {
        return CoinRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideCoinUseCase(repository: CoinRepository): CoinUseCase {
        return CoinUseCase(
            getCoinList = GetCoinsUseCase(repository),
            getCoin = GetCoinUseCase(repository)
        )
    }
}