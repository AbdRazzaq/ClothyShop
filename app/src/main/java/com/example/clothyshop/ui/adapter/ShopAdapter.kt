package com.example.clothyshop.ui.adapter

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.clothyshop.model.product.Product
import com.example.clothyshop.ui.fragments.DetailsFragment
import com.example.clothyshop.utils.OnItemClickListenerInterface
import com.example.clothyshop.R


class ShopAdapter : RecyclerView.Adapter<ShopAdapter.ShopViewHolder>() {


    private var items = ArrayList<Product>()
    lateinit var productImage : ImageView
    lateinit var tvName : TextView
    lateinit var tvPrice : TextView
    lateinit var ratingTextView : TextView
    lateinit var ratingBar : RatingBar

    lateinit var listener: OnItemClickListenerInterface
    var detailsFragment: DetailsFragment = DetailsFragment()
    lateinit var fmanager: FragmentManager

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(items: ArrayList<Product>, parentFragmentManager: FragmentManager) {
       // this.items.clear()
        //this.items.addAll(items)
        this.fmanager = parentFragmentManager
        this.items = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopViewHolder {
        return ShopViewHolder(
            LayoutInflater.from(parent.context).inflate(com.example.clothyshop.R.layout.shopitem, parent, false)
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ShopViewHolder, position: Int) {
        //val item = differ.currentList[position]
        val item = items[position]

        val productImage : ImageView = holder.itemView.findViewById(com.example.clothyshop.R.id.itemImage)
        val tvName : TextView = holder.itemView.findViewById(com.example.clothyshop.R.id.itemName)
        val tvPrice : TextView = holder.itemView.findViewById(com.example.clothyshop.R.id.itemPrice)
        //val tvDescription : TextView = holder.itemView.findViewById(R.id.itemDescription)
        val ratingBar : RatingBar = holder.itemView.findViewById(com.example.clothyshop.R.id.itemRatingBar)
        val ratingTextView : TextView = holder.itemView.findViewById(com.example.clothyshop.R.id.itemRatingText)

        val id = item.id

        tvName.text = item.title
        tvPrice.text = "Price : $" + item.price.toString()
        //tvDescription.text = item.description
        Glide.with(holder.itemView.context)
            .load(item.images[0])
            .override(300,400)
            .placeholder(com.example.clothyshop.R.drawable.placeholder_shop)
            .error(com.example.clothyshop.R.drawable.error_image)
            .into(productImage)

        ratingBar.rating = item.rating.toFloat()
        //ratingTextView.text = item.rating.toString()

        val rating :String = String.format("%.1f", item.rating)
        ratingTextView.text = rating

        holder.itemView.setOnClickListener {


            val fragmentDetail = DetailsFragment()
            val bundle = Bundle()
            bundle.putInt("Id",id)
            fragmentDetail.arguments = bundle

            fmanager.beginTransaction().replace(R.id.flFragment, fragmentDetail).addToBackStack(null).commit()
        }
    }

    override fun getItemCount(): Int {
        //return differ.currentList.size
        return items.size
    }

    class ShopViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener{
        override fun onClick(p0: View?) {

        }
    }
}