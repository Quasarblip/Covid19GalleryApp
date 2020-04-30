package com.bard.android.covid19.api

import com.bard.android.covid19.GalleryItem
import com.google.gson.annotations.SerializedName

class Covid19Response {
    lateinit var Countries: List<GalleryItem>
}