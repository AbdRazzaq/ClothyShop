package com.example.clothyshop.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.clothyshop.R
import com.example.clothyshop.model.productdetails.ProductDetailsModel
import com.example.clothyshop.ui.adapter.SliderAdapterDetails
import com.example.clothyshop.viewmodel.DetailsViewModel
import com.facebook.shimmer.ShimmerFrameLayout
import com.smarteist.autoimageslider.SliderView
import dagger.hilt.android.AndroidEntryPoint
import java.util.ArrayList

@AndroidEntryPoint
class DetailsFragment : Fragment()  {

    //SmartiestAutoSlider
    private lateinit var imageUrl: ArrayList<String>
    lateinit var sliderView: SliderView
    lateinit var sliderAdapter: SliderAdapterDetails
    lateinit var shimmerFrameLayout : ShimmerFrameLayout
    lateinit var scrollView: ScrollView

    private val detailsViewModel: DetailsViewModel by viewModels()
    //lateinit var shimmerFrameLayout : ShimmerFrameLayout
    //lateinit var profileLayout : ConstraintLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        shimmerFrameLayout = requireView().findViewById(R.id.shimmerFrameLayout_productdetails)
        scrollView = requireView().findViewById(R.id.scrollView_productdetails)
        sliderView = requireView().findViewById(R.id.imageslider_details)
        imageUrl = ArrayList()

        val bundle = arguments
        val id = bundle!!.getInt("Id")

        val backArrow :ImageView = requireActivity().findViewById(R.id.details_back_arrow)
        backArrow.setOnClickListener {
            parentFragmentManager.popBackStackImmediate()
        }

        detailsViewModel.productDetails.observe(viewLifecycleOwner, Observer {
            if (!it.equals(null)){
                shimmerFrameLayout.stopShimmerAnimation()
                shimmerFrameLayout.visibility = View.GONE
                scrollView.visibility = View.VISIBLE
                setUpProductDetailsData(it)
            }
        })
        detailsViewModel.getProductDetails(id)
    }

    @SuppressLint("SetTextI18n")
    private fun setUpProductDetailsData(it: ProductDetailsModel) {
        val title : TextView = requireView().findViewById(R.id.details_title)
        val price : TextView = requireView().findViewById(R.id.details_price)
        val brand : TextView = requireView().findViewById(R.id.details_brand)
        val discount : TextView = requireView().findViewById(R.id.details_discount)
        val description : TextView = requireView().findViewById(R.id.details_description)
        val rating : RatingBar = requireView().findViewById(R.id.details_RatingBar)
        val ratingTextView : TextView = requireView().findViewById(R.id.details_RatingText)
        val addToBag : Button = requireView().findViewById(R.id.details_add_to_bag)

        imageUrl = it.images as ArrayList<String>

        sliderAdapter = SliderAdapterDetails(imageUrl)
        sliderView.setSliderAdapter(sliderAdapter)
        sliderView.isAutoCycle = false

        title.text = it.title
        price.text = "$"+it.price.toString()
        brand.text = it.brand
        discount.text = it.discountPercentage.toString()+"% off"
        description.text = it.description
        rating.rating = it.rating.toFloat()
        //ratingTextView.text = it.rating.toString()
        val ratingTxv :String = String.format("%.1f", it.rating)
        ratingTextView.text = ratingTxv

        addToBag.setOnClickListener{
            Toast.makeText(context,"Work in Progress", Toast.LENGTH_LONG).show()
        }
    }
}