package com.usalama.usalamatechnology.shuga.activity

import android.os.Bundle
import com.usalama.usalamatechnology.shuga.BaseActivity
import com.usalama.usalamatechnology.shuga.R
import com.usalama.usalamatechnology.shuga.databinding.ActivityViewProfileBinding
import com.usalama.usalamatechnology.shuga.utils.launchActivity
import com.usalama.usalamatechnology.shuga.utils.makeNormalStatusBar
import com.usalama.usalamatechnology.shuga.utils.makeTransaprant
import com.usalama.usalamatechnology.shuga.utils.onClick

class ViewProfileActivity : BaseActivity() {
    private lateinit var binding: ActivityViewProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        makeNormalStatusBar()
        makeTransaprant()
        binding.ivEdit.onClick {
            launchActivity<ProfileActivity>()
        }
        binding.ivBack.onClick {
            onBackPressed()
        }
    }
}