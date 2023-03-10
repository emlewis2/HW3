package lewis.libby.hw3.repository

// Entire code was taken and adapted from the Movie example project

import kotlinx.coroutines.flow.Flow

//Contact Repository interface for all functions from the DAO
interface ContactRepository {
    val contactsFlow: Flow<List<ContactDto>>
    val addressesFlow: Flow<List<AddressDto>>

    suspend fun getContactWithAddresses(id: String): ContactWithAddressesDto

    suspend fun getContact(id: String): ContactDto

    suspend fun getAddress(id: String): AddressDto

    suspend fun insert(address: AddressDto)
    suspend fun insert(contact: ContactDto)

    suspend fun update(address: AddressDto)
    suspend fun update(contact: ContactDto)

    suspend fun delete(address: AddressDto)
    suspend fun delete(contact: ContactDto)

    suspend fun deleteContactsById(ids: Set<String>)

    suspend fun resetDatabase()

    suspend fun addContact(newId: String)

    suspend fun deleteAddressById(id: String)

    suspend fun addAddress(contactId: String, newAddressId: String)
}