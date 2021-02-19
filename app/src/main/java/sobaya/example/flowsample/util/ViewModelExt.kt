package sobaya.example.flowsample.util

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

fun <T> ViewModel.flowToMutableLiveData(flow: Flow<T>): MutableLiveData<T> {
    val liveData = MutableLiveData<T>()
    flow.onEach {
        liveData.postValue(it)
    }.launchIn(viewModelScope)

    return liveData
}

