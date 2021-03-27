package ru.kireev.retechlabstesttask.model.repository

import io.reactivex.rxjava3.core.Single
import ru.kireev.retechlabstesttask.model.models.FactModelApi
import ru.kireev.retechlabstesttask.model.network.FactApiService

class FactRepositoryImpl(private val api: FactApiService) : FactRepository {
    override fun getFact(): Single<FactModelApi> {
        return api.getFact()
    }
}