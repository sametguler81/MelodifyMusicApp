package com.sametguler.musicapp.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.sametguler.musicapp.model.NavItem

@Composable
fun CustomedBottomBar(
    modifier: Modifier,
    selectedIndex: MutableState<Int>,
    navItemList: List<NavItem>
) {
    Box(
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Spacer(
                modifier = Modifier.weight(1f)
            )
            Column(
                modifier = Modifier.width(80.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    tint = if (selectedIndex.value == 0) Color.White else Color.LightGray,
                    imageVector = navItemList[0].icon,
                    contentDescription = "mainPage",
                    modifier = Modifier
                        .size(40.dp)
                        .padding(horizontal = if (selectedIndex.value == 0) 1.dp else 6.dp)
                        .clickable {
                            selectedIndex.value = 0
                        }
                )
                if (selectedIndex.value == 0) {
                    Text(navItemList[0].label, color = Color.White)
                }
            }

            Spacer(
                modifier = Modifier.weight(1f)
            )
            Column(
                modifier = Modifier.width(80.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    tint = if (selectedIndex.value == 1) Color.White else Color.LightGray,
                    imageVector = navItemList[1].icon,
                    contentDescription = "mainPage",
                    modifier = Modifier
                        .size(40.dp)
                        .padding(horizontal = if (selectedIndex.value == 1) 1.dp else 6.dp)
                        .clickable {
                            selectedIndex.value = 1
                        }
                )
                if (selectedIndex.value == 1) {
                    Text(navItemList[1].label, color = Color.White)
                }
            }

            Spacer(
                modifier = Modifier.weight(1f)
            )
            Column(
                modifier = Modifier.width(80.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    tint = if (selectedIndex.value == 2) Color.White else Color.LightGray,
                    imageVector = navItemList[2].icon,
                    contentDescription = "mainPage",
                    modifier = Modifier
                        .size(40.dp)
                        .padding(horizontal = if (selectedIndex.value == 2) 1.dp else 6.dp)
                        .clickable {
                            selectedIndex.value = 2
                        }
                )
                if (selectedIndex.value == 2) {
                    Text(navItemList[2].label, color = Color.White)
                }
            }

            Spacer(
                modifier = Modifier.weight(1f)
            )
        }
    }
}

