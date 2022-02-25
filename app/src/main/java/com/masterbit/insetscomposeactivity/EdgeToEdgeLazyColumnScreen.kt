package com.masterbit.insetscomposeactivity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import coil.compose.rememberImagePainter
import com.google.accompanist.insets.*
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.launch

class EdgeToEdgeLazyColumnScreen: ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            val systemUiController = rememberSystemUiController()
            val useDarkIcons = MaterialTheme.colors.isLight
            SideEffect {
                systemUiController.setSystemBarsColor(Color.Transparent, darkIcons = useDarkIcons)
            }
            ProvideWindowInsets {
                Surface(color = MaterialTheme.colors.background) {
                    EdgeToEdgeLazyColumn(systemUiController)
                }
            }
        }
    }
}

@OptIn(ExperimentalAnimatedInsets::class)
@Composable
fun EdgeToEdgeLazyColumn(systemUiController: SystemUiController, modifier: Modifier = Modifier) {
    Scaffold(
        modifier = modifier,
        topBar = {
            com.google.accompanist.insets.ui.TopAppBar(
                title = {
                    Text("Edge to Edge")
                },
                modifier = Modifier.fillMaxWidth(),
                contentPadding = rememberInsetsPaddingValues(insets = LocalWindowInsets.current.statusBars),
            )
        },
        bottomBar = {
            Spacer(modifier = Modifier
                .navigationBarsHeight()
                .fillMaxWidth())
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Filled.Face, contentDescription = "Face icon")
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) {
        val lazyListState = rememberLazyListState()
        LazyColumn(state = lazyListState, contentPadding = it) {
            items(listItems(40)) { pair ->
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 16.dp)) {
                    Image(modifier = Modifier
                        .size(64.dp)
                        .clip(
                            RoundedCornerShape(8.dp)
                        ), painter = rememberImagePainter(data = pair.second), contentDescription = null)
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(modifier = Modifier
                        .weight(1f)
                        .align(Alignment.CenterVertically), text = "Text ${pair.first}", style = MaterialTheme.typography.subtitle2)
                }
            }
        }

        LaunchedEffect(lazyListState.isScrollInProgress) {
            systemUiController.isNavigationBarVisible = lazyListState.isScrollInProgress
        }
    }
}

fun listItems(count: Int) = List(count) {
    it to randomSampleImageUrl(it)
}

fun randomSampleImageUrl(
    seed: Int,
    width:Int = 300,
    height: Int = width): String {
    return "https://picsum.photos/seed/$seed/$width/$height"
}