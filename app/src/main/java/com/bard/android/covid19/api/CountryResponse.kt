package com.bard.android.covid19.api

import com.bard.android.covid19.GalleryItem
import com.google.gson.annotations.SerializedName

//we Do not use this class our access can be granted through fewer steps

class CountryResponse {
    @SerializedName("Country")
    lateinit var galleryItems: List<GalleryItem>
}