package com.example.clothyshop.ui.fragments


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.clothyshop.ItemOffsetDecoration
import com.example.clothyshop.R
import com.example.clothyshop.model.product.Product
import com.example.clothyshop.ui.adapter.ShopAdapter
import com.example.clothyshop.utils.OnItemClickListenerInterface
import com.example.clothyshop.viewmodel.MainViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import java.util.*


@AndroidEntryPoint
class AllProductsFragment : Fragment() {

    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var adapter: ShopAdapter
    private lateinit var rvShop: RecyclerView
    private lateinit var progressBar: ProgressBar

    private lateinit var backArrow : ImageView

    //SearchEdtx
    lateinit var editTextSearch: EditText
    private var productslist: ArrayList<Product> = ArrayList()


    lateinit var listener: OnItemClickListenerInterface

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_all_products, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //editTextSearch = requireView().findViewById(R.id.searchEditText)
        setRecyclerView()
        mainViewModel.products.observe(viewLifecycleOwner, Observer {
            if (it.products.isNotEmpty()) {
                productslist = it.products as ArrayList<Product>
                progressBar.visibility = View.GONE
                adapter.setItems(ArrayList(it.products),parentFragmentManager)
            }
        })


        val bottomNav = requireActivity().findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNav.isGone = true

        backArrow = requireView().findViewById(R.id.back_arrow)
        backArrow.setOnClickListener {
            parentFragmentManager.popBackStackImmediate()
        }
    }
    private fun setRecyclerView() {
        rvShop = requireView().findViewById(R.id.recyclerView)
        progressBar = requireView().findViewById(R.id.progressBarAllProducts)

        rvShop.setHasFixedSize(true);
        rvShop.layoutManager = GridLayoutManager(context, 2)
        val itemOffsetDecoration = ItemOffsetDecoration(context, R.dimen.item_offset)
        rvShop.addItemDecoration(itemOffsetDecoration)
        adapter = ShopAdapter()
        rvShop.adapter = adapter

        /*editTextSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

            }
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

            }
            override fun afterTextChanged(editable: Editable) {
                //after the change calling the method and passing the search input
                filter(editable.toString())
                rvShop.visibility = View.VISIBLE
            }
        })*/

    }

    private fun filter(text: String) {
        val filterdNames: ArrayList<Product> = ArrayList()
        for (s in productslist) {

            if (s.title.lowercase(Locale.getDefault()).contains(text.lowercase())) {
                filterdNames.add(s)
            }
        }
        Log.i("FilteredList", filterdNames.toString())

        adapter.setItems(filterdNames,parentFragmentManager)
    }

}