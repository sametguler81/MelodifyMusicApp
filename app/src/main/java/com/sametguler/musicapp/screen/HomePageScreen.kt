package com.sametguler.musicapp.screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.google.firebase.auth.FirebaseAuth
import com.sametguler.musicapp.R
import com.sametguler.musicapp.model.User
import com.sametguler.musicapp.viewmodel.MusicViewModel
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun HomeScreen(viewModel: MusicViewModel, navController: NavController) {
    val auth = FirebaseAuth.getInstance()
    val userName = remember { mutableStateOf("") }
    var trends = viewModel.trends.observeAsState().value
    var newTrends = viewModel.newTrends.observeAsState().value
    var trendsArtist = viewModel.trendsArtist.observeAsState().value
    var isSearching = remember { mutableStateOf(false) }
    val tfSearch = remember { mutableStateOf("") }
    val currentUser = FirebaseAuth.getInstance().currentUser

    LaunchedEffect(currentUser) {
        currentUser?.uid?.let { uid ->
            println(uid)
            viewModel.getUsername(uid) { user ->
                userName.value = user?.userName ?: "Yükleniyor"
            }
        }
    }


    LaunchedEffect(Unit) {
        viewModel.getTrends()
        viewModel.getNewTrends()
        viewModel.getTrendsArtist()
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 40.dp, end = 10.dp, bottom = 10.dp, start = 10.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 30.dp)
        ) {
            if (isSearching.value) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                    ) {
                        // Textfield + Cancel butonu aynı Row içinde
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            OutlinedTextField(
                                placeholder = { Text("Şarkı veya sanatçı ara...") },
                                modifier = Modifier
                                    .weight(1f) // Textfield alanı kaplasın
                                    .padding(10.dp)
                                    .background(
                                        color = Color.Transparent,
                                        shape = RoundedCornerShape(32.dp)
                                    )
                                    .clip(RoundedCornerShape(32.dp)),
                                colors = TextFieldDefaults.colors(
                                    focusedIndicatorColor = Color.Transparent,
                                    unfocusedIndicatorColor = Color.Transparent,
                                    focusedContainerColor = Color.White,
                                    unfocusedContainerColor = Color.White,
                                    focusedTextColor = Color.Black,
                                    unfocusedTextColor = Color.Black
                                ),
                                value = tfSearch.value,
                                onValueChange = {
                                    tfSearch.value = it
                                    if (tfSearch.value.isNotBlank()) {
                                        viewModel.getSearch(tfSearch.value)
                                    }
                                },
                            )

                            Image(
                                painter = painterResource(R.drawable.cancel),
                                contentDescription = "cancel",
                                modifier = Modifier
                                    .size(30.dp)
                                    .padding(end = 5.dp)
                                    .clickable {
                                        tfSearch.value = ""
                                        isSearching.value = false
                                    }
                            )
                        }

                        // Arama sonuçları textfield’in ALTINDA
                        if (tfSearch.value.isNotBlank()) {
                            val searchResult = viewModel.search.observeAsState().value
                            searchResult?.let {
                                LazyColumn(
                                    verticalArrangement = Arrangement.spacedBy(10.dp),
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(top = 8.dp, bottom = 30.dp)
                                ) {
                                    items(count = searchResult.size) { index ->
                                        val track = searchResult[index]
                                        Row(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .border(
                                                    width = 0.dp,
                                                    color = Color.Transparent,
                                                    shape = RoundedCornerShape(16.dp)
                                                )
                                                .background(
                                                    color = Color.DarkGray.copy(alpha = 0.7F),
                                                    shape = RoundedCornerShape(16.dp)
                                                )
                                                .clickable {
                                                    track?.let {
                                                        val encodedUrl = URLEncoder.encode(
                                                            track.preview,
                                                            StandardCharsets.UTF_8.toString()
                                                        )
                                                        navController.navigate("music_player/$encodedUrl/${track.id}")
                                                    }
                                                },
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Row(
                                                verticalAlignment = Alignment.CenterVertically,
                                                modifier = Modifier
                                                    .fillMaxSize()
                                                    .padding(10.dp)
                                            ) {
                                                Image(
                                                    painter = rememberAsyncImagePainter(track.album.cover_big),
                                                    contentDescription = track.preview,
                                                    modifier = Modifier
                                                        .size(70.dp)
                                                        .clip(shape = CircleShape)
                                                        .border(
                                                            width = 0.dp,
                                                            color = Color.Transparent,
                                                            shape = CircleShape
                                                        )
                                                )
                                                Column(
                                                    modifier = Modifier.padding(start = 15.dp),
                                                    horizontalAlignment = Alignment.Start,
                                                    verticalArrangement = Arrangement.Center
                                                ) {
                                                    Text(
                                                        track.title,
                                                        color = Color.White,
                                                    )
                                                    Text(
                                                        track.artist.name,
                                                        color = Color.White,
                                                    )
                                                }
                                            }

                                        }
                                    }
                                }
                            }
                        }
                    }

                }

            } else {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Image(
                        painterResource(R.drawable.avatar),
                        contentDescription = "avatar",
                        modifier = Modifier
                            .padding(horizontal = 7.dp)
                            .size(70.dp)

                    )
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text("Günaydın!", color = Color.White)
                        Text(
                            text = if (userName.value.isNotEmpty()) userName.value else "Yükleniyor",
                            color = Color.White
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier.background(
                        color = Color.DarkGray.copy(alpha = 0.5f),
                        shape = CircleShape
                    )
                ) {
                    Icon(
                        tint = Color.White,
                        painter = painterResource(R.drawable.search),
                        contentDescription = "Search",
                        modifier = Modifier
                            .size(50.dp)
                            .padding(10.dp)
                            .clickable {
                                isSearching.value = !isSearching.value
                            }
                    )
                }
                Spacer(modifier = Modifier.padding(horizontal = 5.dp))
                Box(
                    modifier = Modifier.background(
                        color = Color.DarkGray.copy(alpha = 0.5f),
                        shape = CircleShape
                    )
                ) {
                    Icon(
                        tint = Color.White,
                        painter = painterResource(R.drawable.bell),
                        contentDescription = "Bell",
                        modifier = Modifier
                            .size(50.dp)
                            .padding(10.dp)
                    )
                }
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Shorts'ta trend şarkılar", color = Color.White, fontSize = 20.sp)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    "Hepsini gör",
                    color = Color.LightGray,
                    fontSize = 16.sp,
                )
                Icon(
                    imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = "seeAll",
                    tint = Color.LightGray,
                    modifier = Modifier.size(25.dp)
                )
            }
        }
        LazyRow(
            modifier = Modifier
                .border(
                    width = 0.dp,
                    color = Color.Transparent,
                    shape = RoundedCornerShape(16.dp)
                )
                .clip(shape = RoundedCornerShape(16.dp))
        ) {
            trends?.let { trendList ->
                items(count = trendList.size) { index ->
                    val track = trendList[index]
                    val imageUrl = track.album.cover_big ?: ""
                    Column() {
                        Image(
                            painter = rememberAsyncImagePainter(imageUrl),
                            contentDescription = track.title,
                            modifier = Modifier
                                .size(160.dp)
                                .padding(8.dp)
                                .border(
                                    width = 0.dp,
                                    color = Color.Transparent,
                                    shape = RoundedCornerShape(16.dp)
                                )
                                .clip(shape = RoundedCornerShape(16.dp))
                                .clickable {
                                    track?.let {
                                        val encodedUrl = URLEncoder.encode(
                                            track.preview,
                                            StandardCharsets.UTF_8.toString()
                                        )
                                        navController.navigate("music_player/$encodedUrl/${track.id}")
                                    }
                                }
                        )
                        Column(modifier = Modifier.padding(start = 7.dp)) {
                            Text(track.title, color = Color.White)
                            Text(track.artist.name, color = Color.White)
                        }
                    }
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Trend şarkıcılar", color = Color.White, fontSize = 20.sp)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    "Hepsini gör",
                    color = Color.LightGray,
                    fontSize = 16.sp,
                )
                Icon(
                    imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = "seeAll",
                    tint = Color.LightGray,
                    modifier = Modifier.size(25.dp)
                )
            }
        }
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(15.dp),
            modifier = Modifier
                .padding(top = 5.dp)
                .border(
                    width = 0.dp,
                    color = Color.Transparent,
                    shape = RoundedCornerShape(16.dp)
                )
                .clip(shape = RoundedCornerShape(16.dp))
        ) {
            trendsArtist?.let { artistList ->
                items(count = artistList.size) { index ->
                    val track = artistList[index]
                    val imageUrl = track.picture_medium ?: ""
                    Box(
                        modifier = Modifier
                            .background(
                                color = Color.DarkGray.copy(alpha = 0.5F),
                                shape = RoundedCornerShape(32.dp)
                            )
                            .clickable {
                                track.id?.let { artistId ->
                                    Log.d("NAV_TEST", "Artist ID: ${track.id}")
                                    println(track.id)
                                    track.id?.let { artistId ->
                                        navController.navigate("artist_detail/$artistId")
                                    }
                                }
                            }
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(horizontal = 5.dp, vertical = 5.dp)
                        ) {
                            Image(
                                modifier = Modifier
                                    .size(60.dp)
                                    .clip(shape = CircleShape)
                                    .border(2.dp, Color.Transparent, CircleShape)
                                    .padding(1.dp),
                                painter = rememberAsyncImagePainter(imageUrl),
                                contentDescription = track.name,
                                contentScale = ContentScale.Fit
                            )
                            Column(
                                horizontalAlignment = Alignment.Start,
                                modifier = Modifier.padding(horizontal = 15.dp)
                            ) {
                                Text(
                                    track.name,
                                    color = Color.White,
                                )
                                Text("Artist", color = Color.White)
                            }

                        }

                    }
                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Bu şarkıları dinle", color = Color.White, fontSize = 20.sp)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    "Hepsini gör",
                    color = Color.LightGray,
                    fontSize = 16.sp,
                )
                Icon(
                    imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = "seeAll",
                    tint = Color.LightGray,
                    modifier = Modifier.size(25.dp)
                )
            }
        }
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(15.dp),
            modifier = Modifier
                .padding(top = 5.dp)
                .border(
                    width = 0.dp,
                    color = Color.Transparent,
                    shape = RoundedCornerShape(16.dp)
                )
                .clip(shape = RoundedCornerShape(16.dp))
        ) {
            newTrends?.let { list ->
                items(count = list.size) { index ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(color = Color.DarkGray.copy(alpha = 0.5F))
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(10.dp)
                        ) {
                            Image(
                                modifier = Modifier.size(120.dp),
                                painter = rememberAsyncImagePainter(newTrends[index].album.cover_big),
                                contentDescription = "${newTrends[index].title}"
                            )
                            Column(
                                horizontalAlignment = Alignment.Start,
                                modifier = Modifier.padding(horizontal = 10.dp)
                            ) {
                                Text(
                                    "${newTrends[index].title}",
                                    color = Color.White,
                                    fontSize = 16.sp
                                )
                                Text(
                                    "${newTrends[index].artist.name}",
                                    color = Color.White,
                                    fontSize = 15.sp
                                )
                            }
                            Spacer(modifier = Modifier.weight(1F))
                            Box(
                                modifier = Modifier
                                    .background(
                                        color = Color.White,
                                        shape = RoundedCornerShape(16.dp)
                                    )
                                    .clip(shape = RoundedCornerShape(16.dp))
                                    .clickable {
                                        val encodedUrl = URLEncoder.encode(
                                            newTrends[index].preview,
                                            StandardCharsets.UTF_8.toString()
                                        )
                                        navController.navigate("music_player/$encodedUrl/${newTrends[index].id}")
                                    }
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.padding(10.dp)
                                ) {
                                    Image(
                                        painter = painterResource(R.drawable.play),
                                        contentDescription = "play",
                                        modifier = Modifier
                                            .size(30.dp)
                                            .padding(5.dp)
                                    )
                                    Text("Oynat")
                                }

                            }
                        }
                    }
                }
            }
        }

    }
}
