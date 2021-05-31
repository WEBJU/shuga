package com.usalama.usalamatechnology.shuga.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.usalama.usalamatechnology.shuga.BaseActivity
import com.usalama.usalamatechnology.shuga.R
import com.usalama.usalamatechnology.shuga.databinding.ActivitySignUpDetailsBinding
import com.usalama.usalamatechnology.shuga.utils.launchActivity
import com.usalama.usalamatechnology.shuga.utils.onClick
import kotlinx.android.synthetic.main.activity_sign_up_details.*


class SignUpDetailsActivity : BaseActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_details)
        auth = FirebaseAuth.getInstance()

        continuer.setOnClickListener{
            signupuser()
        }


    }
    private fun signupuser() {
        if (email.text.toString().isEmpty()){
            email.error = "Enter your Email"
            email.requestFocus()
            return
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email.text.toString()).matches()){
            email.error = "Enter a valid email"
            email.requestFocus()
            return
        }
        if (password.text.toString().isEmpty()){
            password.error = "Enter a Password"
            password.requestFocus()
            return
        }

        auth.createUserWithEmailAndPassword(email.text.toString(), password.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    user?.sendEmailVerification()
                        ?.addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                startActivity(Intent(this, LoginActivity::class.java))
                                finish()
                            }
                        }

                } else {
                    Toast.makeText(baseContext, "Sign up Failed. Try again Later.",
                    Toast.LENGTH_SHORT).show()
                }
            }

    }
}