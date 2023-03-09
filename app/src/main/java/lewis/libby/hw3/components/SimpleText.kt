package lewis.libby.hw3.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

//To display simple text and have onClick function
@Composable
fun SimpleText(
    text: String,
    modifier: Modifier = Modifier
) =
    Text(
        text = text,
        modifier = modifier
            .padding(8.dp)
    )
