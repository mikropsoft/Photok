package dev.leonlatsch.photok.database

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.leonlatsch.photok.database.PhotokDatabase.Companion.VERSION

@Database(
    entities = [Photo::class],
    version = VERSION,
    exportSchema = false
)
abstract class PhotokDatabase : RoomDatabase() {

    companion object {
        const val VERSION = 1
        const val DATABASE_NAME = "photok"
    }

    abstract fun getPhotoDao(): PhotoDao
}