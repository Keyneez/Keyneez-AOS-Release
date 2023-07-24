package com.release.keyneez.data.repository

import com.release.keyneez.data.entity.response.ResponseGetLikeDto
import com.release.keyneez.data.entity.response.ResponseGetPopularDto
import com.release.keyneez.data.entity.response.ResponseGetRecentDto
import com.release.keyneez.data.entity.response.ResponseGetSearchResultDto
import com.release.keyneez.data.entity.response.ResponsePostLikeDto
import com.release.keyneez.data.entity.response.wrapper.BaseResponse

interface ContentRepository {

    suspend fun getRecent(filter: String): Result<BaseResponse<List<ResponseGetRecentDto>>>
    suspend fun getPopular(filter: String): Result<BaseResponse<List<ResponseGetPopularDto>>>
    suspend fun getLike(filter: String): Result<BaseResponse<List<ResponseGetLikeDto>>>
    suspend fun getSearch(keyword: String): Result<BaseResponse<List<ResponseGetSearchResultDto>>>
    suspend fun postLike(pk: Int): Result<BaseResponse<ResponsePostLikeDto>>
    suspend fun postUnlike(pk: Int): Result<BaseResponse<Unit>>
}
