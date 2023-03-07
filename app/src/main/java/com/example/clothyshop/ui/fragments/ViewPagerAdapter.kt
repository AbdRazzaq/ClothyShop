package com.example.clothyshop.ui.fragments


import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.example.clothyshop.R


class ViewPagerAdapter(context: Context) : PagerAdapter() {
    private val context: Context
    private var layoutInflater: LayoutInflater? = null
    private val images =
        arrayOf<Int>(R.drawable.fragrances1,R.drawable.mobiles1,R.drawable.laptops1,R.drawable.groceries1, R.drawable.skincare1, R.drawable.house)

    init {
        this.context = context
    }

    override fun getCount(): Int {
        return images.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    @SuppressLint("InflateParams")
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater?
        val view: View = layoutInflater!!.inflate(R.layout.viewpager_layout, null)
        //val view: View =  (context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater).inflate(R.layout.viewpager_layout, null)
        val imageView: ImageView = view.findViewById(R.id.imageViewViewPager) as ImageView
        //imageView.setImageResource(images[position])
        Glide.with(context)
            .load(images[position])
            .centerInside()
            .into(imageView);


        val vp = container as ViewPager
        vp.addView(view, 0)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val vp = container as ViewPager
        val view: View = `object` as View
        vp.removeView(view)
    }
}