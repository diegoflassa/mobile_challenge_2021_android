package io.github.diegoflassa.mobile_challenge_2021_android.models

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.diegoflassa.mobile_challenge_2021_android.data.entities.Patient
import io.github.diegoflassa.mobile_challenge_2021_android.data.repository.PatientsRepository
import io.github.diegoflassa.mobile_challenge_2021_android.helper.Constants
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

// @HiltViewModel
class AllPatientsFragmentViewModel /*@Inject*/ constructor(
    private val handle: SavedStateHandle,
) : ViewModel() {

    companion object {
        private val tag = AllPatientsFragmentViewModel::class.simpleName
        private const val key = "patients"
    }

    private var _patients: MutableLiveData<List<Patient>> = MutableLiveData(ArrayList())

    init {
        Log.i(tag, "AllPatientsFragmentViewModel.init")
        handle.set(key, _patients)
        loadItems()
    }

    private fun loadItems() {
        val patientsRepo = PatientsRepository()
        viewModelScope.launch {
            patientsRepo.getAllPaginated(1, Constants.DEFAULT_PAGE_SIZE_MOBILE)
                .catch { exception -> Log.e(tag, exception.toString()) }
                .map {
                    _patients.postValue(it)
                    handle.set(key, _patients)
                }
        }
    }

    fun reloadData() {
        loadItems()
    }

    val patientsLiveData: MutableLiveData<List<Patient>>
        get(): MutableLiveData<List<Patient>> {
            return handle.get<MutableLiveData<List<Patient>>>(key)!!
        }

    val patients: List<Patient>
        get(): List<Patient> {
            return handle.get<MutableLiveData<List<Patient>>>(key)!!.value!!
        }
}
