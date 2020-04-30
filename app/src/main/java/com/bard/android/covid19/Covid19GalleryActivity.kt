package com.bard.android.covid19

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.FrameLayout
import android.widget.ImageView
import com.bard.android.covid19.CountryGalleryFragment.*
import kotlinx.android.synthetic.main.list_item_gallery.*


private val TAG = "Activity"

class Covid19GalleryActivity : AppCompatActivity(), CountryGalleryFragment.Callbacks {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_covid19_gallery)

        val isFragmentContainerEmpty = savedInstanceState == null
        if (isFragmentContainerEmpty){
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragmentContainer, CountryGalleryFragment.newInstance())
                .commit()
        }
    }

/*    fun onClick(bundle: Bundle){
        //val newFragment = CountryFragment()
        //bundle args = Bundle()
        //args.putInt(CountryFragment.ARG_POSITION, position)
        //newFragment.arguments = args

        //setContentView(R.layout.activity_covid19_gallery)
        val transaction = supportFragmentManager.beginTransaction().apply {
            // Replace whatever is in the fragment_container view with this fragment,
            // and add the transaction to the back stack so the user can navigate back
            replace(R.id.fragmentContainer, newFragment)
            addToBackStack(null)
            Log.d(TAG, "Country selected from activity")
        }
    }*/

    override fun onCountrySelected(CountryData: GalleryItem){
        Log.d(TAG, "onCountrySelected called from mainActivity")
        val fragment = CountryFragment.newInstance(CountryData)
        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right)
            .replace(R.id.fragmentContainer, fragment)
            .addToBackStack(null)
            .commit()
    }
}
