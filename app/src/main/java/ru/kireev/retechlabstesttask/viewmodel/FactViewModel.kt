package ru.kireev.retechlabstesttask.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.kireev.retechlabstesttask.model.repository.FactRepository
import ru.kireev.retechlabstesttask.viewmodel.converter.convertFactModelApi

class FactViewModel(
    private val repository: FactRepository
) : ViewModel() {
    private val _data = MutableLiveData<AppState>()
    val data get(): LiveData<AppState> = _data

    private var disposable: Disposable? = null

    fun loadFact() {
        disposable?.dispose()
        _data.value = AppState.Loading
        disposable = repository.getFact()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map {
                convertFactModelApi(it)
            }
            .subscribe({
                _data.value = AppState.Success(it)
            }, {
                _data.value = AppState.Error(it)
            })
    }

    override fun onCleared() {
        disposable?.dispose()
        super.onCleared()
    }
}