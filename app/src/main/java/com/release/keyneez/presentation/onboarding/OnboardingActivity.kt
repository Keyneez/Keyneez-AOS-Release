package com.release.keyneez.presentation.onboarding

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.release.keyneez.R
import com.release.keyneez.databinding.ActivityOnboardingBinding
import com.release.keyneez.util.binding.BindingActivity

class OnboardingActivity :
    BindingActivity<ActivityOnboardingBinding>(R.layout.activity_onboarding) {

    private val viewModel: OnboardingViewModel by viewModels()

    private lateinit var resultLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel

        initResultLauncher()
        initOnboardingViewPager()
    }

    private fun initResultLauncher() {
        resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
                // signup or login success
                if (result.resultCode == Activity.RESULT_OK) {
                    finish()
                }
            }
    }

    private fun initOnboardingViewPager() {
        val viewPager = binding.vpOnboarding
        val dotIndicator = binding.tabOnboardingDot
        viewPager.adapter = OnboardingAdapter(this)
        dotIndicator.attachTo(viewPager)

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int,
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)

                viewModel.setPosition(position)
            }
        })
    }

//    private fun initLoginBtnClickListener() {
//        binding.btnOnboardingLogin.setOnSingleClickListener {
//            val toLogin = Intent(this, LoginActivity::class.java)
//            resultLauncher.launch(toLogin)
//        }
//    }
}
