package com.example.clothyshop.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.text.TextUtils
import android.util.Patterns
import android.widget.EditText

class NetworkUtils {
    companion object{
        fun isInternetAvailable(context: Context): Boolean{
            (context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).run {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    return this.getNetworkCapabilities(this.activeNetwork)?.hasCapability(
                        NetworkCapabilities.NET_CAPABILITY_INTERNET
                    ) ?: false
                } else {
                    (@Suppress("DEPRECATION")
                    return this.activeNetworkInfo?.isConnected ?: false)
                }
            }
        }

        fun checkAllFields(
            email: EditText,
            password: EditText,
            confirmPassword: EditText
        ): Boolean {
            if (TextUtils.isEmpty(email.text.toString())) {
                email.error = "Email cannot be empty"
                email.requestFocus()
                return false
            } else if (!Patterns.EMAIL_ADDRESS.matcher(email.text).matches()){
                email.error = "Invalid Email"
                email.requestFocus()
                return false
            }else if (TextUtils.isEmpty(password.text.toString()) || password.text.length < 6) {
                password.error = "Password length should be greater than 6"
                password.requestFocus()
                return false
            }else if (TextUtils.isEmpty(confirmPassword.text.toString())) {
                confirmPassword.error = "Confirm Password cannot be empty"
                confirmPassword.requestFocus()
                return false
            } else if (confirmPassword.text.toString() != password.text.toString()) {
                confirmPassword.error = "Password and Confirm Password should be same"
                confirmPassword.requestFocus()
                return false
            }
            return true
        }
    }
}