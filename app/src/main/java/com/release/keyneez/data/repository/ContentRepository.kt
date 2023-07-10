package com.release.keyneez.data.repository

import com.release.keyneez.data.entity.response.ResponseGetContentDto
import com.release.keyneez.data.entity.response.ResponseGetLikeDto
import com.release.keyneez.data.entity.response.ResponseGetSearchResultDto
import com.release.keyneez.data.entity.response.ResponsePostLikeDto
import com.release.keyneez.data.entity.response.wrapper.BaseResponse

interface ContentRepository {

    suspend fun getContent(filter: String): Result<BaseResponse<List<ResponseGetContentDto>>>
    suspend fun getLike(filter: String): Result<BaseResponse<List<ResponseGetLikeDto>>>
    suspend fun getSearch(keyword: String): Result<BaseResponse<List<ResponseGetSearchResultDto>>>
    suspend fun postLike(pk: Int): Result<BaseResponse<ResponsePostLikeDto>>
    suspend fun postUnlike(pk: Int): Result<BaseResponse<Unit>>
}
