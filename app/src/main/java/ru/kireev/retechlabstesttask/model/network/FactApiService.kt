package ru.kireev.retechlabstesttask.model.network

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import ru.kireev.retechlabstesttask.model.models.FactModelApi

interface FactApiService {
    @GET("facts/random")
    fun getFact(): Single<FactModelApi>
}