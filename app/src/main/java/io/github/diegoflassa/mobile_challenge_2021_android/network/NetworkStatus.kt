package io.github.diegoflassa.mobile_challenge_2021_android.network

sealed class NetworkStatus {
    object Available : NetworkStatus()
    object Unavailable : NetworkStatus()
}
