package com.kyonggi.retrofitdemo

import retrofit2.Response
import retrofit2.http.*

interface AlbumsService {

    @GET("/albums")
    suspend fun getAlbums(): Response<Albums>

    @GET("/albums")
    suspend fun getSortedAlbums(@Query("userId") userId: Int) : Response<Albums>

    @GET("/albums/{id}")
    suspend fun getAlbum(@Path(value = "id") albumId: Int) : Response<AlbumItem>

    @POST("/albums")
    suspend fun uploadAlbum(@Body album: AlbumItem) : Response<AlbumItem>
}