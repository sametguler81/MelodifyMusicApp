package com.sametguler.musicapp.model

import androidx.compose.ui.graphics.vector.ImageVector
import com.google.gson.annotations.SerializedName

data class NavItem(
    var label: String,
    var icon: ImageVector
)

data class ArtistDetailResponse(
    val id: Long,
    val name: String,
    val picture_big: String
)

data class PlaylistResponse(
    val id: Long,
    val title: String,
    val tracks: TracksWrapper
)

data class AlbumResponse(
    val data: List<ArtistAlbum>
)

data class Contributor(
    val id: Long,
    val name: String,
)

data class AlbumsForDetail(
    val id: Long,
    val title: String,
    val cover_medium: String,
    val tracklist: String
)

data class ArtistAlbum(
    val id: Long,
    val title: String,
    val link: String,
    val duration: Int,
    val contributors: List<Contributor>,
    val album: AlbumsForDetail
)

data class TracksWrapper(
    val data: List<TrackForTrends>
)

data class TrackForTrends(
    var id: Long,
    var title: String,
    var preview: String,
    var artist: Artist,
    var album: Album,
)

data class Artist(
    val id: Long,
    val name: String,
)

data class TopArtist(
    @SerializedName("id")
    val id: Long,
    val name: String,
    val picture_medium: String,
    val picture_big: String
)

data class ArtistResponse(
    val data: List<TopArtist>
)

data class Album(
    val id: Long,
    val cover_big: String,
    val cover_medium: String
)