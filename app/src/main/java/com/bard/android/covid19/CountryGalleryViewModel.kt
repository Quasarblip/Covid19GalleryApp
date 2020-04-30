package com.bard.android.covid19

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class CountryGalleryViewModel: ViewModel() {

    val galleryItemLiveData: LiveData<List<GalleryItem>>

    init{
        galleryItemLiveData = Covid19Fetcher().fetchCovidData()
    }

}