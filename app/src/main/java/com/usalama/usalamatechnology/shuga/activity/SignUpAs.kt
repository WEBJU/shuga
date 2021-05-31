package com.usalama.usalamatechnology.shuga.activity

import android.os.Bundle
import androidx.core.content.ContextCompat
import com.usalama.usalamatechnology.shuga.BaseActivity
import com.usalama.usalamatechnology.shuga.R
import com.usalama.usalamatechnology.shuga.databinding.ActivitySignUpAsBinding
import com.usalama.usalamatechnology.shuga.utils.launchActivity
import com.usalama.usalamatechnology.shuga.utils.onClick

class SignUpAs : BaseActivity() {
    private lateinit var binding: ActivitySignUpAsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpAsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.ivBack.onClick {
            onBackPressed()
        }
        binding.sponsor.onClick {
            binding.sponsor.setBackgroundColor(ContextCompat.getColor(context, R.color.shuga_red))
            binding.sugarBaby.setBackgroundColor(ContextCompat.getColor(context, R.color.white))

//            binding.sugarBaby.setBackgroundColor(R.color.white)
//            binding.sugarBaby.setTextColor(R.color.da_textColorPrimary)
        }
        binding.sugarBaby.onClick {
            binding.sugarBaby.setBackgroundColor(ContextCompat.getColor(context, R.color.shuga_red))
            binding.sponsor.setBackgroundColor(ContextCompat.getColor(context, R.color.white))

//            binding.sponsor.setBackgroundColor(R.color.white)
//            binding.sponsor.setTextColor(R.color.da_textColorPrimary)
        }

        binding.continuec.onClick { launchActivity<SignUpDetailsActivity> { } }
    }
}