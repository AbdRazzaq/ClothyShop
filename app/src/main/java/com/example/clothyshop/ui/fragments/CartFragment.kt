package com.example.clothyshop.ui.fragments

import android.annotation.SuppressLint
import android.graphics.Paint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.clothyshop.R
import com.example.clothyshop.ui.adapter.CartAdapter
import com.example.clothyshop.viewmodel.CartViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartFragment : Fragment() {

    private val cartViewModel: CartViewModel by viewModels()
    private lateinit var adapter: CartAdapter
    private lateinit var rvCart: RecyclerView
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setRecyclerView()
        cartViewModel.cartOfUser.observe(viewLifecycleOwner, Observer {
            if (!it.equals(null)){
                progressBar.visibility = View.GONE
                /*shimmerFrameLayout.stopShimmerAnimation()
                shimmerFrameLayout.visibility = View.GONE
                profileLayout.visibility = View.VISIBLE*/
                adapter.setItems(ArrayList(it.products))
                val totalprice = requireView().findViewById<TextView>(R.id.totalamount)
                val discountedPrice = requireView().findViewById<TextView>(R.id.discountedTotal)
                totalprice.text = "$"+it.total.toString()
                totalprice.paintFlags = totalprice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG

                discountedPrice.text = "$"+ it.discountedTotal.toString()
            }
        })
    }

    private fun setRecyclerView() {
        progressBar = requireView().findViewById(R.id.progressBarCart)
        rvCart = requireView().findViewById(R.id.recyclerView_cart)
        rvCart.setHasFixedSize(true);
        rvCart.layoutManager = LinearLayoutManager(context)
        adapter = CartAdapter()
        rvCart.adapter = adapter
    }
}