package com.sametguler.musicapp.viewmodel

import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.api.Context
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.sametguler.musicapp.model.User
import com.sametguler.musicapp.repository.MusicRepository
import kotlinx.coroutines.launch

class MusicViewModel : ViewModel() {
    val repo = MusicRepository()
    val trends = repo.trends
    val newTrends = repo.newTrends
    val trendsArtist = repo.trendsArtist
    val search = repo.search
    val artistDetail = repo.artistDetail
    val artistAlbum = repo.artistAlbum

    val playerAlbum = repo.playerAlbum

    fun getUsername(uid: String, onResult: (User?) -> Unit) {
        val db = FirebaseFirestore.getInstance()

        db.collection("users")
            .document(uid)
            .get()
            .addOnSuccessListener { documentSnapshot ->
                if (documentSnapshot.exists()) {
                    val user = documentSnapshot.toObject(User::class.java)
                    onResult(user)
                } else {
                    onResult(null)
                }
            }.addOnFailureListener {
                onResult(null)
            }

    }


    fun login(email: String, password: String, onResult: (Boolean, String) -> Unit) {
        if (email.isBlank() || password.isBlank()) {
            onResult(false, "Lütfen tüm alanları doldurun!")
            return
        }

        val auth = FirebaseAuth.getInstance()

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val uid = auth.currentUser?.uid ?: ""
                    onResult(true, "Başarılıyla giriş yapıldı!")
                } else {
                    val exception = task.exception
                    val errorMessage = when (exception) {
                        is com.google.firebase.auth.FirebaseAuthInvalidUserException -> "Kullanıcı bulunamadı"
                        is com.google.firebase.auth.FirebaseAuthInvalidCredentialsException -> "E-posta formatı geçersiz"
                        else -> "Bir hata oluştu"
                    }
                    onResult(false, errorMessage)
                }
            }.addOnFailureListener { exception ->
                onResult(false, exception.message.toString())
            }

    }

    fun registerUser(
        name: String,
        email: String,
        password: String,
        onResult: (Boolean, String?) -> Unit
    ) {
        if (name.isBlank() || email.isBlank() || password.isBlank()) {
            onResult(false, "Lütfen tüm alanları doldurun!")
            return
        }

        val auth = FirebaseAuth.getInstance()
        val db = FirebaseFirestore.getInstance()

        auth.createUserWithEmailAndPassword(email.trim(), password.trim())
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val uid = auth.currentUser?.uid ?: ""
                    val user = User(uid, name.trim(), email.trim(), password.trim())


                    db.collection("users").document(uid)
                        .set(user)
                        .addOnSuccessListener {
                            onResult(true, null)
                        }
                        .addOnFailureListener { e ->
                            onResult(false, "Kullanıcı bilgileri kaydedilemedi")
                        }
                } else {
                    val exception = task.exception
                    val errorMessage = when (exception) {
                        is com.google.firebase.auth.FirebaseAuthUserCollisionException -> "Bu e-posta zaten kullanılıyor"
                        is com.google.firebase.auth.FirebaseAuthInvalidCredentialsException -> "E-posta formatı geçersiz"
                        is com.google.firebase.auth.FirebaseAuthWeakPasswordException -> "Şifre çok zayıf, en az 6 karakter olmalı"
                        else -> "Bir hata oluştu"
                    }
                    onResult(false, errorMessage)
                }
            }

    }


    fun getPlayerAlbum(id: Long) {
        viewModelScope.launch {
            repo.getPlayerAlbum(id = id)
        }
    }

    fun getArtistAlbum(id: Long) {
        viewModelScope.launch {
            repo.getArtistAlbum(id = id)
        }
    }

    fun getArtistDetail(id: Long) {
        viewModelScope.launch {
            repo.getArtistDetail(id = id)
        }
    }

    fun getSearch(q: String) {
        viewModelScope.launch {
            repo.getSearch(q)
        }
    }

    fun getTrendsArtist() {
        viewModelScope.launch {
            repo.getArtist(10)
        }
    }

    fun getTrends() {
        viewModelScope.launch {
            repo.getTrends(10)
        }
    }

    fun getNewTrends() {
        viewModelScope.launch {
            repo.getNewTrends(20)
        }
    }


}