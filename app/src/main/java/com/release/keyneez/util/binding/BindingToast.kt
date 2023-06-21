package com.release.keyneez.util.binding

import android.content.Context
import android.content.res.Resources
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.release.keyneez.R
import com.release.keyneez.databinding.ToastLikeBinding

object BindingToast {

    fun initLikeDeleteToast(context: Context, message: String): Toast? {
        val inflater = LayoutInflater.from(context)
        val binding: ToastLikeBinding =
            DataBindingUtil.inflate(inflater, R.layout.toast_like, null, false)

        binding.tvLikeToast.text = message

        return Toast(context).apply {
            setGravity(Gravity.TOP or Gravity.START, 400, 550)
            duration = Toast.LENGTH_SHORT
            view = binding.root
        }
    }
    private fun Int.toPx(): Int = (this * Resources.getSystem().displayMetrics.density).toInt()
}
