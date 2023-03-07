package com.example.clothyshop.ui.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.clothyshop.R
import com.example.clothyshop.model.product.Product
import com.example.clothyshop.ui.adapter.CustomAdapter
import com.example.clothyshop.ui.adapter.SliderAdapter
import com.example.clothyshop.viewmodel.MainViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.smarteist.autoimageslider.SliderView
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var customAdapter: CustomAdapter

    //For SearchEdtx
    lateinit var recyclerView: RecyclerView
    lateinit var editTextSearch: EditText
    private lateinit var productslist: ArrayList<Product>

    //SmartiestAutoSlider
    private lateinit var imageUrl: ArrayList<Int>
    lateinit var sliderView: SliderView
    lateinit var sliderAdapter: SliderAdapter

    lateinit var viewAll: TextView
    var viewAllProductsFragment: AllProductsFragment = AllProductsFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpSearchView()
        setUpSlideView()

        viewAll = requireView().findViewById(R.id.viewAll)
        viewAll.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.flFragment, viewAllProductsFragment).addToBackStack("home").commit()
        }
        val bottomNav = requireActivity().findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNav.isVisible = true
    }

    private fun setUpSearchView() {
        productslist = ArrayList()
        mainViewModel.products.observe(viewLifecycleOwner, Observer {
            if (it.products.isNotEmpty()) {
                for (i in 0 until it.products.size) {
                    // productslist.add(it.products[i].title)
                    productslist.add(it.products[i])
                }
            }
        })
        recyclerView = requireView().findViewById(R.id.searchRView)
        editTextSearch = requireView().findViewById(R.id.searchEditText)

        recyclerView.layoutManager = LinearLayoutManager(context)

        customAdapter = CustomAdapter(productslist)

        recyclerView.adapter = customAdapter

        editTextSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

            }

            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

            }

            override fun afterTextChanged(editable: Editable) {
                //after the change calling the method and passing the search input
                filter(editable.toString())
                recyclerView.visibility = View.VISIBLE
                if (editable.toString().isEmpty()) {
                    recyclerView.visibility = View.GONE
                }
            }
        })
    }

    private fun filter(text: String) {
        val filterdNames: ArrayList<Product> = ArrayList()
        for (s in productslist) {
            if (s.title.lowercase()
                    .contains(text.lowercase())
            ) {  //s.lowercase().contains(text.lowercase() Changed 13/2/2023 7:40 PM
                filterdNames.add(s)
            }
        }
        customAdapter.filterList(filterdNames, text, parentFragmentManager)
    }

    private fun setUpSlideView() {
        sliderView = requireView().findViewById(R.id.slider)
        imageUrl = ArrayList()

        imageUrl = ((imageUrl + R.drawable.mobiles1) as ArrayList<Int>)
        imageUrl = ((imageUrl + R.drawable.laptops1) as ArrayList<Int>)
        imageUrl = ((imageUrl + R.drawable.house) as ArrayList<Int>)
        imageUrl = ((imageUrl + R.drawable.groceries1) as ArrayList<Int>)
        imageUrl = ((imageUrl + R.drawable.skincare1) as ArrayList<Int>)
        imageUrl = ((imageUrl + R.drawable.fragrances1) as ArrayList<Int>)

        sliderAdapter = SliderAdapter(imageUrl)
        sliderView.autoCycleDirection = SliderView.LAYOUT_DIRECTION_LTR
        sliderView.setSliderAdapter(sliderAdapter)
        sliderView.scrollTimeInSec = 3
        sliderView.isAutoCycle = true
        sliderView.startAutoCycle()
    }
}