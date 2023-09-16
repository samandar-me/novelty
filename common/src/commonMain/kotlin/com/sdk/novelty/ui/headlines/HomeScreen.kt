package com.sdk.novelty.ui.headlines

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.sdk.novelty.ui.components.ContentList
import com.sdk.novelty.ui.components.Loading
import com.sdk.novelty.ui.components.NetworkImage
import com.sdk.novelty.ui.components.NormalAppBar
import com.sdk.novelty.ui.detail.DetailScreen
import com.sdk.novelty.ui.headlines.top.TopBarItem
import com.sdk.novelty.ui.search.SearchScreen
import org.koin.compose.koinInject
import org.koin.compose.rememberKoinInject

internal class HomeScreen(
    private val state: State<HomeState>,
    private val loadNews: (Int) -> Unit
) : Screen {

    @Composable
    override fun Content() {
        var selectedTabIndex by remember { mutableStateOf(0) }
        val tabs = listOf(TopBarItem.General,TopBarItem.Business,TopBarItem.Traveling)
        val nav = LocalNavigator.currentOrThrow
        Scaffold(
            topBar = {
                NormalAppBar(
                    onOpen = {
                        nav.push(SearchScreen)
                    },
                    title = "Novelty"
                )
            }
        ) {
            Column(
                modifier = Modifier.padding(it).padding(bottom = 80.dp)
            ) {
                TabRow(
                    selectedTabIndex = selectedTabIndex
                ) {
                    tabs.forEachIndexed { index, topBarItem ->
                        Tab(
                            selected = selectedTabIndex == index,
                            onClick = {
                                selectedTabIndex = index
                                loadNews(selectedTabIndex)
                            },
                            icon = {
                                Icon(
                                    imageVector = topBarItem.icon,
                                    contentDescription = topBarItem.label
                                )
                            },
                            text = {
                                Text(text = topBarItem.label)
                            }
                        )
                    }
                }
                if(state.value.error.isNotBlank()) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(text = state.value.error, fontSize = 25.sp)
                    }
                }
                if(state.value.isLoading) {
                    Loading()
                }
                if(state.value.isLoaded) {
                    ContentList(
                        list = state.value.success[selectedTabIndex],
                        onClick = { news ->
                            nav.push(DetailScreen(news))
                        }
                    )
                }
            }
        }
    }
}