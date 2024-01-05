package com.asemlab.samples.ktor.remote

import com.asemlab.samples.ktor.models.PostResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody

class PostService(private val client: HttpClient) {

    suspend fun createPost(body: PostResponse): PostResponse {
        // TODO Make request
        return client.post(HttpRoutes.CREATE_POST) {
            setBody(body)
        }.body() // TODO Deserialize response body
    }

}