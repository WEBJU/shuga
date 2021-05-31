package com.usalama.usalamatechnology.shuga.activity

import android.os.Bundle
import com.usalama.usalamatechnology.shuga.BaseActivity
import com.usalama.usalamatechnology.shuga.databinding.ActivitySecurityBinding

class SecurityActivity : BaseActivity() {
    private lateinit var binding: ActivitySecurityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecurityBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}