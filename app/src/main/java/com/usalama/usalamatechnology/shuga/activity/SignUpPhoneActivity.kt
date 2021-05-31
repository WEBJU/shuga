package com.usalama.usalamatechnology.shuga.activity

import android.app.ProgressDialog
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthProvider
import com.usalama.usalamatechnology.shuga.BaseActivity
import com.usalama.usalamatechnology.shuga.databinding.ActivitySignUpPhoneBinding
import com.usalama.usalamatechnology.shuga.utils.launchActivity
import com.usalama.usalamatechnology.shuga.utils.onClick

class SignUpPhoneActivity : BaseActivity() {

    private lateinit var binding: ActivitySignUpPhoneBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpPhoneBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.continued.onClick { launchActivity<OtpActivity> { } }
    }
}