package com.example.hmma;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

@Entity
public class Item {
    @PrimaryKey
    public int iid;

    @ColumnInfo(name = "item_name")
    public String ItemName;

    @ColumnInfo(name = "date")
    public String currentDate;

    @ColumnInfo(name = "price")
    public String price ;

    @ColumnInfo(name = "category")
    public String category;
}
