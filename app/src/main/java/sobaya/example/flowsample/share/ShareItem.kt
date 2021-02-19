package sobaya.example.flowsample.share

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

class ShareItem {
    private val _sample = MutableSharedFlow<List<Int>>(1)
    val sample: SharedFlow<List<Int>> = _sample

    suspend fun emitSample(args: List<Int>) = _sample.emit(args)
}
