package bobby.irawan.githubsearcher.ui.viewmodel

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import bobby.irawan.githubsearcher.repository.GithubSearchRepositoryContract
import bobby.irawan.githubsearcher.ui.modelview.GithubSearchItemModelView
import bobby.irawan.githubsearcher.utils.Constant.EMPTY
import bobby.irawan.githubsearcher.utils.Constant.NETWORK_TIMEOUT
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.net.UnknownHostException
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * Created by bobbyirawan09 on 22/04/20.
 */
class MainActivityViewModel(private val repository: GithubSearchRepositoryContract) :
    ViewModel() {

    private var currentPage = 1
    private val compositeDisposable = CompositeDisposable()
    private var githubUsers = mutableListOf<GithubSearchItemModelView>()
    private var saveQuery: String = EMPTY

    private val _githubUsersLiveData = MutableLiveData<MutableList<GithubSearchItemModelView>>()
    val githubUsersLiveData: LiveData<MutableList<GithubSearchItemModelView>>
        get() = _githubUsersLiveData

    private val _loadingAdapterItemLiveData = MutableLiveData<Int>()
    val loadingAdapterItemLiveData: LiveData<Int>
        get() = _loadingAdapterItemLiveData

    private val _emptyViewLiveData = MutableLiveData<Int>()
    val emptyViewLiveData: LiveData<Int>
        get() = _emptyViewLiveData

    private val _errorMessageLiveData = MutableLiveData<String>()
    val errorMessageLiveData: LiveData<String>
        get() = _errorMessageLiveData

    private val _loadingListLiveData = MutableLiveData<Int>()
    val loadingListLiveData: LiveData<Int>
        get() = _loadingListLiveData

    private val _isFirstDataLiveData = MutableLiveData<Boolean>()
    val isFirstDataLiveData: LiveData<Boolean>
        get() = _isFirstDataLiveData

    fun callApi(
        query: String = EMPTY,
        fetchNextResult: Boolean = false
    ) {
        if (isQueryNotSame(query)) {
            compositeDisposable.clear()
            val useQuery = getQuery(query, fetchNextResult)
            val disposable =
                repository.callApi(useQuery.toLowerCase(Locale.getDefault()), currentPage)
                    .subscribeOn(Schedulers.io())
                    .timeout(NETWORK_TIMEOUT, TimeUnit.SECONDS)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        _loadingListLiveData.value = View.GONE
                        if (fetchNextResult) {
                            _isFirstDataLiveData.value = false
                            _loadingAdapterItemLiveData.value = View.GONE
                            githubUsers.addAll(it)
                        } else {
                            _isFirstDataLiveData.value = true
                            githubUsers = it
                        }
                        _githubUsersLiveData.postValue(it)
                        _emptyViewLiveData.value = View.GONE
                    }, {
                        val errorMessage: String
                        _loadingAdapterItemLiveData.value = View.GONE
                        _loadingListLiveData.value = View.GONE
                        when {
                            it is UnknownHostException -> errorMessage =
                                "Anda tidak terhubung dengan jaringan"
                            else -> errorMessage = it.message.toString()
                        }
                        if (!fetchNextResult) {
                            githubUsers.clear()
                            _githubUsersLiveData.postValue(githubUsers)
                            _emptyViewLiveData.value = View.VISIBLE
                        }
                        _errorMessageLiveData.value = errorMessage
                    })
            compositeDisposable.add(disposable)
        } else {
            setLoadingListVisibility(View.GONE)
        }
    }

    private fun isQueryNotSame(query: String): Boolean =
        if (!query.equals(saveQuery, ignoreCase = true)) true else false

    fun showLoadingAdapterItem() {
        _loadingAdapterItemLiveData.value = View.VISIBLE
    }

    fun setLoadingListVisibility(visiblity: Int) {
        _loadingListLiveData.value = visiblity
    }

    private fun getQuery(query: String, fetchNextResult: Boolean): String {
        _emptyViewLiveData.value = View.GONE
        return if (fetchNextResult) {
            currentPage++
            saveQuery
        } else {
            _loadingListLiveData.value = View.VISIBLE
            saveQuery = query
            currentPage = 1
            githubUsers.clear()
            query
        }
    }

    fun isDataEmpty(result: MutableList<GithubSearchItemModelView>, isFirstTime: Boolean) {
        if (result.isEmpty() && isFirstTime) {
            _emptyViewLiveData.value = View.VISIBLE
        } else if (result.isEmpty() && !isFirstTime) {
            _errorMessageLiveData.value = "Data baru tidak ditemukan"
        }
    }

}