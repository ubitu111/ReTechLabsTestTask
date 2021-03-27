package ru.kireev.retechlabstesttask.di

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import org.koin.androidx.fragment.dsl.fragment
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.kireev.retechlabstesttask.model.network.FactApiService
import ru.kireev.retechlabstesttask.model.network.ImageApiService
import ru.kireev.retechlabstesttask.model.repository.FactRepository
import ru.kireev.retechlabstesttask.model.repository.FactRepositoryImpl
import ru.kireev.retechlabstesttask.model.repository.ImageRepository
import ru.kireev.retechlabstesttask.model.repository.ImageRepositoryImpl
import ru.kireev.retechlabstesttask.view.FactsFragment
import ru.kireev.retechlabstesttask.view.ImageFragment
import ru.kireev.retechlabstesttask.viewmodel.FactViewModel
import ru.kireev.retechlabstesttask.viewmodel.ImageViewModel

val fragmentModule = module {
    fragment { ImageFragment(get()) }
    fragment { FactsFragment(get()) }
}

val viewModelModule = module {
    viewModel { ImageViewModel(get()) }
    viewModel { FactViewModel(get()) }
}

val apiModule = module {
    fun provideImageApiService(): ImageApiService {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://aws.random.cat/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
        return retrofit.create(ImageApiService::class.java)
    }

    fun provideFactApiService(): FactApiService {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://cat-fact.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
        return retrofit.create(FactApiService::class.java)
    }

    single { provideImageApiService() }
    single { provideFactApiService() }
}

val repositoryModule = module {
    fun provideImageRepository(
        api: ImageApiService
    ): ImageRepository {
        return ImageRepositoryImpl(api)
    }

    fun provideFactRepository(
        api: FactApiService
    ): FactRepository {
        return FactRepositoryImpl(api)
    }

    single { provideImageRepository(get()) }
    single { provideFactRepository(get()) }
}

val appModules = listOf(fragmentModule, viewModelModule, apiModule, repositoryModule)