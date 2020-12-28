package com.example.hmma;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.room.Room;

import org.json.JSONException;

import java.util.List;

public class ItemsTableActivity extends Activity {

    static int head_flag = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itemstable);

        // add the finish to close this activity and go back to main activity
        Button backButton = findViewById(R.id.backbutton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        //show the table content from items (from the database)
        TableLayout tv = findViewById(R.id.tableLayout);
        tv.removeAllViewsInLayout();

        List<Item> items = MainActivity.items;

        for (int i = head_flag; i < (items.size() - 1); i++) {

            // this is a table row
            TableRow tr = new TableRow(ItemsTableActivity.this);
            tr.setLayoutParams(new TableLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));

            if (head_flag == -1) {

                TextView h1 = new TextView(ItemsTableActivity.this);
                h1.setText("Item Name \t\t");
                h1.setTextSize(15);
                tr.addView(h1);

                TextView h2 = new TextView(ItemsTableActivity.this);
                h2.setText("date       \t");
                h2.setTextSize(15);
                tr.addView(h2);

                TextView h3 = new TextView(ItemsTableActivity.this);
                h3.setText("price \t");
                h3.setTextSize(15);
                tr.addView(h3);

                TextView h4 = new TextView(ItemsTableActivity.this);
                h4.setText("category \t");
                h4.setTextSize(15);
                tr.addView(h4);
                tv.addView(tr);

                final View vline = new View(ItemsTableActivity.this);
                vline.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, 2));
                tv.addView(vline);
                head_flag = 0;
            } else {

                TextView h5 = new TextView(ItemsTableActivity.this);
                h5.setText(items.get(i).ItemName + "\t\t");
                h5.setTextSize(15);
                tr.addView(h5);

                TextView h6 = new TextView(ItemsTableActivity.this);
                h6.setText(items.get(i).currentDate + "\t");
                h6.setTextSize(15);
                tr.addView(h6);

                TextView h7 = new TextView(ItemsTableActivity.this);
                h7.setText(items.get(i).price + "\t");
                h7.setTextSize(15);
                tr.addView(h7);

                TextView h8 = new TextView(ItemsTableActivity.this);
                h8.setText(items.get(i).category + "\t");
                h8.setTextSize(15);
                tr.addView(h8);
                tv.addView(tr);

                final View vline = new View(ItemsTableActivity.this);
                vline.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, 2));
                tv.addView(vline);
            }
        }
    }
}