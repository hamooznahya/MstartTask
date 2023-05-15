package com.example.mstarttask.data.database

import androidx.room.*
import com.example.mstarttask.data.entity.DateEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface DateDao {

    @Query("SELECT * FROM date")
    fun getAll(): Flow<List<DateEntity>>

    @Update
    fun update(item: DateEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg users: DateEntity)

    @Delete
    fun delete(item: DateEntity)

    @Query("DELETE FROM date")
    fun deleteAll()

    @Query("DELETE FROM date WHERE id IN (:idList)")
    fun deleteById(idList: List<Int>)
}