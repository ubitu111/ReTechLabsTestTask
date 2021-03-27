package ru.kireev.retechlabstesttask.model.network

import io.reactivex.rxjava3.core.Single
import retrofit2.http.POST
import ru.kireev.retechlabstesttask.model.models.ImageModel

interface ImageApiService {
    @POST("meow")
    fun getImageUrl(): Single<ImageModel>
}