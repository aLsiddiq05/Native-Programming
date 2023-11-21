/*
Muhammad Amier
S63650
 */
package com.example.a30daysnovel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardArrowUp
//import androidx.compose.material.icons.filled.ExpandLess
//import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.a30daysnovel.data.Datasource
import com.example.a30daysnovel.model.NovelTip
import com.example.a30daysnovel.ui.theme._30DaysNovelTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            _30DaysNovelTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    _30DaysNovelApp()
                }
            }
        }
    }
}

@Composable
fun _30DaysNovelApp() {
    _30DaysNovelList(
        novelList = Datasource().loadNovelTips(),
    )
}

@Composable
fun _30DaysNovelList(novelList: List<NovelTip>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(novelList) { novelTip ->
            _30DaysNovelCard(
                novelTip = novelTip,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Composable
fun _30DaysNovelCard(novelTip: NovelTip, modifier: Modifier = Modifier) {
    Card(modifier = modifier) {
        Row {
            // Left column with the novel picture, author, and novel name
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.4f)
                    .padding(16.dp)
            ) {
                Image(
                    painter = painterResource(novelTip.imageResourceId),
                    contentDescription = stringResource(novelTip.author),
                    modifier = Modifier
                        .height(120.dp)
                        .fillMaxWidth()
                        .clip(MaterialTheme.shapes.medium),
                    contentScale = ContentScale.Crop
                )
                Text(
                    text = LocalContext.current.getString(novelTip.author),
                    modifier = Modifier.padding(top = 8.dp),
                    style = MaterialTheme.typography.labelSmall // Use smaller text style
                )
                Text(
                    text = LocalContext.current.getString(novelTip.novel),
                    style = MaterialTheme.typography.labelMedium // Use smaller text style
                )
            }

            // Right column with the expandable novel description
            var expanded by remember { mutableStateOf(false) }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clickable { expanded = !expanded }
                ) {
                    Icon(
                        imageVector = if (expanded) Icons.Default.KeyboardArrowDown else Icons.Default.KeyboardArrowRight,
                        contentDescription = null, // Content description not needed for decorative icons
                        tint = MaterialTheme.colorScheme.primary // Use primary color for icon tint
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Click the Arrow Icon to see the novel's description",
                        style = MaterialTheme.typography.bodyMedium,
                    )
                }
                if (expanded) {
                    Text(
                        text = LocalContext.current.getString(novelTip.description),
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }
        }
    }
}



@Preview
@Composable
private fun _30DaysNovelCardPreview() {
    _30DaysNovelCard(NovelTip(R.string.author1, R.string.novel1, R.string.novel_description1, R.drawable._984))
}