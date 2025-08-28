package com.asemlab.samples.chucker.remote

import com.asemlab.samples.chucker.models.PostResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface PostService {

    @POST(HttpRoutes.CREATE_POST)
    suspend fun createPost(@Body body: PostResponse): PostResponse

}