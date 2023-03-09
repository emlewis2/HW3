package lewis.libby.hw3.components

// Entire code taken and adapted from the Movie Ui 2 example project

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import lewis.libby.hw3.R

@Composable
fun TextEntry(
    @StringRes labelId: Int,
    @StringRes placeholderId: Int,
    value: String?,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) =
    OutlinedTextField(
        label = {
            Text(
                text = stringResource(id = labelId),
            )
        },
        placeholder = {
            Text(
                text =
                if (value == null) {
                    stringResource(id = R.string.loading)
                } else {
                    stringResource(id = placeholderId)
                }
            )
        },
        value = value ?: "",
        onValueChange = {
            if (value != null) {
                onValueChange(it)
            }
        },
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
    )