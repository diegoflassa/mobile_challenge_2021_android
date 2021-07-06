package io.github.diegoflassa.mobile_challenge_2021_android.helper

import io.github.diegoflassa.mobile_challenge_2021_android.data.entities.Patient
import io.github.diegoflassa.mobile_challenge_2021_android.data.entities.Patients

class SearchResult {
    lateinit var patients : Patients
    var lastSearchedPage = 0
}
