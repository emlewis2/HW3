package lewis.libby.hw3.screens

// Entire code taken and adapted from Movie Ui 2 example project

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import lewis.libby.hw3.R
import lewis.libby.hw3.Screen
import lewis.libby.hw3.components.ContactScaffold
import lewis.libby.hw3.components.TextEntry
import lewis.libby.hw3.repository.ContactDto
import lewis.libby.hw3.repository.ContactRepository

@Composable
fun ContactEdit(
    contactId: String,
    fetchContact: suspend (String) -> ContactDto,
    onSelectListScreen: (Screen) -> Unit,
    onResetDatabase: () -> Unit,
    onContactUpdate: (ContactDto) -> Unit,
) {
    var contact by remember { mutableStateOf<ContactDto?>(null) }

    LaunchedEffect(key1 = contactId) {
        // starts a coroutine to fetch the rating
        contact = fetchContact(contactId)
    }

    ContactScaffold(
        title = contact?.firstName ?: stringResource(id = R.string.loading),
        onSelectListScreen = onSelectListScreen,
        onResetDatabase = onResetDatabase,
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxHeight()
        ) {
            TextEntry(
                labelId = R.string.first_name,
                placeholderId = R.string.contact_first_name_placeholder,
                value = contact?.firstName ?: "",
                onValueChange = {
                    contact = contact?.copy(firstName = it)?.apply {
                        onContactUpdate(this)
                    }
                },
            )
//            TextEntry(
//                labelId = R.string.description,
//                placeholderId = R.string.movie_description_placeholder,
//                value = movie?.description ?: "",
//                onValueChange = {
//                    movie = movie?.copy(description = it)?.apply {
//                        onMovieUpdate(this)
//                    }
//                },
//            )
        }
    }
}