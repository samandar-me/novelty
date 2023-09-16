package com.sdk.novelty.ui.saved

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.sdk.novelty.ui.components.AnimVisible
import com.sdk.novelty.ui.components.NetworkImage
import com.sdk.novelty.ui.components.NormalAppBar
import com.sdk.novelty.ui.components.SearchBar
import com.sdk.novelty.util.TopBarState
import com.sdk.novelty.util.byteArrayToImageBitmap
import org.koin.compose.rememberKoinInject

internal object SavedTab : Tab {
    override val options: TabOptions
        @Composable
        get() {
            val icon = rememberVectorPainter(image = Icons.Default.Bookmark)
            return remember {
                TabOptions(
                    index = 1u,
                    title = "Saved",
                    icon = icon
                )
            }
        }

    @OptIn(ExperimentalComposeUiApi::class)
    @Composable
    override fun Content() {
        val vm = rememberKoinInject<SavedViewModel>()
        var topBarState by remember { mutableStateOf(TopBarState.NORMAL) }
        val keyboardController = LocalSoftwareKeyboardController.current
        Scaffold(
            topBar = {
                AnimVisible(topBarState == TopBarState.NORMAL) {
                    NormalAppBar(
                        onOpen = {
                            topBarState = TopBarState.SEARCH
                            keyboardController?.show()
                        },
                        title = "Saved"
                    )
                }
                AnimVisible(topBarState == TopBarState.SEARCH) {
                    SearchBar(
                        onSearch = vm::searchNews,
                        onBack = {
                            vm.reloadList()
                            keyboardController?.hide()
                            //focusRequester.freeFocus()
                            topBarState = TopBarState.NORMAL
                        }
                    )
                }
            }
        ) {
            LazyColumn(
                modifier = Modifier.padding(it)
            ) {
                items(vm.localNewsList) { currentNews ->
                    ListItem(
                        modifier = Modifier.clickable {
                            //onClick(currentNews)
                        },
                        leadingContent = {
                            val image = byteArrayToImageBitmap(currentNews.imageBytes)
                            if (image != null) {
                                Image(
                                    bitmap = image,
                                    contentDescription = "image",
                                    modifier = Modifier.size(height = 100.dp, width = 150.dp).clip(
                                        RoundedCornerShape(12.dp)
                                    ),
                                    contentScale = ContentScale.Crop
                                )
                            }
                        },
                        headlineContent = {
                            Text(
                                text = currentNews.title,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                fontSize = 20.sp
                            )
                        },
                        supportingContent = {
                            Text(
                                text = currentNews.description,
                                maxLines = 3,
                                overflow = TextOverflow.Ellipsis,
                                fontSize = 14.sp
                            )
                        }
                    )
                }
            }
        }
    }
}