package io.github.diegoflassa.mobile_challenge_2021_android.models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import io.github.diegoflassa.mobile_challenge_2021_android.network.NetworkStatusTracker
import io.github.diegoflassa.mobile_challenge_2021_android.network.map
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

sealed class MyState {
    object Fetched : MyState()
    object Error : MyState()
}

// @HiltViewModel
class NetworkStatusViewModel/*@Inject*/ @ExperimentalCoroutinesApi constructor(
    // private val handle: SavedStateHandle,
    networkStatusTracker: NetworkStatusTracker,
) : ViewModel() {

    @FlowPreview
    @ExperimentalCoroutinesApi
    val state =
        networkStatusTracker.networkStatus.map(
            onUnavailable = { MyState.Error },
            onAvailable = { MyState.Fetched },
        )
            .asLiveData(Dispatchers.IO)
}
