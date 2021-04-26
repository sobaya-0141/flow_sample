package sobaya.example.flowsample.registration

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.wada811.databinding.dataBinding
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.ldralighieri.corbind.widget.textChanges
import sobaya.example.flowsample.R
import sobaya.example.flowsample.databinding.FragmentRegistrationBinding
import sobaya.example.flowsample.util.makeNotificationSettingIntent

class RegistrationFragment() : Fragment(R.layout.fragment_registration) {

    private val binding: FragmentRegistrationBinding by dataBinding()
    private val viewModel: RegistrationViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        viewModel.buttonMorningEnabled.observe(viewLifecycleOwner, Observer { it.toString() })

        binding.button.setOnClickListener {
            startActivity(makeNotificationSettingIntent(requireContext()))
        }
    }

    private fun inputEvent() {
        binding.edit1.textChanges().debounce(500).onEach {
            viewModel.request()
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }
}
