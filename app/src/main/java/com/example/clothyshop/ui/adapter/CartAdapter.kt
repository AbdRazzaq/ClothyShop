package com.example.clothyshop.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.clothyshop.R
import com.example.clothyshop.model.cart.CartProduct

class CartAdapter : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    private var cartItems = ArrayList<CartProduct>()
    @SuppressLint("NotifyDataSetChanged")
    fun setItems(items: ArrayList<CartProduct>) {
        // this.items.clear()
        //this.items.addAll(items)
        this.cartItems = items
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        return CartAdapter.CartViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.cart_layout, parent, false)
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val item = cartItems[position]
        val cartTitle : TextView = holder.itemView.findViewById(R.id.cart_title)
        val quantity : TextView = holder.itemView.findViewById(R.id.cart_qty)
        val price : TextView = holder.itemView.findViewById(R.id.cart_price)

        cartTitle.text = item.title
        quantity.text = "Qty :"+item.quantity.toString()
        price.text = "$"+item.discountedPrice.toString()
    }

    override fun getItemCount(): Int {
        return cartItems.size
    }
    class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener{
        override fun onClick(p0: View?) {

        }
    }
}