package com.example.hmma;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.room.Room;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    static AppDatabase db;
    static int counter=0;
    static List<Item> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // data base initialization
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class,"item-database").build();
        new itemGetAsyncTask().execute();
    }




    public void onclick_addItem(View view){
        // get the input text from the GUI
        EditText itemName = (EditText)findViewById(R.id.editTextTextPersonName2);
        String itemNameStr = itemName.getText().toString();
        EditText date = (EditText)findViewById(R.id.editTextDate);
        String dateStr = date.getText().toString();
        EditText price = (EditText)findViewById(R.id.editTextNumber);
        String priceStr = price.getText().toString();
        EditText category = (EditText)findViewById(R.id.editTextTextPersonName);
        String categoryStr = category.getText().toString();

        //check if the item and the price has a value
        if (!itemNameStr.contentEquals("Item") && !priceStr.contentEquals("") && !priceStr.contentEquals("0")) {
            // increment the data base UNIQUE ID
            counter++;

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();
                if (dateStr.contentEquals("")) {
                    dateStr = dtf.format(now);
                }
            }

            // create new item and add it to database
            Item item = new Item();
            item.iid = counter;
            item.ItemName = itemNameStr;
            item.currentDate = dateStr;
            item.price = priceStr;
            item.category = categoryStr;

            // access DAO for database through asyncTask
            new itemSaveAsyncTask(item).execute();

        }
        else {
            System.out.println("fields are not filled");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: --------");
        if(db !=null){
            if(db.isOpen()) {
                db.close();
            }
            db=null;
        }
    }

    private static class itemSaveAsyncTask extends AsyncTask<Void, Void, Integer> {

        //Prevent leak
        private Item item;

        public itemSaveAsyncTask(Item item_loc) {
            item = item_loc;
        }

        @Override
        protected Integer doInBackground(Void... params) {
            UserDao userDao = db.userDao();
            userDao.insertAll(item);
            System.out.println("items added");
            return null;
        }
    }

    private static class itemGetAsyncTask extends AsyncTask<Void, Void, Integer> {

        public itemGetAsyncTask() {
        }

        @Override
        protected Integer doInBackground(Void... params) {
            UserDao userDao = db.userDao();
            items = userDao.getAll();
            if (items.size() > 0) {
                counter = items.get(items.size() - 1).iid;
            }
            return null;
        }
    }
}

