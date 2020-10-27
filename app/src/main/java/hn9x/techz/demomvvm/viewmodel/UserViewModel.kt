package hn9x.techz.demomvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import hn9x.techz.demomvvm.api.ApiBuilder
import hn9x.techz.demomvvm.base.BaseViewModel
import hn9x.techz.demomvvm.model.RepositoriesEntity
import hn9x.techz.demomvvm.model.UserEntity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class UserViewModel: BaseViewModel() {
    private val TAG = UserViewModel::class.java.simpleName
    private val userResponse = MutableLiveData<UserEntity>()
    private var repositoriesResponse = MutableLiveData<ArrayList<RepositoriesEntity>>()
    private val disposables = CompositeDisposable()

    fun showUserInfo(): MutableLiveData<UserEntity> {
        return userResponse
    }

    fun showRepositories(): MutableLiveData<ArrayList<RepositoriesEntity>> {
        return repositoriesResponse
    }

    fun getUserInfo(userId: String) {
        disposables.add(ApiBuilder.getWebService().getUser(userId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { showLoading(true) }
            .doFinally { showLoading(false) }
            .subscribe({
                userResponse.value = it
            }, {
                showFailure(it)
            }))
    }

    fun getRepositories(userId: String) {
        disposables.add(ApiBuilder.getWebService().getRepositories(userId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { showLoading(true) }
            .doFinally { showLoading(false) }
            .subscribe({
                repositoriesResponse.value = it
            }, {
                showFailure(it)
            }))
    }

    override fun onCleared() {
        disposables.clear()
    }
}