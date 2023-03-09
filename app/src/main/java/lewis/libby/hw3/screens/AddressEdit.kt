package lewis.libby.hw3.screens

// Entire code taken and adapted from Movie Ui 2 example project

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import lewis.libby.hw3.repository.AddressDto

@Composable
fun AddressEdit(
    addressId: String,
    fetchAddress: suspend (String) -> AddressDto,
    onSelectListScreen: (Screen) -> Unit,
    onResetDatabase: () -> Unit,
    onAddressUpdate: (AddressDto) -> Unit,
    onAbout: () -> Unit,
) {
    var address by remember { mutableStateOf<AddressDto?>(null) }

    LaunchedEffect(key1 = addressId) {
        // starts a coroutine to fetch the rating
        address = fetchAddress(addressId)
    }

    ContactScaffold(
        title = address?.type ?: stringResource(id = R.string.loading),
        onSelectListScreen = onSelectListScreen,
        onResetDatabase = onResetDatabase,
        onAbout = onAbout
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            TextEntry(
                labelId = R.string.type,
                placeholderId = R.string.type_placeholder,
                value = address?.type ?: "",
                onValueChange = {
                    address = address?.copy(type = it)?.apply {
                        onAddressUpdate(this)
                    }
                },
            )
            TextEntry(
                labelId = R.string.street,
                placeholderId = R.string.street_placeholder,
                value = address?.street ?: "",
                onValueChange = {
                    address = address?.copy(street = it)?.apply {
                        onAddressUpdate(this)
                    }
                },
            )
            TextEntry(
                labelId = R.string.city,
                placeholderId = R.string.city_placeholder,
                value = address?.city ?: "",
                onValueChange = {
                    address = address?.copy(city = it)?.apply {
                        onAddressUpdate(this)
                    }
                },
            )
            TextEntry(
                labelId = R.string.state,
                placeholderId = R.string.state_placeholder,
                value = address?.state ?: "",
                onValueChange = {
                    address = address?.copy(state = it)?.apply {
                        onAddressUpdate(this)
                    }
                },
            )
            TextEntry(
                labelId = R.string.zip,
                placeholderId = R.string.zip_placeholder,
                value = address?.zip ?: "",
                onValueChange = {
                    address = address?.copy(zip = it)?.apply {
                        onAddressUpdate(this)
                    }
                },
            )
        }
    }
}