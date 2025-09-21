package com.sametguler.musicapp.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.sametguler.musicapp.R
import com.sametguler.musicapp.viewmodel.MusicViewModel
import kotlinx.coroutines.delay

@Composable
fun MusicPlayer(url: String, id: Long, navController: NavController, viewModel: MusicViewModel) {
    val context = LocalContext.current
    val exoPlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            setMediaItem(MediaItem.fromUri(url))
            prepare()
        }
    }
    val currentPosition = remember { mutableStateOf(0L) }
    val duration = remember { mutableStateOf(0L) }
    var isPlaying = remember { mutableStateOf(false) }
    var album = viewModel.playerAlbum.observeAsState().value


    LaunchedEffect(Unit) {
        viewModel.getPlayerAlbum(id = id)
        isPlaying.value = true
        exoPlayer.play()
    }

    LaunchedEffect(exoPlayer) {
        exoPlayer.addListener(object : Player.Listener {
            override fun onMediaItemTransition(mediaItem: MediaItem?, reason: Int) {
                duration.value = exoPlayer.duration
            }
        })

        while (true) {
            currentPosition.value = exoPlayer.currentPosition
            delay(500L)
        }
    }


    DisposableEffect(Unit) {
        onDispose {
            exoPlayer.release()
        }
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
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        album?.let { it ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp, vertical = 40.dp),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Image(
                        modifier = Modifier
                            .size(300.dp)
                            .clip(shape = RoundedCornerShape(32.dp))
                            .border(
                                0.dp,
                                color = Color.Transparent,
                                shape = RoundedCornerShape(32.dp)
                            )
                            .background(
                                color = Color.Transparent,
                                shape = RoundedCornerShape(32.dp)
                            ),
                        painter = rememberAsyncImagePainter(it.album.cover_medium),
                        contentDescription = it.title
                    )
                    Text(
                        it.title,
                        color = Color.White,
                        fontSize = 24.sp,
                        modifier = Modifier.padding(vertical = 10.dp)
                    )

                    for (artist in it.contributors) {
                        Text(
                            text = artist.name,
                            color = Color.White,
                            fontSize = 20.sp,
                            modifier = Modifier.padding(vertical = 3.dp)
                        )
                    }

                }
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        "${(currentPosition.value / 1000 / 60)}:${
                            (currentPosition.value / 1000 % 60).toString().padStart(2, '0')
                        } / " +
                                "${(duration.value / 1000 / 60)}:${
                                    (duration.value / 1000 % 60).toString().padStart(2, '0')
                                }",
                        color = Color.White
                    )

                    val sliderMax = if (duration.value > 0) duration.value.toFloat() else 0f

                    Slider(
                        value = currentPosition.value.coerceIn(0L, duration.value).toFloat(),
                        onValueChange = { newValue -> exoPlayer.seekTo(newValue.toLong()) },
                        valueRange = 0f..sliderMax
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedButton(
                        onClick = {
                            if (isPlaying.value) {
                                isPlaying.value = !isPlaying.value
                                exoPlayer.pause()
                            } else {
                                isPlaying.value = !isPlaying.value
                                exoPlayer.play()
                            }
                        },
                        contentPadding = PaddingValues(0.dp),
                        modifier = Modifier
                            .size(70.dp)
                            .clip(shape = CircleShape)
                            .background(
                                brush = Brush.horizontalGradient(
                                    listOf(
                                        colorResource(R.color.bg_1),
                                        colorResource(R.color.bg_2),
                                        colorResource(R.color.bg_3)
                                    )
                                ), shape = CircleShape
                            )
                    ) {
                        Icon(
                            tint = Color.White,
                            modifier = Modifier
                                .padding(vertical = 20.dp)
                                .size(30.dp)
                                .background(shape = CircleShape, color = Color.Transparent),
                            painter = if (isPlaying.value) painterResource(R.drawable.pause) else painterResource(
                                R.drawable.play2
                            ), contentDescription = "playAndPause"
                        )
                    }
                    IconButton(onClick = {}, modifier = Modifier.padding(horizontal = 10.dp)) {
                        Icon(
                            Icons.Default.Favorite,
                            tint = Color.Red,
                            contentDescription = "favorite",
                            modifier = Modifier.size(40.dp)
                        )
                    }
                }

            }
        }
    }
}
