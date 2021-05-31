package com.usalama.usalamatechnology.shuga.activity

import android.app.Dialog
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import com.usalama.usalamatechnology.shuga.BaseActivity
import com.usalama.usalamatechnology.shuga.R
import com.usalama.usalamatechnology.shuga.databinding.ActivityProfileBinding
import com.usalama.usalamatechnology.shuga.utils.invalidateButton
import com.usalama.usalamatechnology.shuga.utils.onClick
import com.usalama.usalamatechnology.shuga.utils.updateGenderButton
import kotlinx.android.synthetic.main.da_item_addphoto.*

class ProfileActivity : BaseActivity(), TextWatcher {
    private lateinit var binding: ActivityProfileBinding
    private fun showAddPhotoDialog() {
        val dialog = Dialog(this)
        dialog.window?.setBackgroundDrawable(ColorDrawable(0))
        dialog.setContentView(R.layout.da_item_addphoto)
        dialog.window?.setLayout(
            FrameLayout.LayoutParams.MATCH_PARENT,
            FrameLayout.LayoutParams.WRAP_CONTENT
        )
        dialog.tvCamera.onClick {
            // launchActivity<DACameraActivity>()
        }
        dialog.tvCameraRoll.onClick {
            // launchActivity<DACameraActivity>()
        }
        dialog.show()

    }


    private var cardClickListener = View.OnClickListener { showAddPhotoDialog() }
    private var genderClickListener = View.OnClickListener {
        updateGenderButton(it as ImageView, selected)
        selected = it
    }
    private var selected: ImageView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.edtUsername.addTextChangedListener(this)
        binding.edtAboutMe.addTextChangedListener(this)
        binding.cardProfile1.setOnClickListener(cardClickListener)
        binding.cardProfile2.setOnClickListener(cardClickListener)
        binding.cardProfile3.setOnClickListener(cardClickListener)
        binding.cardProfile4.setOnClickListener(cardClickListener)
        binding.cardProfile5.setOnClickListener(cardClickListener)
        binding.cardProfile6.setOnClickListener(cardClickListener)
        binding.ivFemale.setOnClickListener(genderClickListener)
        binding.ivMale.setOnClickListener(genderClickListener)
        binding.ivOther.setOnClickListener(genderClickListener)

        binding.ivMale.performClick()
        binding.ivBack.onClick {
            onBackPressed()
        }
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        TODO("Not yet implemented")
        invalidateButton(p0.toString().isNotEmpty(), binding.btnSave)

    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        TODO("Not yet implemented")
    }

    override fun afterTextChanged(p0: Editable?) {
        TODO("Not yet implemented")
    }
}