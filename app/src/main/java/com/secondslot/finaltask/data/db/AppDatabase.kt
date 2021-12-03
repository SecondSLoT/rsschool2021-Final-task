package com.secondslot.finaltask.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.secondslot.finaltask.data.db.dao.MessageDao
import com.secondslot.finaltask.data.db.dao.MessageWithReactionsDao
import com.secondslot.finaltask.data.db.dao.ReactionDao
import com.secondslot.finaltask.data.db.dao.StreamDao
import com.secondslot.finaltask.data.db.dao.StreamWithTopicsDao
import com.secondslot.finaltask.data.db.dao.TopicDao
import com.secondslot.finaltask.data.db.dao.UserDao
import com.secondslot.finaltask.data.db.model.entity.MessageEntity
import com.secondslot.finaltask.data.db.model.entity.ReactionEntity
import com.secondslot.finaltask.data.db.model.entity.StreamEntity
import com.secondslot.finaltask.data.db.model.entity.TopicEntity
import com.secondslot.finaltask.data.db.model.entity.UserEntity

@Database(
    entities = [
        StreamEntity::class,
        TopicEntity::class,
        UserEntity::class,
        MessageEntity::class,
        ReactionEntity::class
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract val streamDao: StreamDao

    abstract val topicDao: TopicDao

    abstract val streamWithTopicsDao: StreamWithTopicsDao

    abstract val userDao: UserDao

    abstract val messageDao: MessageDao

    abstract val messageWithReactionDao: MessageWithReactionsDao

    abstract val reactionDao: ReactionDao

    companion object {
        const val DATABASE_NAME = "app_database"
    }
}
