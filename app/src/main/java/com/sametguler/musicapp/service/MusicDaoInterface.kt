package com.sametguler.musicapp.service

import com.sametguler.musicapp.model.AlbumResponse
import com.sametguler.musicapp.model.ArtistAlbum
import com.sametguler.musicapp.model.ArtistDetailResponse
import com.sametguler.musicapp.model.ArtistResponse
import com.sametguler.musicapp.model.PlaylistResponse
import com.sametguler.musicapp.model.TopArtist
import com.sametguler.musicapp.model.TrackForTrends
import com.sametguler.musicapp.model.TracksWrapper
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MusicDaoInterface {

    @GET("track/{id}")
    suspend fun getPlayerAlbum(
        @Path("id") id: Long,
    ): ArtistAlbum

    @GET("/artist/{id}/top")
    suspend fun getAlbum(
        @Path("id") id: Long,
        @Query("limit") limit: Int = 10
    ): AlbumResponse

    @GET("artist/{id}")
    suspend fun getArtistDetail(@Path("id") id: Long): ArtistDetailResponse

    @GET("playlist/10418162482")
    suspend fun getTrends(@Query("limit") limit: Int): PlaylistResponse

    @GET("search")
    suspend fun getSearch(@Query("q") q: String): TracksWrapper

    @GET("playlist/7678032782")
    suspend fun getNewTrends(@Query("limit") limit: Int): PlaylistResponse

    @GET("chart/0/artists")
    suspend fun getTrendArtist(@Query("limit") limit: Int): ArtistResponse

}