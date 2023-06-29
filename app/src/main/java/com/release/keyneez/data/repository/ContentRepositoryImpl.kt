package com.release.keyneez.data.repository

import com.release.keyneez.data.entity.response.ResponseGetContentDto
import com.release.keyneez.data.entity.response.ResponseGetLikeDto
import com.release.keyneez.data.entity.response.ResponseGetSearchResultDto
import com.release.keyneez.data.entity.response.wrapper.BaseResponse
import com.release.keyneez.data.source.ContentDataSource
import javax.inject.Inject

class ContentRepositoryImpl @Inject constructor(
    private val contentDataSource: ContentDataSource
) : ContentRepository {

    override suspend fun getContent(): Result<BaseResponse<List<ResponseGetContentDto>>> =
        kotlin.runCatching { contentDataSource.getContent() }

    override suspend fun getLike(): Result<BaseResponse<List<ResponseGetLikeDto>>> =
        kotlin.runCatching { contentDataSource.getLike() }

    override suspend fun getSearch(keyword: String): Result<BaseResponse<List<ResponseGetSearchResultDto>>> =
        kotlin.runCatching { contentDataSource.getSearch(keyword) }
}
