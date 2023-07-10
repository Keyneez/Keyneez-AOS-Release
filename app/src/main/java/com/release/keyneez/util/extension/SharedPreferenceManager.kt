package com.release.keyneez.util.extension

import android.content.Context
import androidx.appcompat.app.AppCompatActivity

fun saveUserLoginProvider(context: Context, provider: String) {
    val spf = context.getSharedPreferences("provider", AppCompatActivity.MODE_PRIVATE)
    val editor = spf.edit()

    editor.putString("provider", provider)
    editor.apply()
}

fun getUserLoginProvider(context: Context): String {
    val spf = context.getSharedPreferences("provider", AppCompatActivity.MODE_PRIVATE)

    return spf.getString("provider", "")!!
}

fun saveUserToken(context: Context, token: String) {
    val spf = context.getSharedPreferences("token", AppCompatActivity.MODE_PRIVATE)
    val editor = spf.edit()

    editor.putString("token", token)
    editor.apply()
}

fun getUserToken(context: Context): String {
    val spf = context.getSharedPreferences("token", AppCompatActivity.MODE_PRIVATE)

    return spf.getString("token", "")!!
}

fun saveUserJwt(context: Context, jwt: String) {
    val spf = context.getSharedPreferences("jwt", AppCompatActivity.MODE_PRIVATE)
    val editor = spf.edit()

    editor.putString("jwt", "Bearer $jwt")
    editor.apply()
}

fun getUserJwt(context: Context): String {
    val spf = context.getSharedPreferences("jwt", AppCompatActivity.MODE_PRIVATE)

    return spf.getString("jwt", "")!!
}
