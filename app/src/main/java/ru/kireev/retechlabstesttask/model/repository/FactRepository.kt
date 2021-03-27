package ru.kireev.retechlabstesttask.model.repository

import io.reactivex.rxjava3.core.Single
import ru.kireev.retechlabstesttask.model.models.FactModelApi

interface FactRepository {
    fun getFact(): Single<FactModelApi>
}