package com.release.keyneez.data.service

import com.release.keyneez.data.entity.response.ResponseGetSearchResultDto
import com.release.keyneez.data.entity.response.wrapper.BaseResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ContentService {
    @GET("content/search")
    suspend fun getSearch(
        @Query("keyword") keyword: String
    ): BaseResponse<List<ResponseGetSearchResultDto>>
}