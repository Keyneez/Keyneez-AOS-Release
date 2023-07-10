package com.release.keyneez.data.source

import com.release.keyneez.data.entity.response.ResponseGetContentDto
import com.release.keyneez.data.entity.response.ResponseGetLikeDto
import com.release.keyneez.data.entity.response.ResponseGetSearchResultDto
import com.release.keyneez.data.entity.response.ResponsePostLikeDto
import com.release.keyneez.data.entity.response.wrapper.BaseResponse
import com.release.keyneez.data.service.ContentService
import javax.inject.Inject

class ContentDataSource @Inject constructor(
    private val contentService: ContentService
) {
    suspend fun getContent(filter: String): BaseResponse<List<ResponseGetContentDto>> =
        contentService.getContent(filter)

    suspend fun getLike(filter: String): BaseResponse<List<ResponseGetLikeDto>> =
        contentService.getLike(filter)

    suspend fun getSearch(keyword: String): BaseResponse<List<ResponseGetSearchResultDto>> =
        contentService.getSearch(keyword)

    suspend fun postLike(pk: Int): BaseResponse<ResponsePostLikeDto> =
        contentService.postLike(pk)
    suspend fun postUnlike(pk: Int): BaseResponse<Unit> =
        contentService.postUnlike(pk)
}
