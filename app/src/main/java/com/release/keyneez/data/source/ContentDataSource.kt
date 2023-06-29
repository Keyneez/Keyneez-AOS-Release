package com.release.keyneez.data.source

import com.release.keyneez.data.entity.response.ResponseGetContentDto
import com.release.keyneez.data.entity.response.ResponseGetLikeDto
import com.release.keyneez.data.entity.response.ResponseGetSearchResultDto
import com.release.keyneez.data.entity.response.wrapper.BaseResponse
import com.release.keyneez.data.service.ContentService
import javax.inject.Inject

class ContentDataSource @Inject constructor(
    private val contentService: ContentService
) {
    suspend fun getContent(): BaseResponse<List<ResponseGetContentDto>> =
        contentService.getContent()

    suspend fun getLike(): BaseResponse<List<ResponseGetLikeDto>> = contentService.getLike()
    suspend fun getSearch(keyword: String): BaseResponse<List<ResponseGetSearchResultDto>> =
        contentService.getSearch(keyword)
}
