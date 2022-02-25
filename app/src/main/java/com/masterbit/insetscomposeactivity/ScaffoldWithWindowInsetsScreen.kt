package com.masterbit.insetscomposeactivity

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.rememberInsetsPaddingValues
import com.google.accompanist.insets.ui.BottomNavigation

@Composable
fun ScaffoldWithWindowInsets(state: ScaffoldState) {
    Scaffold(
        Modifier.fillMaxSize(),
        scaffoldState = state,
        topBar = {
            TopAppBar(contentPadding = rememberInsetsPaddingValues(insets = LocalWindowInsets.current.statusBars)) {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("Window Insets")
                }
            }
        },
        bottomBar = {
            BottomNavigation(contentPadding = rememberInsetsPaddingValues(
                insets = LocalWindowInsets.current.navigationBars
            )
            ) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    Button(modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight(), onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Filled.Home, contentDescription = null)
                    }

                    Button(modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight(), onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Filled.Settings, contentDescription = null)
                    }

                    Button(modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight(), onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Filled.Person, contentDescription = null)
                    }
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { /*TODO*/ }, shape = CircleShape) {
                Image(imageVector = Icons.Filled.Add, contentDescription = null)
            }
        },
        floatingActionButtonPosition = FabPosition.End) {
        LazyColumn(Modifier.padding(it)) {
            repeat(20) {
                item {
                    Text(modifier = Modifier
                        .defaultMinSize(minHeight = 64.dp)
                        .fillMaxWidth(),text = "item $it", textAlign = TextAlign.Center)
                }
            }
        }
    }
}