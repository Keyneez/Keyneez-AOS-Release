package com.release.keyneez.data.repository

import com.release.keyneez.data.entity.response.ResponseGetContentDto
import com.release.keyneez.data.entity.response.ResponseGetLikeDto
import com.release.keyneez.data.entity.response.ResponseGetSearchResultDto
import com.release.keyneez.data.entity.response.wrapper.BaseResponse

interface ContentRepository {

    suspend fun getContent(): Result<BaseResponse<List<ResponseGetContentDto>>>
    suspend fun getLike(): Result<BaseResponse<List<ResponseGetLikeDto>>>
    suspend fun getSearch(keyword: String): Result<BaseResponse<List<ResponseGetSearchResultDto>>>
}
