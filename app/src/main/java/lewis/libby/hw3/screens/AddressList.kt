package lewis.libby.hw3.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import lewis.libby.hw3.R
import lewis.libby.hw3.Screen
import lewis.libby.hw3.components.ContactScaffold
import lewis.libby.hw3.components.SimpleText
import lewis.libby.hw3.repository.AddressDto

//Screen setup for list of all addresses
@Composable
fun AddressList(
    addresses: List<AddressDto>,
    onAddressClick: (String) -> Unit,
    onSelectListScreen: (Screen) -> Unit,
    onResetDatabase: () -> Unit,
) = ContactScaffold(
    title = stringResource(id = R.string.screen_title_addresses),
    onSelectListScreen = onSelectListScreen,
    onResetDatabase = onResetDatabase,
) { paddingValues ->
    Column(modifier = Modifier.padding(paddingValues)) {
        addresses.forEach {
            SimpleText(text = "${it.type}: ${it.street}") {
                onAddressClick(it.id)
            }
        }
    }
}