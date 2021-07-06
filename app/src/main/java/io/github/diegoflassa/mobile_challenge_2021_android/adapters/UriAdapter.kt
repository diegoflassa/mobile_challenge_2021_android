package io.github.diegoflassa.mobile_challenge_2021_android.adapters

import android.net.Uri
import com.squareup.moshi.*


class UriAdapter : JsonAdapter<Uri>() {
    @ToJson
    override fun toJson(writer: JsonWriter, value: Uri?) {
        value?.let { writer.value(it.toString()) }

    }

    @FromJson
    override fun fromJson(reader: JsonReader): Uri? {
        return if (reader.peek() != JsonReader.Token.NULL) {
            Uri.parse(reader.nextString())
        } else {
            null
        }
    }

}

