package com.coxassginment.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.coxassginment.data.local.dao.GitUsersDao
import com.coxassginment.data.local.entity.GitUsers


@Database(entities = [GitUsers::class], version = 1, exportSchema = false)
abstract class AppDb: RoomDatabase() {

    abstract fun gitUsersDao() : GitUsersDao
}