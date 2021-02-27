package sobaya.example.flowsample.repository

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.koin.core.KoinComponent
import org.koin.core.inject
import sobaya.example.flowsample.util.ErrorHandler

class SampleRepository : KoinComponent {

    private val errorHander: ErrorHandler by inject()

    fun getInputData(isMorning: Boolean) = flow {
        Log.d("DDDDDDDDDDDDd", "AAAAA")
        emit(RegistrationResponse("INPUT1${isMorning}", "INPUT2${isMorning}"))
    }.catch { errorHander.emitError(it) }.flowOn(Dispatchers.IO)
}
