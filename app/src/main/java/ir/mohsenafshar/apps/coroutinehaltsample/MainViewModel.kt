package ir.mohsenafshar.apps.coroutinehaltsample

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ir.mohsenafshar.apps.coroutinehaltsample.di.RemoteRepositoryQualifier
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor(@RemoteRepositoryQualifier private val remoteRepository: Repository) : ViewModel() {

    private val _userLiveData = MutableLiveData<List<User>>()
    val userLiveData: LiveData<List<User>>
        get() = _userLiveData


    private var tapCount = 0

    private val _taps = MutableLiveData<String>()
    val taps: LiveData<String>
        get() = _taps

    fun updateTaps() {
        // launch a coroutine in viewModelScope
        viewModelScope.launch {
            tapCount++
            // suspend this coroutine for one second
            delay(5_000)
            // resume in the main dispatcher
            // _snackbar.value can be called directly from main thread
            _taps.postValue("$tapCount taps")

            Log.d("Logger", "updateTaps " + this.toString())
        }
    }

    fun requestData() {
        viewModelScope.launch {
            val dataList = remoteRepository.getList()

            _userLiveData.postValue(dataList)
            Log.d("Logger", "requestData " + this.toString())
        }
    }

}

data class User(var name: String, var age: Int)