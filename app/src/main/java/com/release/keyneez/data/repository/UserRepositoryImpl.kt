package com.release.keyneez.data.repository

import com.release.keyneez.data.source.LocalPrefDataSource
import com.release.keyneez.data.source.UserDataSource
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDataSource: UserDataSource,
    private val localPrefDataSource: LocalPrefDataSource
) : UserRepository