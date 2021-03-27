package ru.kireev.retechlabstesttask.model.repository

import io.reactivex.rxjava3.core.Single
import ru.kireev.retechlabstesttask.model.models.ImageModel
import ru.kireev.retechlabstesttask.model.network.ImageApiService

class ImageRepositoryImpl(
    private val apiService: ImageApiService
) : ImageRepository {
    override fun getImageUrl(): Single<ImageModel> {
        return apiService.getImageUrl()
    }
}