package com.coxassginment.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.coxassginment.data.local.entity.GitUsers


@Dao
interface GitUsersDao   {

    @Query("SELECT * FROM GitUsers")
      fun select(): LiveData<List<GitUsers>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insert(obj: GitUsers)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertAll(obj: List<GitUsers>)
    @Query("SELECT * FROM GitUsers WHERE id = :id")
     fun findById(id: Long): GitUsers

    @Delete
     fun delete(obj: GitUsers)



}