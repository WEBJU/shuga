package com.usalama.usalamatechnology.shuga.activity

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.usalama.usalamatechnology.shuga.BaseActivity
import com.usalama.usalamatechnology.shuga.R
import com.usalama.usalamatechnology.shuga.databinding.ActivityLoginBinding
import com.usalama.usalamatechnology.shuga.utils.launchActivity
import com.usalama.usalamatechnology.shuga.utils.onClick
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.email
import kotlinx.android.synthetic.main.activity_login.password
import kotlinx.android.synthetic.main.activity_sign_up_details.*

class LoginActivity : BaseActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        auth = FirebaseAuth.getInstance()

        register.setOnClickListener {
            startActivity(Intent (this,SignUpActivity::class.java))
            finish()
        }
        login.setOnClickListener{
            doLogin()
        }


//        setContentView(binding.root)
//        binding.login.onClick { launchActivity<DashboardActivity> { } }
//        binding.register.onClick { launchActivity<SignUpActivity> { } }
    }

    private fun doLogin() {
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

        auth.signInWithEmailAndPassword(email.text.toString(), password.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    
                    updateUI(null)
                }
            }
    }
//    public override fun onStart() {
//        super.onStart()
//        // Check if user is signed in (non-null) and update UI accordingly.
//        val currentUser = auth.currentUser
//        updateUI (currentUser)
//    }

    private fun updateUI(currentUser: FirebaseUser?){
         if(currentUser !=null) {
             if (currentUser.isEmailVerified){
                 startActivity(Intent(this, DashboardActivity::class.java))
                 finish()
             }else{
                 Toast.makeText(
                     baseContext, "please verify your email address",
                     Toast.LENGTH_SHORT
                 ).show()
             }

         } else {
             Toast.makeText(
                 baseContext, "Login Failed. Incorrect username or password",
                 Toast.LENGTH_SHORT
             ).show()
         }
    }
}