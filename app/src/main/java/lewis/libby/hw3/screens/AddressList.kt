package lewis.libby.hw3.screens

//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
//import androidx.compose.material.Card
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.res.stringResource
//import androidx.compose.ui.unit.dp
//import lewis.libby.hw3.R
//import lewis.libby.hw3.Screen
//import lewis.libby.hw3.components.ContactScaffold
//import lewis.libby.hw3.components.SimpleText
//import lewis.libby.hw3.repository.AddressDto
//
////Screen setup for list of all addresses
//@Composable
//fun AddressList(
//    addresses: List<AddressDto>,
//    onAddressClick: (String) -> Unit,
//    onSelectListScreen: (Screen) -> Unit,
//    onResetDatabase: () -> Unit,
//) = ContactScaffold(
//    title = stringResource(id = R.string.screen_title_addresses),
//    onSelectListScreen = onSelectListScreen,
//    onResetDatabase = onResetDatabase,
//) { paddingValues ->
//    LazyColumn(
//        modifier = Modifier.padding(paddingValues)
//    ) {
//        items(
//            items = addresses,
//            key = { it.id },
//        ) {
//            Card(
//                elevation = 4.dp,
//                modifier = Modifier
//                    .padding(8.dp)
//                    .fillMaxWidth()
//            ) {
//                SimpleText(text = "${it.type}: ${it.street}") {
//                    onAddressClick(it.id)
//                }
//            }
//        }
//    }
//}