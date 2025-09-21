package com.sametguler.musicapp.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.sametguler.musicapp.R
import com.sametguler.musicapp.viewmodel.MusicViewModel

@Composable
fun AccountScreen(viewModel: MusicViewModel) {

    val auth = FirebaseAuth.getInstance()
    val uid = auth.currentUser?.uid
    val username = remember { mutableStateOf("") }
    val userEmail = remember { mutableStateOf("") }


    LaunchedEffect(auth.currentUser) {
        viewModel.getUsername(uid = uid.toString()) { user ->
            username.value = user!!.userName
            userEmail.value = user!!.userEmail
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    listOf(
                        colorResource(R.color.bg_1),
                        colorResource(R.color.bg_2),
                        colorResource(R.color.bg_3),
                    )
                )
            )
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painterResource(R.drawable.avatar),
                contentDescription = "Avatar",
                modifier = Modifier.size(200.dp)
            )
            Spacer(modifier = Modifier.size(30.dp))
            Text("Kullancı Adı =  ${username.value}", color = Color.White)
            Text("Email = ${userEmail.value}", color = Color.White)
        }
    }
}
