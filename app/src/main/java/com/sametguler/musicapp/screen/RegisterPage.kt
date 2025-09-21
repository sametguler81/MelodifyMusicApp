package com.sametguler.musicapp.screen

import android.os.Looper
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.sametguler.musicapp.Playwrite
import com.sametguler.musicapp.R
import com.sametguler.musicapp.model.User
import com.sametguler.musicapp.viewmodel.MusicViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.logging.Handler

@Composable
fun RegisterPage(navController: NavController, viewModel: MusicViewModel) {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    var visible = remember { mutableStateOf(false) }
    var visible2 = remember { mutableStateOf(false) }
    var visible3 = remember { mutableStateOf(false) }
    var visible4 = remember { mutableStateOf(false) }
    var visible5 = remember { mutableStateOf(false) }
    val tfUserName = remember { mutableStateOf("") }
    val tfEmail = remember { mutableStateOf("") }
    val tfPassword = remember { mutableStateOf("") }


    LaunchedEffect(Unit) {
        delay(300)
        visible.value = true
        delay(300)
        visible2.value = true
        delay(300)
        visible3.value = true
        delay(300)
        visible4.value = true
        delay(300)
        visible5.value = true
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        colorResource(R.color.bg_1),
                        colorResource(R.color.bg_2),
                        colorResource(R.color.bg_3),
                    )
                )
            ),
    ) {
        AnimatedVisibility(
            modifier = Modifier.align(alignment = Alignment.BottomCenter),
            visible = visible.value,
            enter = slideInHorizontally(
                initialOffsetX = { it },
                animationSpec = tween(1000),
            ) + fadeIn(animationSpec = tween(1000))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(650.dp)
                    .border(
                        width = 1.dp,
                        color = Color.White,
                        shape = RoundedCornerShape(32.dp)
                    )
                    .clip(shape = RoundedCornerShape(32.dp))
                    .background(
                        color = Color.DarkGray.copy(0.7F),
                        shape = RoundedCornerShape(32.dp)
                    )
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    AnimatedVisibility(
                        modifier = Modifier.padding(top = 10.dp),
                        visible = visible2.value,
                        enter = slideInHorizontally(
                            initialOffsetX = { it },
                            animationSpec = tween(1000)
                        ) + fadeIn(animationSpec = tween(1000)),
                    ) {
                        Text(
                            "Melodify' a kayıt ol!",
                            color = Color.White,
                            fontSize = 24.sp,
                            fontFamily = Playwrite
                        )
                    }
                    AnimatedVisibility(
                        modifier = Modifier.padding(top = 20.dp),
                        visible = visible3.value,
                        enter = slideInHorizontally(
                            initialOffsetX = { it },
                            animationSpec = tween(1000),
                        ) + fadeIn(animationSpec = tween(1000))
                    ) {
                        OutlinedTextField(
                            modifier = Modifier
                                .height(70.dp)
                                .padding(bottom = 10.dp),
                            singleLine = true,
                            colors = TextFieldDefaults.colors(
                                errorTextColor = Color.White,
                                disabledTextColor = Color.White,
                                focusedTextColor = Color.White,
                                unfocusedTextColor = Color.White,
                                focusedIndicatorColor = Color.White,
                                unfocusedIndicatorColor = Color.White,
                                errorIndicatorColor = Color.White,
                                disabledIndicatorColor = Color.White,
                                errorContainerColor = Color.Red,
                                disabledContainerColor = Color.Transparent,
                                focusedContainerColor = Color.Transparent,
                                unfocusedContainerColor = Color.Transparent
                            ),
                            label = { Text("Kullanıcı Adı", color = Color.White) },
                            placeholder = {
                                Text(
                                    "Kullanıcı adı giriniz",
                                    color = Color.LightGray
                                )
                            },
                            value = tfUserName.value,
                            onValueChange = {
                                tfUserName.value = it
                            },
                            shape = RoundedCornerShape(32.dp)
                        )
                    }
                    AnimatedVisibility(
                        visible = visible4.value,
                        enter = slideInHorizontally(
                            initialOffsetX = { it },
                            animationSpec = tween(1000),
                        ) + fadeIn(animationSpec = tween(1000))
                    ) {
                        OutlinedTextField(
                            modifier = Modifier
                                .height(70.dp)
                                .padding(bottom = 10.dp),
                            singleLine = true,
                            colors = TextFieldDefaults.colors(
                                errorTextColor = Color.White,
                                disabledTextColor = Color.White,
                                focusedTextColor = Color.White,
                                unfocusedTextColor = Color.White,
                                focusedIndicatorColor = Color.White,
                                unfocusedIndicatorColor = Color.White,
                                errorIndicatorColor = Color.White,
                                disabledIndicatorColor = Color.White,
                                errorContainerColor = Color.Red,
                                disabledContainerColor = Color.Transparent,
                                focusedContainerColor = Color.Transparent,
                                unfocusedContainerColor = Color.Transparent
                            ),
                            label = { Text("Email", color = Color.White) },
                            placeholder = {
                                Text(
                                    "Email giriniz",
                                    color = Color.LightGray
                                )
                            },
                            value = tfEmail.value,
                            onValueChange = {
                                tfEmail.value = it
                            },
                            shape = RoundedCornerShape(32.dp)
                        )
                    }
                    AnimatedVisibility(
                        modifier = Modifier.padding(bottom = 5.dp),
                        visible = visible5.value,
                        enter = slideInHorizontally(
                            initialOffsetX = { it },
                            animationSpec = tween(1000),
                        ) + fadeIn(animationSpec = tween(1000))
                    ) {
                        OutlinedTextField(
                            modifier = Modifier
                                .height(70.dp)
                                .padding(bottom = 10.dp),
                            singleLine = true,
                            colors = TextFieldDefaults.colors(
                                errorTextColor = Color.White,
                                disabledTextColor = Color.White,
                                focusedTextColor = Color.White,
                                unfocusedTextColor = Color.White,
                                focusedIndicatorColor = Color.White,
                                unfocusedIndicatorColor = Color.White,
                                errorIndicatorColor = Color.White,
                                disabledIndicatorColor = Color.White,
                                errorContainerColor = Color.Red,
                                disabledContainerColor = Color.Transparent,
                                focusedContainerColor = Color.Transparent,
                                unfocusedContainerColor = Color.Transparent
                            ),
                            label = { Text("Şifre", color = Color.White) },
                            placeholder = { Text("Şifrenizi giriniz", color = Color.LightGray) },
                            value = tfPassword.value,
                            onValueChange = {
                                tfPassword.value = it
                            },
                            shape = RoundedCornerShape(32.dp)
                        )
                    }
                    AnimatedVisibility(
                        visible = visible5.value,
                        enter = fadeIn(animationSpec = tween(durationMillis = 2500)),
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Button(
                                shape = RoundedCornerShape(32.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color.Transparent,
                                    contentColor = Color.White
                                ),
                                onClick = {
                                    scope.launch {
                                        viewModel.registerUser(
                                            name = tfUserName.value,
                                            email = tfEmail.value,
                                            password = tfPassword.value
                                        ) { success, message ->
                                            android.os.Handler(android.os.Looper.getMainLooper())
                                                .post {
                                                    if (success) {
                                                        Toast.makeText(
                                                            context,
                                                            "Kayıt başarılı, giriş sayfasına yönlendiriliyorsunuz!",
                                                            Toast.LENGTH_SHORT
                                                        ).show()
                                                        navController.navigate("login") {
                                                            popUpTo("register") { inclusive = true }
                                                        }
                                                    } else {
                                                        Toast.makeText(
                                                            context,
                                                            message ?: "Bir hata oluştu",
                                                            Toast.LENGTH_SHORT
                                                        ).show()
                                                    }
                                                }
                                        }
                                    }
                                },
                                modifier = Modifier
                                    .padding(bottom = 15.dp)
                                    .border(
                                        width = 1.dp,
                                        color = Color.White,
                                        shape = RoundedCornerShape(32.dp)
                                    )
                            ) {
                                Text(
                                    "Kayıt ol",
                                    modifier = Modifier.padding(vertical = 7.dp, horizontal = 10.dp)
                                )
                            }

                            Text(
                                "Zaten hesabım var", color = Color.White, fontSize = 16.sp,
                                modifier = Modifier.clickable {
                                    navController.navigate("login") {
                                        popUpTo(0) { inclusive = true } // stack'i tamamen temizle
                                        launchSingleTop = true
                                    }
                                },
                            )
                        }

                    }
                }
            }
        }
    }
}