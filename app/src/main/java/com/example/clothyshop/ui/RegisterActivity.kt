package com.example.clothyshop.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.example.clothyshop.R
import com.example.clothyshop.utils.NetworkUtils
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {

    private var isAllFieldsChecked: Boolean = false
    lateinit var firebaseAuth: FirebaseAuth
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        progressBar = findViewById(R.id.signup_progress)
        firebaseAuth = FirebaseAuth.getInstance()
        val txvSignIn = findViewById<TextView>(R.id.signin_txv)
        val btnSignUp = findViewById<Button>(R.id.signup_btn)

        btnSignUp.setOnClickListener {
            signUpUser()
        }
        txvSignIn.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

    private fun signUpUser() {
        val email = findViewById<EditText>(R.id.signup_email_edtx)
        val password = findViewById<EditText>(R.id.signup_password_etdx)
        val confirmPassword = findViewById<EditText>(R.id.signup_confirm_password_etdx)


        isAllFieldsChecked = NetworkUtils.checkAllFields(email, password, confirmPassword)
        if (isAllFieldsChecked) {
            progressBar.visibility = View.VISIBLE
            firebaseAuth.createUserWithEmailAndPassword(
                email.text.toString(),
                password.text.toString()
            ).addOnCompleteListener {
                    if (it.isSuccessful) {
                        progressBar.visibility = View.GONE
                        Toast.makeText(this, "Registered Successfully", Toast.LENGTH_SHORT).show()
                        /*startActivity(Intent(this, LoginActivity::class.java))
                        finish()*/
                        val i = Intent(this, LoginActivity::class.java)
                        i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(i)
                    } else {
                        progressBar.visibility = View.GONE
                        Toast.makeText(
                            this,
                            "SignUp Error:" + it.exception!!.message,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
            }
        }
    }
}