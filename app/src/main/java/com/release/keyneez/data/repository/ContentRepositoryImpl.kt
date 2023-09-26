package com.release.keyneez.data.repository

import com.release.keyneez.data.entity.response.ResponseGetLikeDto
import com.release.keyneez.data.entity.response.ResponseGetPopularDto
import com.release.keyneez.data.entity.response.ResponseGetRecentDto
import com.release.keyneez.data.entity.response.ResponseGetSearchResultDto
import com.release.keyneez.data.entity.response.ResponsePostLikeDto
import com.release.keyneez.data.entity.response.wrapper.BaseResponse
import com.release.keyneez.data.source.ContentDataSource
import javax.inject.Inject

class ContentRepositoryImpl @Inject constructor(
    private val contentDataSource: ContentDataSource
) : ContentRepository {

    override suspend fun getRecent(filter: String): Result<BaseResponse<List<ResponseGetRecentDto>>> =
        kotlin.runCatching { contentDataSource.getRecent(filter) }

    override suspend fun getAllRecent(): Result<BaseResponse<List<ResponseGetRecentDto>>> =
        kotlin.runCatching { contentDataSource.getAllRecent() }

    override suspend fun getPopular(filter: String): Result<BaseResponse<List<ResponseGetPopularDto>>> =
        kotlin.runCatching { contentDataSource.getPopular(filter) }

    override suspend fun getAllPopular(): Result<BaseResponse<List<ResponseGetPopularDto>>> =
        kotlin.runCatching { contentDataSource.getAllPopular() }

    override suspend fun getLike(filter: String): Result<BaseResponse<List<ResponseGetLikeDto>>> =
        kotlin.runCatching { contentDataSource.getLike(filter) }

    override suspend fun getAllLike(): Result<BaseResponse<List<ResponseGetLikeDto>>> =
        kotlin.runCatching { contentDataSource.getAllLike() }

    override suspend fun getSearch(keyword: String): Result<BaseResponse<List<ResponseGetSearchResultDto>>> =
        kotlin.runCatching { contentDataSource.getSearch(keyword) }

    override suspend fun postLike(pk: Int): Result<BaseResponse<ResponsePostLikeDto>> =
        kotlin.runCatching { contentDataSource.postLike(pk) }

    override suspend fun postUnlike(pk: List<Int>): Result<BaseResponse<Unit>> =
        kotlin.runCatching { contentDataSource.postUnlike(pk) }
}
