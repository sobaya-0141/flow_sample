package sobaya.example.flowsample.util

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

class ErrorHandler {
    private val _error = MutableSharedFlow<Throwable>(1)
    val error: SharedFlow<Throwable> = _error

    suspend fun emitError(e: Throwable) = _error.emit(e)
}
