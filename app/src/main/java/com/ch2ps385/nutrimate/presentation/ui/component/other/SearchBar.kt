package com.ch2ps385.nutrimate.presentation.ui.component.other

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.R
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ch2ps385.nutrimate.presentation.ui.theme.NutriMateTheme
import com.ch2ps385.nutrimate.presentation.ui.theme.neutralColor1
import com.ch2ps385.nutrimate.presentation.ui.theme.solidWhite

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    SearchBar(
        query = query,
        onQueryChange = onQueryChange,
        onSearch = {},
        active = false,
        onActiveChange = {},
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
                tint = neutralColor1
            )
        },
        placeholder = {
            Text(
                stringResource(R.string.search_bar_search),
                style = MaterialTheme.typography.labelSmall
            )
        },
        shape = MaterialTheme.shapes.large,
        modifier = modifier
            .background(color = solidWhite)
            .padding(16.dp)
            .fillMaxWidth()
            .heightIn(min = 40.dp)
            .width(312.dp)
    ) {

    }
}

@Composable
@Preview(showBackground = true)
fun SearchBarPreview() {
    NutriMateTheme{
        // Wrap your composable in a Surface for the preview
        Surface {
//            SearchBar(
//                query = "Your query",
//                onQueryChange = { /* Handle query change */ }
//            )
        }
    }
}