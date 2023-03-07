package com.example.clothyshop.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.clothyshop.R
import com.example.clothyshop.model.profile.ProfileModelClass
import com.example.clothyshop.ui.LoginActivity
import com.example.clothyshop.viewmodel.MainViewModel
import com.facebook.shimmer.ShimmerFrameLayout
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private val mainViewModel: MainViewModel by viewModels()
    lateinit var shimmerFrameLayout : ShimmerFrameLayout
    lateinit var profileLayout :ConstraintLayout
    lateinit var firebaseAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        shimmerFrameLayout = requireView().findViewById(R.id.shimmerFrameLayout)
        profileLayout = requireView().findViewById(R.id.profile_layout)
        val logout = requireView().findViewById<TextView>(R.id.logout_tv)

        firebaseAuth = FirebaseAuth.getInstance()

        mainViewModel.user.observe(viewLifecycleOwner, Observer {
            if (!it.equals(null)){
                shimmerFrameLayout.stopShimmerAnimation()
                shimmerFrameLayout.visibility = View.GONE
                profileLayout.visibility = View.VISIBLE
                setUpProfileData(it)
            }
        })
        logout.setOnClickListener {
            firebaseAuth.signOut()
            startActivity(Intent(activity,LoginActivity::class.java))
            //parentFragmentManager.popBackStackImmediate()
            activity?.finish()
        }
    }

    private fun setUpProfileData(profileModelClass: ProfileModelClass) {
        val profile : ImageView = requireView().findViewById(R.id.profileimage)
        val name : TextView = requireView().findViewById(R.id.username)
        val email : TextView = requireView().findViewById(R.id.useremail)
        val number : TextView = requireView().findViewById(R.id.number)


        Glide.with(this)
            .load(profileModelClass.image)
            .override(300,400)
            .placeholder(R.drawable.placeholder_shop)
            .error(R.drawable.error_image)
            .into(profile)
        name.text = profileModelClass.firstName
        email.text = profileModelClass.email
        number.text = profileModelClass.phone
    }
    override fun onResume() {
        super.onResume()
        shimmerFrameLayout.startShimmerAnimation()
    }

    override fun onPause() {
        shimmerFrameLayout.stopShimmerAnimation()
        super.onPause()
    }
}