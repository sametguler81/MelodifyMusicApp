package com.sametguler.musicapp.service

class ApiUtils {
    companion object {
        val BASE_URL = "https://api.deezer.com/"

        fun getMusicAppDaoInterface(): MusicDaoInterface {
            return ApiClient.getClient(BASE_URL)
                .create(MusicDaoInterface::class.java)
        }

    }
}