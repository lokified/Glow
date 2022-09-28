package com.loki.glow.domain.use_cases.get_coin

import com.loki.glow.data.remote.dto.CoinX
import com.loki.glow.data.remote.dto.toCoinDetail
import com.loki.glow.domain.model.CoinDetail
import com.loki.glow.domain.repository.CoinRepository
import com.loki.glow.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class GetCoinUseCase (
    private val repository: CoinRepository
) {

    operator fun invoke(coinId: String): Flow<Resource<CoinDetail>> = flow {

        try {
            emit(Resource.Loading<CoinDetail>())
            val coins = repository.getCoinDetail(coinId).data.coin.toCoinDetail()
            emit(Resource.Success<CoinDetail>(data = coins))
        }
        catch (e: HttpException) {
            emit(Resource.Error<CoinDetail>(e.localizedMessage ?: "An unexpected error occurred"))
        }
        catch (e: IOException) {
            emit(Resource.Error<CoinDetail>("check your internet connection"))
        }
    }
}