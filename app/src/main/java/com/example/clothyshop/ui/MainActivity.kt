package com.example.clothyshop.ui



import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import com.example.clothyshop.R
import com.example.clothyshop.ui.fragments.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity(), NavigationBarView.OnItemSelectedListener {

    private lateinit var bottomNavigationView: BottomNavigationView
    var homeFragment: HomeFragment = HomeFragment()
    var notificationsFragment: NotificationsFragment = NotificationsFragment()
    var profileFragment: ProfileFragment = ProfileFragment()
    var cartFragment: CartFragment = CartFragment()


    lateinit var actionBarDrawerToggle: ActionBarDrawerToggle


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpBottomNav()
    }

    private fun setUpBottomNav() {
        bottomNavigationView = findViewById(R.id.bottom_navigation)

        bottomNavigationView.setOnItemSelectedListener(this)
        bottomNavigationView.selectedItemId = R.id.home
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            true
        } else super.onOptionsItemSelected(item)
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
}