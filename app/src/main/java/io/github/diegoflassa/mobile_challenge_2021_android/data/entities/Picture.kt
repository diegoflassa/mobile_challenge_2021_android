package io.github.diegoflassa.mobile_challenge_2021_android.data.entities

import android.net.Uri
import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
class Picture(
    @Json(name = LARGE)
    var largeUrl: Uri? = null,

    @Json(name = MEDIUM)
    var mediumUrl: Uri? = null,

    @Json(name = THUMBNAIL)
    var thumbnailUrl: Uri? = null,
) : Parcelable {
    companion object {
        const val LARGE: String = "large"
        const val MEDIUM: String = "medium"
        const val THUMBNAIL: String = "thumbnail"
    }
}
