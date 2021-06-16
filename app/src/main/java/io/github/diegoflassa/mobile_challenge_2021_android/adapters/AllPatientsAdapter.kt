package io.github.diegoflassa.mobile_challenge_2021_android.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import io.github.diegoflassa.mobile_challenge_2021_android.R
import io.github.diegoflassa.mobile_challenge_2021_android.data.entities.Patient
import io.github.diegoflassa.mobile_challenge_2021_android.databinding.RecyclerviewItemPatientBinding
import io.github.diegoflassa.mobile_challenge_2021_android.ui.allPatients.AllPatientsFragmentDirections

open class AllPatientsAdapter(
    private val patients: List<Patient>,
) : RecyclerView.Adapter<AllPatientsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ViewHolder {
        val binding = RecyclerviewItemPatientBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        holder.bind(patients[position])
    }

    class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        private val binding = RecyclerviewItemPatientBinding.bind(itemView)
        fun bind(
            patient: Patient?,
        ) {
            val resources = itemView.resources

            if (patient != null) {
                // Load image
                binding.title.text = resources.getString(R.string.rv_name, patient.fullName.first)
            }
            // Click listener
            itemView.setOnClickListener {
                it.findNavController().navigate(
                    AllPatientsFragmentDirections.actionNavHomeToPatientDetailsFragment(patient)
                )
            }
        }
    }

    override fun getItemCount(): Int {
        return patients.size
    }
}
