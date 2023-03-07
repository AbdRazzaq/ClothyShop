package com.example.clothyshop.ui.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.example.clothyshop.R
import com.example.clothyshop.model.product.Product
import com.example.clothyshop.ui.fragments.DetailsFragment
import java.util.*


class CustomAdapter(private var productlist: ArrayList<Product>) :
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    private var items = ArrayList<String>()
    private var searchString: String = ""
    lateinit var fmanager: FragmentManager

    @SuppressLint("NotifyDataSetChanged")
    fun filterList(filterdNames: ArrayList<Product>, text: String, parentFragmentManager: FragmentManager) {
        this.searchString = text
        productlist = filterdNames
        this.fmanager = parentFragmentManager
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textViewName.text = productlist[position].title

        val item = productlist[position]
        val text2 = item.title.lowercase((Locale.getDefault()))
        val id = item.id

        if (text2.contains(searchString)) {
                val startPos: Int = text2.indexOf(searchString)
                val endPos = startPos + searchString.length
                val spanString =
                    Spannable.Factory.getInstance().newSpannable(holder.textViewName.text)
                spanString.setSpan(
                    ForegroundColorSpan(Color.BLUE),
                    startPos,
                    endPos,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                holder.textViewName.text = spanString
        }
        holder.itemView.setOnClickListener {
            val fragmentDetail = DetailsFragment()
            val bundle = Bundle()
            bundle.putInt("Id",id)
            fragmentDetail.arguments = bundle

            fmanager.beginTransaction().replace(R.id.flFragment, fragmentDetail).addToBackStack(null).commit()
        }
    }

    override fun getItemCount(): Int {
        Log.i("FilteredList", productlist.size.toString())
        return productlist.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textViewName: TextView

        init {
            textViewName = itemView.findViewById(R.id.textViewName)
        }
    }
}