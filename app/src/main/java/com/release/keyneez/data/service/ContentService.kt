package com.release.keyneez.data.service

import com.release.keyneez.data.entity.response.ResponseGetLikeDto
import com.release.keyneez.data.entity.response.ResponseGetPopularDto
import com.release.keyneez.data.entity.response.ResponseGetRecentDto
import com.release.keyneez.data.entity.response.ResponseGetSearchResultDto
import com.release.keyneez.data.entity.response.ResponsePostLikeDto
import com.release.keyneez.data.entity.response.wrapper.BaseResponse
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ContentService {
    @GET("api/contents/liked")
    suspend fun getLike(@Query("filter") filter: String): BaseResponse<List<ResponseGetLikeDto>>

    @GET("api/contents/")
    suspend fun getRecent(@Query("filter") filter: String): BaseResponse<List<ResponseGetRecentDto>>

    @GET("api/contents/popularity")
    suspend fun getPopular(@Query("filter") filter: String): BaseResponse<List<ResponseGetPopularDto>>

    @GET("api/contents/search")
    suspend fun getSearch(
        @Query("keyword") keyword: String
    ): BaseResponse<List<ResponseGetSearchResultDto>>

    @POST("api/contents/{pk}/like")
    suspend fun postLike(@Path("pk") pk: Int): BaseResponse<ResponsePostLikeDto>

    @POST("api/contents/{pk}/unlike")
    suspend fun postUnlike(@Path("pk") pk: Int): BaseResponse<Unit>
}
