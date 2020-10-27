package hn9x.techz.demomvvm.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import hn9x.techz.demomvvm.api.BaseResponse
import hn9x.techz.demomvvm.viewmodel.Event

open class BaseViewModel : ViewModel() {
    val eventLoading = MutableLiveData<Event<Boolean>>()
    val eventError = MutableLiveData<Event<BaseResponse>>()
    val eventFailure = MutableLiveData<Event<Throwable>>()

    fun showLoading(value: Boolean) {
        eventLoading.value = Event(value)
    }

    fun showError(baseResponse: BaseResponse) {
        eventError.value = Event(baseResponse)
    }

    fun showFailure(throwable: Throwable) {
        eventFailure.value = Event(throwable)
    }
}