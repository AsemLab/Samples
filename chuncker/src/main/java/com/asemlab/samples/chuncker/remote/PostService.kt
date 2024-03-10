package com.asemlab.samples.chuncker.remote

import com.asemlab.samples.chuncker.models.PostResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface PostService {

    @POST(HttpRoutes.CREATE_POST)
    suspend fun createPost(@Body body: PostResponse): PostResponse

}