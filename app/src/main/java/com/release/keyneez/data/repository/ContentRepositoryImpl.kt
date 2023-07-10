package com.release.keyneez.data.repository

import com.release.keyneez.data.entity.response.ResponseGetContentDto
import com.release.keyneez.data.entity.response.ResponseGetLikeDto
import com.release.keyneez.data.entity.response.ResponseGetSearchResultDto
import com.release.keyneez.data.entity.response.ResponsePostLikeDto
import com.release.keyneez.data.entity.response.wrapper.BaseResponse
import com.release.keyneez.data.source.ContentDataSource
import javax.inject.Inject

class ContentRepositoryImpl @Inject constructor(
    private val contentDataSource: ContentDataSource
) : ContentRepository {

    override suspend fun getContent(filter: String): Result<BaseResponse<List<ResponseGetContentDto>>> =
        kotlin.runCatching { contentDataSource.getContent(filter) }

    override suspend fun getLike(filter: String): Result<BaseResponse<List<ResponseGetLikeDto>>> =
        kotlin.runCatching { contentDataSource.getLike(filter) }

    override suspend fun getSearch(keyword: String): Result<BaseResponse<List<ResponseGetSearchResultDto>>> =
        kotlin.runCatching { contentDataSource.getSearch(keyword) }

    override suspend fun postLike(pk: Int): Result<BaseResponse<ResponsePostLikeDto>> =
        kotlin.runCatching { contentDataSource.postLike(pk) }

    override suspend fun postUnlike(pk: Int): Result<BaseResponse<Unit>> =
        kotlin.runCatching { contentDataSource.postUnlike(pk) }
}
