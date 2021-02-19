package sobaya.example.flowsample.repository

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class SampleRepository {

    fun getInputData(isMorning: Boolean) = flow {
        Log.d("DDDDDDDDDDDDd", "AAAAA")
        emit(RegistrationResponse("INPUT1${isMorning}", "INPUT2${isMorning}"))
    }.catch { print(it.message) }.flowOn(Dispatchers.IO)
}
