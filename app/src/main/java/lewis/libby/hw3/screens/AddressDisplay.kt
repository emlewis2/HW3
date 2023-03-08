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
import lewis.libby.hw3.repository.AddressDto

//Display screen setup for Address
@Composable
fun AddressDisplay(
    addressId: String,     //Address id
    fetchAddress: suspend (String) -> AddressDto,
    onSelectListScreen: (Screen) -> Unit,
    onResetDatabase: () -> Unit,
//    onAddressClick: (String) -> Unit,
) {
    var addressDto by remember { mutableStateOf<AddressDto?>(null) }

    //Launching coroutine
    LaunchedEffect(key1 = addressId) {
        addressDto = fetchAddress(addressId)
    }

    //Display all info for an individual address from the AddressDto
//    SimpleText(text = "Address")
    ContactScaffold(
        title = addressDto?.street ?: stringResource(id = R.string.loading),
        onSelectListScreen = onSelectListScreen,
        onResetDatabase = onResetDatabase,
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            addressDto?.let { address ->
                Row {
                    SimpleText(text = "Type: ${address.type}")
                }
                Row {
                    SimpleText(text = "Street: ${address.street}")
                }
                Row {
                    SimpleText(text = "City: ${address.city}")
                }
                Row {
                    SimpleText(text = "State: ${address.state}")
                }
                Row {
                    SimpleText(text = "Zip: ${address.zip}")
                }
            }
        }
    }
}