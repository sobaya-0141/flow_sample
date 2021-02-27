package sobaya.example.flowsample

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import sobaya.example.flowsample.registration.RegistrationViewModel
import sobaya.example.flowsample.repository.SampleRepository
import sobaya.example.flowsample.share.ShareItem
import sobaya.example.flowsample.util.ErrorHandler

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(listOf(
                module {
                    viewModel { RegistrationViewModel() }
                    single { SampleRepository() }
                    single { ShareItem() }
                    single { ErrorHandler() }
                }
            ))
        }
    }
}
