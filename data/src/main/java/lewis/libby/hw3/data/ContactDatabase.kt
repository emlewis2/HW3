package lewis.libby.hw3.data

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