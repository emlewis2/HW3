package lewis.libby.hw3.screens

// Entire code taken and adapted from Movie Ui 2 example project

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import lewis.libby.hw3.R
import lewis.libby.hw3.Screen
import lewis.libby.hw3.components.ContactScaffold
import lewis.libby.hw3.components.TextEntry
import lewis.libby.hw3.repository.ContactDto
import lewis.libby.hw3.components.SimpleText
import lewis.libby.hw3.repository.AddressDto
import lewis.libby.hw3.repository.ContactWithAddressesDto

@Composable
fun ContactEdit(
    contactId: String,
    fetchContact: suspend (String) -> ContactDto,
    addresses: List<AddressDto>?,
    setAddresses: (List<AddressDto>?) -> Unit,
    fetchContactWithAddresses: suspend (String) -> ContactWithAddressesDto,
    onSelectListScreen: (Screen) -> Unit,
    onResetDatabase: () -> Unit,
    onContactUpdate: (ContactDto) -> Unit,
    onAddressClick: (String) -> Unit,
    onDeleteAddress: (String) -> Unit,
    onAddAddress: () -> Unit,
    onAbout: () -> Unit,
) {
    var contact by remember { mutableStateOf<ContactDto?>(null) }

    LaunchedEffect(key1 = contactId) {
        // starts a coroutine to fetch the rating
        contact = fetchContact(contactId)
    }

    var contactWithAddressesDto by remember { mutableStateOf<ContactWithAddressesDto?>(null) }

    LaunchedEffect(key1 = contactId) {
        // starts a coroutine to fetch the rating
        contactWithAddressesDto = fetchContactWithAddresses(contactId)
    }

//    var addresses by remember { mutableStateOf<List<AddressDto>?>(emptyList())}

    var addressList = contactWithAddressesDto?.addresses

    LaunchedEffect(key1 = addressList) {
        setAddresses(addressList)
    }

    ContactScaffold(
        title = contact?.firstName ?: stringResource(id = R.string.loading),
        onSelectListScreen = onSelectListScreen,
        onResetDatabase = onResetDatabase,
        onAddAddress = onAddAddress,
        onAbout = onAbout,
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            TextEntry(
                labelId = R.string.first_name,
                placeholderId = R.string.first_name_placeholder,
                value = contact?.firstName ?: "",
                onValueChange = {
                    contact = contact?.copy(firstName = it)?.apply {
                        onContactUpdate(this)
                    }
                },
            )
            TextEntry(
                labelId = R.string.last_name,
                placeholderId = R.string.last_name_placeholder,
                value = contact?.lastName ?: "",
                onValueChange = {
                    contact = contact?.copy(lastName = it)?.apply {
                        onContactUpdate(this)
                    }
                },
            )
            TextEntry(
                labelId = R.string.home_phone,
                placeholderId = R.string.home_phone_placeholder,
                value = contact?.homePhone ?: "",
                onValueChange = {
                    contact = contact?.copy(homePhone = it)?.apply {
                        onContactUpdate(this)
                    }
                },
            )
            TextEntry(
                labelId = R.string.work_phone,
                placeholderId = R.string.work_phone_placeholder,
                value = contact?.workPhone ?: "",
                onValueChange = {
                    contact = contact?.copy(workPhone = it)?.apply {
                        onContactUpdate(this)
                    }
                },
            )
            TextEntry(
                labelId = R.string.mobile_phone,
                placeholderId = R.string.mobile_phone_placeholder,
                value = contact?.mobilePhone ?: "",
                onValueChange = {
                    contact = contact?.copy(mobilePhone = it)?.apply {
                        onContactUpdate(this)
                    }
                },
            )
            TextEntry(
                labelId = R.string.email,
                placeholderId = R.string.email_placeholder,
                value = contact?.email ?: "",
                onValueChange = {
                    contact = contact?.copy(email = it)?.apply {
                        onContactUpdate(this)
                    }
                },
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                SimpleText(
                    text = stringResource(id = R.string.section_title_addresses),
                    modifier = Modifier.weight(1f)
                )
                IconButton(
                    onClick = onAddAddress,
                    modifier = Modifier
                        .size(48.dp)
//                            .fillMaxWidth()
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        tint = Color.Green,
                        contentDescription = stringResource(id = R.string.add_address),
                    )
                }
            }
            addressList?.forEach { address ->
                Card(
                    elevation = 4.dp,
                    backgroundColor = MaterialTheme.colors.surface,
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                        .pointerInput(true) {
                            detectTapGestures(
                                onTap = { onAddressClick(address.id) }
                            )
                        }
                ) {
                    Column(
                        modifier = Modifier
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                        ) {
                            SimpleText(
                                text = address.type,
                                modifier = Modifier.weight(1f)
                            )
                            IconButton(
                                onClick = {
                                    /* Currently deleting address but not updating the screen
                                     * Backing out or going to next screen and coming back to
                                     * ContactEdit screen will update the screen with the deleted
                                     * address card removed
                                    */
                                    onDeleteAddress(address.id)
                                    addressList?.minus(address)
                                    setAddresses(addressList)
                                          },
                                modifier = Modifier
                                    .size(48.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Delete,
                                    tint = Color.Red,
                                    contentDescription = stringResource(id = R.string.delete_address),
                                )
                            }
                        }
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                        ) {
                            SimpleText(text = address.street)
                        }
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                        ) {
                            SimpleText(text = "${address.city} ${address.state} ${address.zip}")
                        }
                    }
                }
            }
        }
    }
}