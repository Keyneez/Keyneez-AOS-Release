package com.release.keyneez.data.source

import com.release.keyneez.data.entity.response.ResponseGetSearchResultDto
import com.release.keyneez.data.entity.response.wrapper.BaseResponse
import com.release.keyneez.data.service.ContentService
import javax.inject.Inject

class ContentDataSource @Inject constructor(
    private val contentService: ContentService
) {
    suspend fun getSearch(keyword: String): BaseResponse<List<ResponseGetSearchResultDto>> =
        contentService.getSearch(keyword)
}