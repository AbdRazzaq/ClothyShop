package com.example.clothyshop.ui


import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.clothyshop.R
import com.example.clothyshop.ui.adapter.ShopAdapter
import com.example.clothyshop.ui.adapter.SliderAdapter
import com.example.clothyshop.ui.fragments.*
import com.example.clothyshop.viewmodel.MainViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import com.smarteist.autoimageslider.SliderView
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity(), NavigationBarView.OnItemSelectedListener {

    private lateinit var bottomNavigationView: BottomNavigationView
    var homeFragment: HomeFragment = HomeFragment()
    var settingsFragment: NotificationsFragment = NotificationsFragment()
    var notificationsFragment: NotificationsFragment = NotificationsFragment()
    var profileFragment: ProfileFragment = ProfileFragment()
    var cartFragment: CartFragment = CartFragment()

    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var adapter: ShopAdapter
    private lateinit var rvShop: RecyclerView
    private lateinit var progressBar: ProgressBar

    //SmartiestAutoSlider
    private lateinit var imageUrl: ArrayList<Int>
    lateinit var sliderView: SliderView
    lateinit var sliderAdapter: SliderAdapter

    //Nav-Drawer
    lateinit var drawerLayout: DrawerLayout
    lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    private lateinit var sidenav_imageView: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpActionBar()
        //setUpSlideView()
        setUpBottomNav()
        /*setRecyclerView()

        mainViewModel.res.observe(this, Observer {
            if (it.isNotEmpty()) {
                progressBar.visibility = View.GONE
                adapter.setItems(ArrayList(it.subList(0, it.size)))
            }
        })*/
        /*onBackPressedDispatcher.addCallback(this) {
            // Back is pressed... Finishing the activity
            supportFragmentManager.popBackStack()
        }*/
    }

    private fun setUpBottomNav() {
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnItemSelectedListener(this);
        bottomNavigationView.selectedItemId = R.id.home

        //For Bottom-Nav Rounded corners
        /*val radius = resources.getDimension(R.dimen.radius_small)
        val bottomNavigationViewBackground = bottomNavigationView.background as MaterialShapeDrawable
        bottomNavigationViewBackground.shapeAppearanceModel =
            bottomNavigationViewBackground.shapeAppearanceModel.toBuilder()
                .setTopRightCorner(CornerFamily.ROUNDED, radius)
                .setTopLeftCorner(CornerFamily.ROUNDED, radius)
                .build()*/


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            true
        } else super.onOptionsItemSelected(item)
    }

    private fun setUpActionBar() {
        val colorDrawable = ColorDrawable(Color.parseColor("#EEEEEE"))
        // Calling the support action bar and setting it to custom
        //this.supportActionBar!!.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM

        // Displaying the custom layout in the ActionBar
        /*supportActionBar!!.setDisplayShowCustomEnabled(true)
        supportActionBar!!.setCustomView(R.layout.actionbar_layout)*/

        //supportActionBar!!.setBackgroundDrawable(colorDrawable)

        /*supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM;
        supportActionBar?.setCustomView(R.layout.actionbar_layout)*/
        /*supportActionBar!!.elevation = 0F
        supportActionBar?.title = HtmlCompat.fromHtml("<font color='#6200EE'>" + getString(R.string.app_name) + "</font>", HtmlCompat.FROM_HTML_MODE_LEGACY)*/
    }

    private fun setUpSlideView() {
        //SmartiestAutoSliderCode
        sliderView = findViewById(R.id.slider)
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

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.home -> {
                supportFragmentManager.beginTransaction().replace(R.id.flFragment, homeFragment)
                    .commit()
                return true
            }
            R.id.notification -> {
                supportFragmentManager.beginTransaction().replace(R.id.flFragment, notificationsFragment)
                    .commit()
                return true
            }
            R.id.profile -> {
                supportFragmentManager.beginTransaction().replace(R.id.flFragment, profileFragment)
                    .commit()
                return true
            }
            R.id.cart -> {
                supportFragmentManager.beginTransaction().replace(R.id.flFragment, cartFragment)
                    .commit()
                return true
            }
        }
        return false
    }
    /*private fun setRecyclerView() {
        rvShop = findViewById(R.id.recyclerView)
        progressBar = findViewById(R.id.progress)
        adapter = ShopAdapter()

        rvShop.layoutManager = GridLayoutManager(this, 2)
        val itemOffsetDecoration = ItemOffsetDecoration(this, R.dimen.item_offset)
        rvShop.addItemDecoration(itemOffsetDecoration)
        rvShop.adapter = adapter
    }*/
}