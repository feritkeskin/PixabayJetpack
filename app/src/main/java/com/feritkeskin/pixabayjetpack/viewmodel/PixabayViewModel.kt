package com.feritkeskin.pixabayjetpack.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.feritkeskin.pixabayjetpack.model.Model
import com.feritkeskin.pixabayjetpack.service.PixabayAPIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class PixabayViewModel : ViewModel() {

    private val pixabayAPIService = PixabayAPIService()
    private var disposable = CompositeDisposable()
    private var _image = MutableLiveData<Model>()
    val image: LiveData<Model>
        get() = _image

    fun getData(search: String) {
        disposable.add(
            pixabayAPIService.getData(search)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<Model>() {
                    override fun onSuccess(t: Model) {
                        _image.value = t
                    }

                    override fun onError(e: Throwable) {
                        println("Error ${e.message}")
                    }
                })
        )
    }
}