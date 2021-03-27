package ru.kireev.retechlabstesttask.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.kireev.retechlabstesttask.model.repository.ImageRepository

class ImageViewModel(
    private val repository: ImageRepository
) : ViewModel() {

    private val _data = MutableLiveData<AppState>()
    val data get(): LiveData<AppState> = _data

    private var disposable: Disposable? = null

    fun loadImageUrl() {
        disposable?.dispose()
        _data.value = AppState.Loading
        disposable = repository.getImageUrl()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _data.value = AppState.Success(it.file)
            }, {
                _data.value = AppState.Error(it)
            })
    }

    override fun onCleared() {
        disposable?.dispose()
        super.onCleared()
    }
}