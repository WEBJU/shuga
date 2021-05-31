package com.usalama.usalamatechnology.shuga.activity

import android.os.Bundle
import com.usalama.usalamatechnology.shuga.BaseActivity
import com.usalama.usalamatechnology.shuga.databinding.ActivitySignUpProfilePicBinding
import com.usalama.usalamatechnology.shuga.utils.launchActivity
import com.usalama.usalamatechnology.shuga.utils.onClick

class SignUpProfilePicActivity : BaseActivity() {
    private lateinit var binding: ActivitySignUpProfilePicBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpProfilePicBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.continuec.onClick { launchActivity<SubscriptionActivity> { } }
    }
}