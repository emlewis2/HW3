package lewis.libby.hw3.data

// Entire code was taken and adapted from the Movie example project

import androidx.room.Database
import androidx.room.RoomDatabase

//Creating ContactDatabase using Room
@Database(
    version = 1,
    entities = [
        Address::class,
        Contact::class,
    ],
    exportSchema = false
)
abstract class ContactDatabase: RoomDatabase() {
    abstract val dao: ContactDAO
}