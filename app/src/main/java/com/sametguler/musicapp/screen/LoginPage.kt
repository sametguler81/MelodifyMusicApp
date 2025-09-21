package com.sametguler.musicapp.screen

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.sametguler.musicapp.Playwrite
import com.sametguler.musicapp.R
import com.sametguler.musicapp.viewmodel.MusicViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun LoginPage(navController: NavController, viewModel: MusicViewModel) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    var visible = remember { mutableStateOf(false) }
    var visible2 = remember { mutableStateOf(false) }
    var visible3 = remember { mutableStateOf(false) }
    var visible4 = remember { mutableStateOf(false) }
    var tfEmail = remember { mutableStateOf("") }
    var tfPassword = remember { mutableStateOf("") }


    LaunchedEffect(Unit) {
        delay(300)
        visible.value = true
        delay(300)
        visible2.value = true
        delay(300)
        visible3.value = true
        delay(400)
        visible4.value = true
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
                    ),
                ),
            ),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxSize()
        ) {

            AnimatedVisibility(
                visible = visible.value,
                enter = slideInVertically(
                    initialOffsetY = { -300 },
                    animationSpec = tween(1000),
                ) + fadeIn(animationSpec = tween(1000))
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Image(
                        painter = painterResource(R.drawable.logo),
                        contentDescription = "logo",
                        modifier = Modifier
                            .size(400.dp)
                            .align(alignment = Alignment.Center),
                    )
                }
            }

            AnimatedVisibility(
                visible = visible.value,
                enter = slideInHorizontally(
                    initialOffsetX = { it },
                    animationSpec = tween(1000),
                ) + fadeIn(animationSpec = tween(1000))
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1F)
                        .background(
                            color = Color.DarkGray.copy(alpha = 0.7F),
                            shape = RoundedCornerShape(topStart = 56.dp, topEnd = 56.dp),
                        )
                        .border(
                            width = 1.dp,
                            color = Color.LightGray,
                            shape = RoundedCornerShape(topStart = 56.dp, topEnd = 56.dp),
                        ),
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(25.dp)
                    ) {
                        AnimatedVisibility(
                            visible = visible.value,
                            enter = slideInHorizontally(
                                initialOffsetX = { it },
                                animationSpec = tween(1000),
                            ) + fadeIn(animationSpec = tween(1000))
                        ) {
                            Text(
                                modifier = Modifier.padding(bottom = 20.dp),
                                text = "Melodify' a Hoş geldin!",
                                fontFamily = Playwrite,
                                color = Color.White,
                                fontSize = 26.sp
                            )
                        }

                        AnimatedVisibility(
                            visible = visible2.value,
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
                                placeholder = { Text("Email giriniz", color = Color.LightGray) },
                                value = tfEmail.value,
                                onValueChange = {
                                    tfEmail.value = it
                                },
                                shape = RoundedCornerShape(32.dp)
                            )
                        }
                        AnimatedVisibility(
                            visible = visible3.value,
                            enter = slideInHorizontally(
                                initialOffsetX = { it },
                                animationSpec = tween(1000),
                            ) + fadeIn(animationSpec = tween(1000))
                        ) {
                            OutlinedTextField(
                                visualTransformation = PasswordVisualTransformation(),
                                modifier = Modifier
                                    .height(70.dp)
                                    .padding(bottom = 14.dp),
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
                                placeholder = {
                                    Text(
                                        "Şifrenizi giriniz",
                                        color = Color.LightGray
                                    )
                                },
                                value = tfPassword.value,
                                onValueChange = {
                                    tfPassword.value = it
                                },
                                shape = RoundedCornerShape(32.dp)
                            )
                        }
                        AnimatedVisibility(
                            visible = visible4.value,
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
                                            viewModel.login(
                                                email = tfEmail.value,
                                                password = tfPassword.value
                                            ) { success, message ->
                                                if (success) {
                                                    Toast.makeText(
                                                        context,
                                                        message,
                                                        Toast.LENGTH_SHORT
                                                    ).show()
                                                    navController.navigate("home") {
                                                        popUpTo(0) {
                                                            inclusive = true
                                                        }
                                                        launchSingleTop = true
                                                    }
                                                } else {
                                                    Toast.makeText(
                                                        context,
                                                        message,
                                                        Toast.LENGTH_SHORT
                                                    ).show()
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
                                        "Giriş Yap", modifier = Modifier.padding(
                                            vertical = 7.dp, horizontal = 10.dp
                                        )
                                    )
                                }
                                Text(
                                    "Hesabım yok",
                                    color = Color.White,
                                    fontSize = 16.sp,
                                    modifier = Modifier.clickable {
                                        navController.navigate("register") {
                                            popUpTo(route = "login") {
                                                inclusive = true
                                            }
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
}