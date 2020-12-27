package com.example.hmma;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM item")
    List<Item> getAll();

    @Query("SELECT * FROM item WHERE iid IN (:userIds)")
    List<Item> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM item WHERE item_name LIKE (:first)")
    Item findByName(String first);

    @Insert
    void insertAll(Item... items);

    @Delete
    void delete(Item user);
}
