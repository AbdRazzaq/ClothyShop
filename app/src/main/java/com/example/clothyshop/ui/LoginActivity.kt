package com.example.clothyshop.ui

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.example.clothyshop.R
import com.example.clothyshop.utils.NetworkUtils
import com.example.clothyshop.utils.NetworkUtils.Companion.checkAllFields
import com.google.firebase.auth.FirebaseAuth


class LoginActivity : AppCompatActivity() {

    private var isAllFieldsChecked: Boolean = false
    lateinit var firebaseAuth: FirebaseAuth
    private lateinit var progressBar: ProgressBar

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        progressBar = findViewById(R.id.signin_progress)
        firebaseAuth = FirebaseAuth.getInstance()
        val btnSignIn = findViewById<Button>(R.id.signin_btn)

        btnSignIn.setOnClickListener {
            login()
        }

        val signup = findViewById<TextView>(R.id.create_account_txv)
        signup.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
            finish()
        }
    }

    private fun login() {
        val email = findViewById<EditText>(R.id.signin_email_edtx)
        val password = findViewById<EditText>(R.id.signin_password_etdx)

        isAllFieldsChecked = checkAllFields(email, password)
        if (isAllFieldsChecked) {
            progressBar.visibility = View.VISIBLE
            val emailTxt: String = email.text.toString()
            val pwdText: String = password.text.toString()
            firebaseAuth.signInWithEmailAndPassword(emailTxt, pwdText)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        progressBar.visibility = View.GONE
                        /*startActivity(Intent(this, MainActivity::class.java))
                        finish()*/
                        val intent = Intent(this, MainActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(intent)
                    } else {
                        progressBar.visibility = View.GONE
                        Toast.makeText(
                            this,
                            "SignIn Error:" + it.exception!!.message,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }
    }

    private fun checkAllFields(email: EditText, password: EditText): Boolean {
        if (TextUtils.isEmpty(email.text.toString())) {
            email.error = "Email cannot be empty"
            email.requestFocus()
            return false
        }
        if (TextUtils.isEmpty(password.text.toString())) {
            password.error = "Password cannot be empty"
            password.requestFocus()
            return false
        }
        return true
    }
}