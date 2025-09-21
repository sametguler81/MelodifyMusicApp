package com.sametguler.musicapp.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.sametguler.musicapp.R
import com.sametguler.musicapp.model.NavItem
import com.sametguler.musicapp.viewmodel.MusicViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage(viewModel: MusicViewModel, navController: NavController) {
    val navItemList = listOf(
        NavItem(label = "Ana Sayfa", icon = Icons.Default.Home),
        NavItem(label = "Favoriler", icon = Icons.Default.FavoriteBorder),
        NavItem("Hesabım", icon = Icons.Default.AccountCircle)
    )
    val selectedIndex = remember { mutableStateOf(0) }

    Scaffold(
        contentWindowInsets = WindowInsets(0),
        modifier = Modifier
            .fillMaxSize(),

        ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            colorResource(R.color.bg_1),
                            colorResource(R.color.bg_2),
                            colorResource(R.color.bg_3)
                        ),
                    ),
                )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()

            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    when (selectedIndex.value) {
                        0 -> HomeScreen(viewModel, navController = navController)
                        1 -> FavoritesScreen()
                        2 -> AccountScreen(viewModel)
                    }
                    CustomedBottomBar(
                        navItemList = navItemList,
                        selectedIndex = selectedIndex,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(110.dp)
                            .padding(start = 25.dp, top = 10.dp, end = 25.dp, bottom = 30.dp)
                            .align(alignment = Alignment.BottomCenter)
                            .border(
                                width = 1.dp,
                                color = Color.White,
                                shape = RoundedCornerShape(50.dp)
                            )
                            .background(
                                brush = Brush.horizontalGradient(
                                    colors = listOf(
                                        colorResource(R.color.bg_1),
                                        colorResource(R.color.bg_2),
                                        colorResource(R.color.bg_3)
                                    )
                                ),
                                shape = RoundedCornerShape(50.dp)
                            )
                    )
                }
            }
        }
    }
}
