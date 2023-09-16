package com.sdk.novelty.ui.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.sdk.novelty.domain.model.News
import com.sdk.novelty.domain.model.NewsEntity
import com.sdk.novelty.ui.components.NetworkImage
import com.sdk.novelty.util.Other
import org.koin.compose.rememberKoinInject
import org.koin.core.parameter.parametersOf

internal data class DetailScreen(
    private val news: News
) : Screen {

    init {
        Other.isBottomBarVisible = false
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val nav = LocalNavigator.currentOrThrow
        val vm = rememberKoinInject<DetailViewModel>(parameters = { parametersOf(news.title) })
        val scrollBehavior =
            TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())
        val lazyListState = rememberLazyListState()

        Scaffold(
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = {
                MediumTopAppBar(
                    title = {
                        Text(
                            text = "News Detail"
                        )
                    },
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                nav.popUntilRoot()
                                Other.isBottomBarVisible = true
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "back"
                            )
                        }
                    },
                    actions = {
                        IconButton(
                            onClick = {
                                vm.saveNews(news)
                            }
                        ) {
                            Icon(
                                imageVector = if(vm.isSaved) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                                contentDescription = "Favorite",
                                tint = if (vm.isSaved) Color.Red else Color.Gray
                            )
                        }
                    },
                    scrollBehavior = scrollBehavior
                )
            }
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize().padding(it),
                state = lazyListState
            ) {
                item {
                    Text(
                        text = news.title ?: "Title",
                        fontSize = 27.sp,
                        modifier = Modifier.padding(12.dp)
                    )
                }
                item {
                    NetworkImage(
                        url = news.urlToImage.toString(),
                        shape = 0.dp,
                        modifier = Modifier
//                            .graphicsLayer {
//                                sY += lazyListState.firstVisibleItemScrollOffset - previousOffset
//                                translationY = sY * .5f
//                                previousOffset = lazyListState.firstVisibleItemScrollOffset
//                            }
                            .height(250.dp)
                            .fillMaxWidth()
                    )
                }
                item {
                    Column(Modifier.padding(12.dp)) {
                        Text(
                            text = news.sourceName.toString(),
                            fontSize = 25.sp
                        )
                        Spacer(Modifier.height(4.dp))
                        Text(
                            text = news.publishedAt.toString(),
                            fontSize = 13.sp
                        )
                        Spacer(Modifier.height(4.dp))
                        Text(
                            text = news.description.toString(),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}