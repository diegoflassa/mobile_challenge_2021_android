package io.github.diegoflassa.mobile_challenge_2021_android.data.repository

import android.util.Log
import io.github.diegoflassa.mobile_challenge_2021_android.data.entities.Patient
import io.github.diegoflassa.mobile_challenge_2021_android.enums.QueryFields
import io.github.diegoflassa.mobile_challenge_2021_android.helper.Constants
import io.github.diegoflassa.mobile_challenge_2021_android.helper.SearchResults
import io.github.diegoflassa.mobile_challenge_2021_android.interfaces.PatientsDao
import kotlinx.coroutines.flow.Flow
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.*

class PatientsRepository {

    private val tag: String? = PatientsRepository::class.simpleName
    private val patientsBaseUrl = "https://randomuser.me"

    fun getAllPaginated(
        page: Int,
        pageSize: Int
    ): Flow<List<Patient>> {
        val okHttpClient = OkHttpClient()
        val retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(patientsBaseUrl)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
        Log.i(tag, "PatientsRepository.getAll")
        return retrofit.create(PatientsDao::class.java)
            .getAllPaginated(page, pageSize, getUnusedField(), getSeed())
    }

    fun findAllPaginated(
        query: Map<QueryFields, String>,
        page: Int = 1,
        pageSize: Int = Constants.DEFAULT_PAGE_SIZE_MOBILE,
        startPage: Int?,
        maxPageToSearch: Int?,
        previousResults: SearchResults?
    ): Flow<List<Patient>> {
        val okHttpClient = OkHttpClient()
        val retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(patientsBaseUrl)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
        Log.i(tag, "PatientsRepository.getAll")
        val containsGender = query.containsKey(QueryFields.GENDER)
        val seed = ""
        if (containsGender) {
            getSeed()
        }
        return retrofit.create(PatientsDao::class.java)
            .findAllPaginated(
                getQueryString(query),
                page,
                pageSize,
                startPage,
                maxPageToSearch,
                previousResults,
                getUnusedField(),
                seed
            )
    }

    private fun getQueryString(query: Map<QueryFields, String>?): String {
        var ret = ""
        if (query != null && query.isNotEmpty()) {
            for (field in query.keys) {
                when (field) {
                    QueryFields.FULL_NAME -> {
                    }
                    // Do nothing
                    QueryFields.GENDER -> {
                        ret += "&gender=${query[field]!!.lowercase(Locale.getDefault())}"
                    }
                    QueryFields.NATIONALITY -> {
                        ret += "&nat=${query[field]}"
                    }
                    QueryFields.UNKNOWN -> {
                    }
                }
            }
        }
        return ret
    }

    private fun getUnusedField(): String {
        return "exc=login, registered"
    }

    private fun getSeed(): String {
        return "&seed=42"
    }
}
