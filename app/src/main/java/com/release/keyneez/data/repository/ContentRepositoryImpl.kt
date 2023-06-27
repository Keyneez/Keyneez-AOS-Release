package com.release.keyneez.data.repository

import com.release.keyneez.data.entity.response.ResponseGetSearchResultDto
import com.release.keyneez.data.entity.response.wrapper.BaseResponse
import com.release.keyneez.data.source.ContentDataSource
import javax.inject.Inject

class ContentRepositoryImpl @Inject constructor(
    private val contentDataSource: ContentDataSource
) : ContentRepository {
    override suspend fun getSearch(keyword: String): Result<BaseResponse<List<ResponseGetSearchResultDto>>> =
        kotlin.runCatching { contentDataSource.getSearch(keyword) }
}
