package sobaya.example.flowsample.registration

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject
import sobaya.example.flowsample.repository.SampleRepository
import sobaya.example.flowsample.share.ShareItem
import sobaya.example.flowsample.util.flowToMutableLiveData

class RegistrationViewModel : ViewModel(), KoinComponent {
    private val repository: SampleRepository by inject()

    private val shareItem: ShareItem by inject()

    private val isMorning = MutableStateFlow(true)

    private val response = isMorning.flatMapLatest {
        repository.getInputData(it)
    }

    private val stateResponse = response.stateIn(viewModelScope, SharingStarted.Eagerly, null)

    private val shareResponse = response.shareIn(viewModelScope, SharingStarted.Eagerly, 0)

    init {
        shareResponse.onEach {
            shareItem.emitSample(listOf(it.input1.length, it.input2.length))
        }.launchIn(viewModelScope)
    }

    val edit1 = flowToMutableLiveData(shareResponse.map { it.input1 })
    val edit2 = flowToMutableLiveData(shareResponse.map { it.input2 })

    val buttonMorningEnabled = isMorning.map { !it }.asLiveData()
    val buttonNightEnabled = isMorning.map { it }.asLiveData()

    fun onClickTiming(morning: Boolean) {
        isMorning.value = morning
    }
}
