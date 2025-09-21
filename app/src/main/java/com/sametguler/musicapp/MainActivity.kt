package com.sametguler.musicapp

import android.os.Bundle
import android.util.Log
import android.view.RoundedCorner
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.toLong
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.LinearGradient
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import coil.compose.rememberAsyncImagePainter
import com.google.gson.Gson
import com.sametguler.musicapp.model.NavItem
import com.sametguler.musicapp.screen.ArtistDetail
import com.sametguler.musicapp.screen.HomePage
import com.sametguler.musicapp.screen.LoginPage
import com.sametguler.musicapp.screen.MusicPlayer
import com.sametguler.musicapp.screen.RegisterPage
import com.sametguler.musicapp.ui.theme.MusicAppTheme
import com.sametguler.musicapp.viewmodel.MusicViewModel
import kotlinx.coroutines.delay
import java.net.URLDecoder
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        enableEdgeToEdge()
        setContent {
            MainPageChanges()
        }
    }
}

@Composable
fun MainPageChanges() {
    val mainNavController = rememberNavController()
    val viewModel: MusicViewModel = viewModel()
    NavHost(navController = mainNavController, startDestination = "login") {
        composable(route = "login") {
            LoginPage(navController = mainNavController, viewModel = viewModel)
        }
        composable(route = "register") {
            RegisterPage(navController = mainNavController, viewModel = viewModel)
        }
        composable(route = "home") {
            HomePage(viewModel, navController = mainNavController)
        }
        composable(
            route = "artist_detail/{id}", arguments = listOf(
                navArgument("id") {
                    NavType.StringType
                }
            )) {
            val artistId = it.arguments?.getString("id")?.toLongOrNull() ?: 0
            if (artistId != null) {
                ArtistDetail(
                    id = artistId,
                    navController = mainNavController,
                    viewModel = viewModel
                )
            } else {
                Text("Sanatçı bulunamadı")
            }
        }
        composable(
            route = "music_player/{url}/{id}", arguments = listOf(
                navArgument("url") {
                    NavType.StringType
                },
                navArgument(name = "id") {
                    NavType.StringType
                }
            )) {
            var id = it.arguments?.getString("id")?.toLongOrNull() ?: 0
            val url = it.arguments?.getString("url")?.let { encoded ->
                URLDecoder.decode(encoded, StandardCharsets.UTF_8.toString())
            }
            if (id != null && url != null) {
                MusicPlayer(url = url, id = id, navController = mainNavController, viewModel)
            } else {
                Text("Bulunamadı")
            }
        }
    }
}


