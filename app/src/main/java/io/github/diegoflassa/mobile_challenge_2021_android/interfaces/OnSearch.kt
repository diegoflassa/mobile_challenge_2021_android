package io.github.diegoflassa.mobile_challenge_2021_android.interfaces

import android.os.Parcelable
import io.github.diegoflassa.mobile_challenge_2021_android.enums.Gender
import kotlinx.android.parcel.Parcelize

interface OnSearch : Parcelable  {
  fun onSearch(
      query : String?, nationality:String?, gender : Gender = Gender.UNKNOWN)
  fun clear()
}
