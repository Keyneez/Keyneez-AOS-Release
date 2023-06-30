package com.release.keyneez.data.service

import com.release.keyneez.data.entity.response.ResponseGetContentDto
import com.release.keyneez.data.entity.response.ResponseGetLikeDto
import com.release.keyneez.data.entity.response.ResponseGetSearchResultDto
import com.release.keyneez.data.entity.response.wrapper.BaseResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ContentService {
    @GET("contents/liked")
    suspend fun getLike(): BaseResponse<List<ResponseGetLikeDto>>

    @GET("contents/")
    suspend fun getContent(@Query("filter") filter: String): BaseResponse<List<ResponseGetContentDto>>

    @GET("contents/search")
    suspend fun getSearch(
        @Query("keyword") keyword: String
    ): BaseResponse<List<ResponseGetSearchResultDto>>
}
