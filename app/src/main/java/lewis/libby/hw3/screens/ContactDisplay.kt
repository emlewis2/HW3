package lewis.libby.hw3.screens

import androidx.compose.foundation.clickable
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

//Display screen setup for Contact
@Composable
fun ContactDisplay(
    contactId: String,      //Contact id
    //Returns ContactWithAddressesDto
    fetchContactWithAddresses: suspend (String) -> ContactWithAddressesDto,
    onSelectListScreen: (Screen) -> Unit,
    onResetDatabase: () -> Unit,
    onContactClick: (String) -> Unit,
) {
    var contactWithAddressesDto by remember { mutableStateOf<ContactWithAddressesDto?>(null) }

    //Launching coroutine
    LaunchedEffect(key1 = contactId) {
        contactWithAddressesDto = fetchContactWithAddresses(contactId)
    }

    //Display all info for an individual contact from the ContactDto
//    SimpleText(text = "Contact")
    ContactScaffold(
        title = contactWithAddressesDto?.contact?.firstName ?: stringResource(id = R.string.loading),
        onSelectListScreen = onSelectListScreen,
        onResetDatabase = onResetDatabase,
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            contactWithAddressesDto?.let { contactWithAddresses ->
                Row {
                    SimpleText(text = "Name: ${contactWithAddresses.contact.firstName} " +
                            contactWithAddresses.contact.lastName
                    )
                }
                Row {
                    SimpleText("Home Phone: ${contactWithAddresses.contact.homePhone}")
                }
                Row {
                    SimpleText(text = "Work Phone: ${contactWithAddresses.contact.workPhone}")
                }
                Row {
                    SimpleText(text = "Mobile Phone: ${contactWithAddresses.contact.mobilePhone}")
                }
                Row {
                    SimpleText(text = "Email: ${contactWithAddresses.contact.email}")
                }
                //Display each address for the specific contact
                contactWithAddresses.addresses.forEach { address ->
                    SimpleText(
                        text = "${address.type}: ${address.street}",
                        modifier = Modifier.clickable {
                            onContactClick(address.id)
                            }
                    )
                }
            }
        }
    }
}