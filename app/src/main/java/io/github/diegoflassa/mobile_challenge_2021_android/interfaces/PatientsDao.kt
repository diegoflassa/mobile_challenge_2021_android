package io.github.diegoflassa.mobile_challenge_2021_android.interfaces

import io.github.diegoflassa.mobile_challenge_2021_android.data.entities.Patient
import io.github.diegoflassa.mobile_challenge_2021_android.helper.Constants
import io.github.diegoflassa.mobile_challenge_2021_android.helper.SearchResults
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query

interface PatientsDao {

    @GET("/api/?page={page}&results={results}{unusedFields}{seed}")
    fun getAllPaginated(
        @Query("page") page: Int,
        @Query("results") pageSize: Int,
        @Query("exc") unusedFields: String,
        @Query("seed") seed: String,
    ): Flow<List<Patient>>

    @GET("/api/?page={page}&results={pageSize}{getUnusedFields}{seed}{query}")
    fun findAllPaginated(
        query: String,
        @Query("page") page: Int = 1,
        @Query("pageSize") pageSize: Int = Constants.DEFAULT_PAGE_SIZE_MOBILE,
        startPage: Int?,
        maxPageToSearch: Int?,
        previousResults: SearchResults?,
        @Query("exc") unusedFields: String,
        @Query("seed") seed: String,
    ): Flow<List<Patient>>
}
