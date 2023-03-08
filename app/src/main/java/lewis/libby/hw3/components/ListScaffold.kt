package lewis.libby.hw3.components

// Entire code from the Module 5 Example Project Movie Ui 2

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import lewis.libby.hw3.Screen

@Composable
fun <T> ListScaffold(
    @StringRes titleId: Int,
    items: List<T>,
    getId: (T) -> String,
    onSelectListScreen: (Screen) -> Unit,
    onResetDatabase: () -> Unit,
    onItemClick: (String) -> Unit,
    itemIcon: ImageVector,
    @StringRes itemIconContentDescriptionId: Int,
    itemContent: @Composable RowScope.(T) -> Unit,
) = ContactScaffold(
    title = stringResource(id = titleId),
    onSelectListScreen = onSelectListScreen,
    onResetDatabase = onResetDatabase,
) { paddingValues ->
    LazyColumn(
        modifier = Modifier
            .padding(paddingValues)
            .padding(8.dp)
    ) {
        items(
            items = items,
            key = { getId(it) },
        ) { item ->
            Card(
                elevation = 4.dp,
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(8.dp)
                        .clickable { onItemClick(getId(item)) }
                ) {
                    Icon(
                        imageVector = itemIcon,
                        tint = MaterialTheme.colors.surface,
                        contentDescription = stringResource(id = itemIconContentDescriptionId),
                        modifier = Modifier
                            .size(48.dp)
                            .background(
                                color = MaterialTheme.colors.primary,
                                shape = CircleShape
                            )
                    )
                    itemContent(item)
                }
            }
        }
    }
}