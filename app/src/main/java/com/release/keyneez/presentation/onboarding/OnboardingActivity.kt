package com.release.keyneez.presentation.onboarding

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.AuthErrorCause
import com.kakao.sdk.user.UserApiClient
import com.release.keyneez.R
import com.release.keyneez.databinding.ActivityOnboardingBinding
import com.release.keyneez.util.binding.BindingActivity
import com.release.keyneez.util.extension.saveUserLoginProvider
import com.release.keyneez.util.extension.saveUserToken
import com.release.keyneez.util.extension.setOnSingleClickListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnboardingActivity :
    BindingActivity<ActivityOnboardingBinding>(R.layout.activity_onboarding) {

    private val viewModel: OnboardingViewModel by viewModels()

    private lateinit var resultLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel

        initResultLauncher()
        initOnboardingViewPager()

        val callback: (OAuthToken?, Throwable?) -> Unit = setKakaoCallback()

        binding.btnOnboardingLogin.setOnSingleClickListener {
            if (UserApiClient.instance.isKakaoTalkLoginAvailable(this)) {
                UserApiClient.instance.loginWithKakaoTalk(this, callback = callback)
            } else {
                UserApiClient.instance.loginWithKakaoAccount(this, callback = callback)
            }
        }
    }

    // kakao 로그인 팝업창
    private fun setKakaoCallback(): (OAuthToken?, Throwable?) -> Unit {
        val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
            if (error != null) { // 로그인 할 때 발생하는 에러
                when {
                    error.toString() == AuthErrorCause.AccessDenied.toString() -> {
                        Log.d("[카카오로그인]", "접근이 거부 됨(동의 취소)")
                    }

                    error.toString() == AuthErrorCause.InvalidClient.toString() -> {
                        Log.d("[카카오로그인]", "유효하지 않은 앱")
                    }

                    error.toString() == AuthErrorCause.InvalidGrant.toString() -> {
                        Log.d("[카카오로그인]", "인증 수단이 유효하지 않아 인증할 수 없는 상태")
                    }

                    error.toString() == AuthErrorCause.InvalidRequest.toString() -> {
                        Log.d("[카카오로그인]", "요청 파라미터 오류")
                    }

                    error.toString() == AuthErrorCause.InvalidScope.toString() -> {
                        Log.d("[카카오로그인]", "유효하지 않은 scope ID")
                    }

                    error.toString() == AuthErrorCause.Misconfigured.toString() -> {
                        Log.d("[카카오로그인]", "설정이 올바르지 않음(android key hash)")
                    }

                    error.toString() == AuthErrorCause.ServerError.toString() -> {
                        Log.d("[카카오로그인]", "서버 내부 에러")
                    }

                    error.toString() == AuthErrorCause.Unauthorized.toString() -> {
                        Log.d("[카카오로그인]", "앱이 요청 권한이 없음")
                    }

                    else -> { // Unknown
                        Log.d("[카카오로그인]", "기타 에러")
                    }
                }
            } else if (token != null) {
                // 로그인에 성공하면
                Log.d("[카카오로그인]", "로그인에 성공하였습니다.\n${token.accessToken}")

                // provider, token 저장
                saveUserLoginProvider(this, "Kakao")
                saveUserToken(this, token.accessToken)

                //                // 정보입력 인증 창으로 넘어가기
                //                val intent = Intent(this, SignUpActivity::class.java)
                //                startActivity(intent)
            }
        }
        return callback
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
}
