package com.bard.android.covid19

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_country.*
import java.util.Arrays.setAll

//private const val ARG_COUNTRY_DATA = "country Data"

class CountryFragment(CountryData: GalleryItem): Fragment(){
    //this is not being inited when the fragement tries to swap

    private var CountryData: GalleryItem = CountryData

    private lateinit var CountryField: TextView

    private lateinit var TotalConfirmedField: TextView
    private lateinit var TotalDeathsField: TextView
    private lateinit var TotalRecoveredField: TextView

    private lateinit var NewConfirmedField: TextView
    private lateinit var NewDeathsField: TextView
    private lateinit var NewRecoveredField: TextView

    private lateinit var DateField: TextView

/*    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val CountryData: GalleryItem = arguments?.getSerializable(ARG_COUNTRY_DATA) as GalleryItem
    }*/


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_country, container, false)

        CountryField = view.findViewById(R.id.country) as TextView

        TotalConfirmedField = view.findViewById(R.id.total_confirmed) as TextView
        TotalDeathsField = view.findViewById(R.id.total_deaths) as TextView
        TotalRecoveredField = view.findViewById(R.id.total_recovered) as TextView

        NewRecoveredField = view.findViewById(R.id.new_recovered) as TextView
        NewConfirmedField = view.findViewById(R.id.new_confirmed) as TextView
        NewDeathsField = view.findViewById(R.id.new_deaths) as TextView

        DateField = view.findViewById(R.id.date) as TextView

        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        CountryField.setText(CountryData.Country)

        TotalConfirmedField.setText(CountryData.TotalConfirmed)
        TotalDeathsField.setText(CountryData.TotalDeaths)
        TotalRecoveredField.setText(CountryData.TotalRecovered)

        NewConfirmedField.setText(CountryData.NewConfirmed)
        NewDeathsField.setText(CountryData.NewDeaths)
        NewRecoveredField.setText(CountryData.NewRecovered)

        DateField.setText(CountryData.Date)
    }

    companion object{

        fun newInstance(galleryItem: GalleryItem): CountryFragment{
            val args = Bundle().apply{
                //putAll(Bundle())
            }

            return CountryFragment(galleryItem).apply{
                arguments = args
            }
        }
    }
}
