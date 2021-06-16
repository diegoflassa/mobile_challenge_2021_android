package io.github.diegoflassa.mobile_challenge_2021_android.models

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import io.github.diegoflassa.mobile_challenge_2021_android.data.entities.Patient

// @HiltViewModel
class PatientDetailsFragmentViewModel /*@Inject*/ constructor(
    private val handle: SavedStateHandle
) : ViewModel() {

    companion object {
        private val tag = PatientDetailsFragmentViewModel::class.simpleName
        private const val key = "patient"
    }

    init {
        handle.set<Patient>(key, Patient())
        Log.i(tag, "PatientDetailsFragmentViewModel.init")
    }

    val patientLiveData: MutableLiveData<Patient>
        get(): MutableLiveData<Patient> {
            return handle.getLiveData(key)
        }
    var patient: Patient?
        get(): Patient? {
            return handle.get<Patient>(key)
        }
        set(value) {
            handle.set<Patient>(key, value)
        }
}
