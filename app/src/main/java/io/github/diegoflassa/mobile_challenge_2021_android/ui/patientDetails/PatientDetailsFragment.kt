package io.github.diegoflassa.mobile_challenge_2021_android.ui.patientDetails

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import coil.load
import io.github.diegoflassa.mobile_challenge_2021_android.R
import io.github.diegoflassa.mobile_challenge_2021_android.data.entities.Patient
import io.github.diegoflassa.mobile_challenge_2021_android.databinding.FragmentPatientDetailsBinding
import io.github.diegoflassa.mobile_challenge_2021_android.helper.viewLifecycle
import io.github.diegoflassa.mobile_challenge_2021_android.models.PatientDetailsFragmentViewModel
import java.text.SimpleDateFormat
import java.util.*

private const val ARG_PATIENT = "patient"

/**
 * A simple [Fragment] subclass.
 * Use the [PatientDetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PatientDetailsFragment : Fragment() {

    private var binding: FragmentPatientDetailsBinding by viewLifecycle()
    private lateinit var viewModel: PatientDetailsFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPatientDetailsBinding.inflate(inflater, container, false)
        arguments?.let {
            val patient: Patient? = it.getParcelable(ARG_PATIENT)
            if (patient != null) {
                viewModel.patient = patient
            }
        }
        Log.i(tag, "PatientDetailsFragment.onCreateView")
        showPatient()
        return binding.root
    }

    private fun showPatient() {
        if (viewModel.patient != null) {
            binding.avatar.load(viewModel.patient!!.picture.mediumUrl) { placeholder(R.drawable.image_placeholder) }
            var sanitizedTitle = "Unavailable"
            if (viewModel.patient!!.fullName.getFullName()
                .isNotEmpty()
            ) {
                sanitizedTitle = viewModel.patient!!.fullName.getFullName()
            }
            binding.title.text = getString(R.string.dt_name, sanitizedTitle)
            var sanitizedDescription = "Unavailable"
            if (viewModel.patient!!.address.getFullAddress().isNotEmpty()) {
                sanitizedDescription = viewModel.patient!!.address.getFullAddress()
            }
            binding.description.text = getString(R.string.dt_address, sanitizedDescription)
            val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
            val formattedDate = formatter.format(viewModel.patient!!.dateOfBirth)
            binding.creationDate.text = getString(R.string.dt_email, formattedDate)
            binding.openLink.isEnabled = true
            Log.i(PatientDetailsFragment.tag, "PatientDetailsFragment.showPatient")
        }
    }

    companion object {
        private val tag = PatientDetailsFragment::class.simpleName

        @JvmStatic
        fun newInstance(patient: Patient) =
            PatientDetailsFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_PATIENT, patient)
                }
            }
    }
}
