package com.bard.android.covid19

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.drawable.Drawable
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import android.widget.*
import androidx.core.graphics.scaleMatrix
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

private const val TAG = "CountryGalleryFragment"

class CountryGalleryFragment: Fragment() {

    interface Callbacks{
        fun onCountrySelected(CountryData: GalleryItem)
    }

    private var callbacks: Callbacks? = null

    //private lateinit var imageView: ImageView
    private lateinit var countryGalleryViewModel: CountryGalleryViewModel
    private lateinit var countryRecyclerView: RecyclerView


    //these two functioncs added below
    override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks = context as Callbacks?
    }

    override fun onDetach() {
        super.onDetach()
        callbacks = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        countryGalleryViewModel =
            ViewModelProviders.of(this).get(CountryGalleryViewModel::class.java)

        //find scanner bar

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_covid19_gallery, container, false)

        countryRecyclerView = view.findViewById(R.id.country_recycler_view)
        countryRecyclerView.layoutManager = GridLayoutManager(context, 2)
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        countryGalleryViewModel.galleryItemLiveData.observe(
            viewLifecycleOwner,
            Observer{ galleryItems ->
                countryRecyclerView.adapter = CountryAdapter(galleryItems)
                }
        )
    }



    inner class CountryHolder(view: View): RecyclerView.ViewHolder(view), View.OnClickListener{

        private lateinit var CountryData: GalleryItem

        private val imageView: ImageView = itemView.findViewById(R.id.image_view)
        init{
            //imageView = itemView.findViewById(R.id.image_view)
            imageView.setOnClickListener(this)
        }

        fun bindImage(CountryData: GalleryItem, countryCode: String){
            this.CountryData = CountryData
            val id: Int = getResources().getIdentifier("com.bard.android.covid19:drawable/" + countryCode, null, null)
            //Picasso.get().load(id).into(imageView)
            imageView.setImageResource(id)
        }

        private var mp: MediaPlayer = MediaPlayer.create(context, R.raw.click)

        override fun onClick(v: View?) {
            mp.start()

            //onStartAnimation()

            callbacks?.onCountrySelected(CountryData)
            Log.d(TAG, "country clicked: ${CountryData.Country}")
        }
    }

    private inner class CountryAdapter(private val galleryItems: List<GalleryItem>) : RecyclerView.Adapter<CountryHolder>(){

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryHolder {
            val view = layoutInflater.inflate(R.layout.list_item_gallery, parent, false)

            return CountryHolder(view)
        }

        override fun getItemCount(): Int = galleryItems.size

        override fun onBindViewHolder(holder: CountryHolder, position: Int) {
            val galleryItem = galleryItems[position]
            holder.bindImage(galleryItem, galleryItem.CountryCode.toLowerCase())
        }


    }
    companion object {
        fun newInstance(): CountryGalleryFragment{
            return CountryGalleryFragment()
        }
    }
   /* fun onStartAnimation(){
            val valueAnimator = ValueAnimator.ofFloat(0f, 400f)

            valueAnimator.addUpdateListener {
                val value = it.animatedValue as Float

                imageView.cameraDistance = value
            }

            valueAnimator.interpolator = LinearInterpolator()
            valueAnimator.duration = 1000

            valueAnimator.start()
        }*/
}

