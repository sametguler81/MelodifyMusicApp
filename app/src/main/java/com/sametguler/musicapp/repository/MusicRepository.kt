package com.sametguler.musicapp.repository

import androidx.lifecycle.MutableLiveData
import com.sametguler.musicapp.model.AlbumResponse
import com.sametguler.musicapp.model.ArtistAlbum
import com.sametguler.musicapp.model.ArtistDetailResponse
import com.sametguler.musicapp.model.TopArtist
import com.sametguler.musicapp.model.TrackForTrends
import com.sametguler.musicapp.service.ApiUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MusicRepository {
    private val dao = ApiUtils.getMusicAppDaoInterface()
    val trends = MutableLiveData<List<TrackForTrends>>()

    val playerAlbum = MutableLiveData<ArtistAlbum>()
    val newTrends = MutableLiveData<List<TrackForTrends>>()
    val trendsArtist = MutableLiveData<List<TopArtist>>()
    val search = MutableLiveData<List<TrackForTrends>>()
    val artistDetail = MutableLiveData<ArtistDetailResponse>()
    val artistAlbum = MutableLiveData<AlbumResponse>()


    suspend fun getPlayerAlbum(id: Long) {
        return try {
            val data = dao.getPlayerAlbum(id = id)
            playerAlbum.value = data
        } catch (e: Exception) {
            e.printStackTrace()
            println(e.message)
        }
    }
    suspend fun getArtistAlbum(id: Long) {
        return try {
            val data = dao.getAlbum(id = id)
            artistAlbum.value = data
        } catch (e: Exception) {
            e.printStackTrace()
            println(e.message)
        }
    }

    suspend fun getArtistDetail(id: Long) {
        return try {
            val data = dao.getArtistDetail(id = id)
            artistDetail.value = data
        } catch (e: Exception) {
            e.printStackTrace()
            println(e.message)
        }
    }

    suspend fun getSearch(q: String) {
        return try {
            val data = dao.getSearch(q = q)
            search.value = data.data
        } catch (e: Exception) {
            e.printStackTrace()
            println(e.message)
        }
    }

    suspend fun getArtist(limit: Int) {
        return try {
            val data = dao.getTrendArtist(limit)
            trendsArtist.value = data.data
        } catch (e: Exception) {
            e.printStackTrace()
            println(e.message)
        }
    }

    suspend fun getNewTrends(limit: Int) {
        return try {
            val playlist = dao.getNewTrends(limit)
            newTrends.value = playlist.tracks.data
        } catch (e: Exception) {
            e.printStackTrace()
            println(e.message)
        }
    }

    suspend fun getTrends(limit: Int) {
        return try {
            val playlist = dao.getTrends(limit)
            trends.value = playlist.tracks.data
        } catch (e: Exception) {
            e.printStackTrace()
            println(e.message)
        }
    }

}