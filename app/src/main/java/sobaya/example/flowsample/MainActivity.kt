package sobaya.example.flowsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.collect
import org.koin.android.ext.android.inject
import sobaya.example.flowsample.share.ShareItem
import sobaya.example.flowsample.util.ErrorHandler

class MainActivity : AppCompatActivity() {
    private val shareItem: ShareItem by inject()
    private val errorHandler: ErrorHandler by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lifecycleScope.launchWhenStarted {
            shareItem.sample.collect {
                print(it.size)
            }
        }

        lifecycleScope.launchWhenStarted {
            errorHandler.error.collect {
                print(it.message)
            }
        }
    }
}
