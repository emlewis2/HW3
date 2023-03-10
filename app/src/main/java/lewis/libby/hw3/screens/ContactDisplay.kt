package lewis.libby.hw3.screens

// Entire code taken and adapted from Movie Ui 2 example project

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.layout.Row
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
import lewis.libby.hw3.components.SimpleText
import lewis.libby.hw3.repository.ContactWithAddressesDto
import java.util.*

//Display screen setup for Contact
@Composable
fun ContactDisplay(
    contactId: String,      //Contact id
    //Returns ContactWithAddressesDto
    fetchContactWithAddresses: suspend (String) -> ContactWithAddressesDto,
    onSelectListScreen: (Screen) -> Unit,
    onResetDatabase: () -> Unit,
    onEdit: (String) -> Unit,
    onAdd: (String) -> Unit,
    onAbout: () -> Unit,
    ) {
    var contactWithAddressesDto by remember { mutableStateOf<ContactWithAddressesDto?>(null) }

    //Launching coroutine
    LaunchedEffect(key1 = contactId) {
        contactWithAddressesDto = fetchContactWithAddresses(contactId)
    }

    //Display all info for an individual contact from the ContactDto
    ContactScaffold(
        title = contactWithAddressesDto?.contact?.firstName ?: stringResource(id = R.string.loading),
        onSelectListScreen = onSelectListScreen,
        onResetDatabase = onResetDatabase,
        onAbout = onAbout,
        onEdit =
        contactWithAddressesDto?.let { contactWithAddresses ->
            {
                onEdit(contactWithAddresses.contact.id)
            }
        },
        onAdd = {
            onAdd(UUID.randomUUID().toString())
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            contactWithAddressesDto?.let { contactWithAddresses ->
                Row {
                    SimpleText(text = "${stringResource(id = R.string.name)}: " +
                            "${contactWithAddresses.contact.firstName} " +
                            contactWithAddresses.contact.lastName
                    )
                }
                Row {
                    SimpleText("${stringResource(id = R.string.home_phone)}: " +
                            contactWithAddresses.contact.homePhone
                    )
                }
                Row {
                    SimpleText(text = "${stringResource(id = R.string.work_phone)}: " +
                            contactWithAddresses.contact.workPhone
                    )
                }
                Row {
                    SimpleText(text = "${stringResource(id = R.string.mobile_phone)}: " +
                            contactWithAddresses.contact.mobilePhone
                    )
                }
                Row {
                    SimpleText(text = "${stringResource(id = R.string.email)}: " +
                            contactWithAddresses.contact.email
                    )
                }
                //Display each address for the specific contact
                contactWithAddresses.addresses.forEach { address ->
                    Row {
                        SimpleText(text = "${address.type}:")
                    }
                    Row {
                        SimpleText(text = "\t${address.street}")
                    }
                    Row {
                        SimpleText(text = "\t${address.city}, ${address.state} ${address.zip}")
                    }
                }
            }
        }
    }
}