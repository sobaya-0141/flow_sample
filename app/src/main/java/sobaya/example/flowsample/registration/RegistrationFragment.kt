package sobaya.example.flowsample.registration

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.wada811.databinding.dataBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import sobaya.example.flowsample.R
import sobaya.example.flowsample.databinding.FragmentRegistrationBinding

class RegistrationFragment() : Fragment(R.layout.fragment_registration) {

    private val binding: FragmentRegistrationBinding by dataBinding()
    private val viewModel: RegistrationViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        viewModel.buttonMorningEnabled.observe(viewLifecycleOwner, Observer { it.toString() })
    }
}
