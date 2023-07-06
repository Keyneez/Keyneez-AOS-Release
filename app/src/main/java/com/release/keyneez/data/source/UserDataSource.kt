package com.release.keyneez.data.source

import com.release.keyneez.data.service.UserService
import javax.inject.Inject

class UserDataSource @Inject constructor(
    private val userService: UserService
)
