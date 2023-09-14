package com.sdk.novelty.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sdk.novelty.domain.model.News

@Composable
fun ContentList(
    modifier: Modifier = Modifier,
    list: List<News>,
    onClick: (News) -> Unit
) {
    LazyColumn {
        items(
            items = list
        ) { currentNews ->
            ListItem(
                modifier = modifier.clickable {
                    onClick(currentNews)
                },
                leadingContent = {
                    NetworkImage(
                        modifier = Modifier.size(height = 100.dp, width = 150.dp),
                        url = "${currentNews.urlToImage}"
                    )
                },
                headlineContent = {
                    Text(
                        text = currentNews.title ?: "Title",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        fontSize = 20.sp
                    )
                },
                supportingContent = {
                    Text(
                        text = currentNews.description ?: "Description",
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis,
                        fontSize = 14.sp
                    )
                }
            )
        }
    }
}