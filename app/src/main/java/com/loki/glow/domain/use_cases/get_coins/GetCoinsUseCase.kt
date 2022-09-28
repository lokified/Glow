package com.loki.glow.domain.use_cases.get_coins

import com.loki.glow.data.remote.dto.toCoin
import com.loki.glow.domain.model.Coin
import com.loki.glow.domain.repository.CoinRepository
import com.loki.glow.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class GetCoinsUseCase(
    private val repository: CoinRepository
) {

    operator fun invoke(): Flow<Resource<List<Coin>>> = flow {

        try {
            emit(Resource.Loading<List<Coin>>())
            val coins = repository.getCoinList().data.coins.map { it.toCoin() }
            emit(Resource.Success<List<Coin>>(data = coins))
        }
        catch (e: HttpException) {
            emit(Resource.Error<List<Coin>>(e.localizedMessage ?: "An unexpected error occurred"))
        }
        catch (e: IOException) {
            emit(Resource.Error<List<Coin>>("check your internet connection"))
        }
    }
}