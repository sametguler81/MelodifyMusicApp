package com.sametguler.musicapp.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.sametguler.musicapp.R
import com.sametguler.musicapp.viewmodel.MusicViewModel

@Composable
fun ArtistDetail(id: Long, navController: NavController, viewModel: MusicViewModel) {
    var artist = viewModel.artistDetail.observeAsState().value
    var artistAlbum = viewModel.artistAlbum.observeAsState().value
    val recommendedArtist = viewModel.trendsArtist.observeAsState().value
    LaunchedEffect(Unit) {
        viewModel.getArtistDetail(id = id)
        viewModel.getArtistAlbum(id = id)
        viewModel.getTrendsArtist()
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    listOf(
                        colorResource(R.color.bg_1),
                        colorResource(R.color.bg_2),
                        colorResource(R.color.bg_3)
                    )
                )
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 40.dp, horizontal = 20.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        width = 1.dp,
                        color = Color.White,
                        shape = RoundedCornerShape(32.dp)
                    )
                    .clip(shape = RoundedCornerShape(32.dp))
                    .background(
                        color = Color.DarkGray.copy(alpha = 0.7F),
                        shape = RoundedCornerShape(32.dp)
                    )
            ) {
                Row(
                    modifier = Modifier
                        .padding(15.dp),
                    // verticalAlignment = Alignment.CenterVertically
                ) {
                    artist?.let {
                        Image(
                            modifier = Modifier
                                .size(170.dp)
                                .clip(shape = RoundedCornerShape(32.dp)),
                            painter = rememberAsyncImagePainter(artist.picture_big),
                            contentDescription = artist.name
                        )
                        Column(modifier = Modifier.padding(start = 15.dp, top = 10.dp)) {
                            Text(artist.name, color = Color.White, fontSize = 24.sp)
                        }
                    }
                }
            }
            Row(modifier = Modifier.padding(top = 30.dp, bottom = 20.dp, start = 20.dp)) {
                artist?.let {
                    Text(
                        "Sanatçının öne çıkan şarkıları",
                        color = Color.White,
                        fontSize = 20.sp
                    )
                }
            }
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(16.dp), // <- boşluk ekler
            ) {
                artistAlbum?.let {
                    val list = artistAlbum.data
                    items(count = list.size) {
                        Box(
                            modifier = Modifier
                                .border(
                                    0.dp,
                                    color = Color.Transparent,
                                    shape = RoundedCornerShape(32.dp)
                                )
                                .height(150.dp)
                                .clip(shape = RoundedCornerShape(32.dp))
                                .background(color = Color.DarkGray.copy(alpha = 0.7F))
                        ) {
                            Row(
                                modifier = Modifier.padding(5.dp),
                            ) {
                                Image(
                                    painter = rememberAsyncImagePainter(list[it].album.cover_medium),
                                    contentDescription = list[it].title,
                                    modifier = Modifier
                                        .size(140.dp)
                                        .clip(shape = RoundedCornerShape(32.dp))
                                        .background(
                                            shape = RoundedCornerShape(32.dp),
                                            color = Color.Transparent
                                        )
                                )
                                Column(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(10.dp),
                                    verticalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(
                                        list[it].album.title,
                                        color = Color.White,
                                        fontSize = 20.sp,
                                    )
                                    Column(

                                    ) {
                                        Text("Sanatçılar", color = Color.White, fontSize = 18.sp)
                                        for (album in artistAlbum.data[it].contributors) {
                                            Text("${album.name}")
                                        }
                                    }

                                }
                            }
                        }
                    }
                }
            }
            Row(modifier = Modifier.padding(top = 30.dp, bottom = 20.dp, start = 20.dp)) {
                artist?.let {
                    Text(
                        "Önerilen Şarkıcılar",
                        color = Color.White,
                        fontSize = 20.sp
                    )
                }
            }
            LazyHorizontalGrid(
                rows = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier.padding(16.dp)
            ) {
                items(count = recommendedArtist!!.size) {
                    Box(
                        modifier = Modifier
                            .size(90.dp)
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                modifier = Modifier
                                    .size(70.dp)
                                    .clip(CircleShape)
                                    .border(0.dp, color = Color.Transparent, shape = CircleShape)
                                    .background(color = Color.Transparent, shape = CircleShape),
                                painter = rememberAsyncImagePainter(recommendedArtist[it].picture_medium),
                                contentDescription = recommendedArtist[it].name
                            )
                            Text(
                                "${recommendedArtist[it].name}",
                                fontSize = 12.sp,
                                color = Color.White,
                                modifier = Modifier.padding(vertical = 7.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}
