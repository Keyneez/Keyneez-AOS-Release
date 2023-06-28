package com.release.keyneez.data.repository

import com.release.keyneez.data.entity.response.ResponseGetSearchResultDto
import com.release.keyneez.data.entity.response.wrapper.BaseResponse

interface ContentRepository {
    suspend fun getSearch(keyword: String): Result<BaseResponse<List<ResponseGetSearchResultDto>>>
}
