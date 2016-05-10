package com.example.assignmenttwo.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.assignmenttwo.Classes.PersonAdapter;
import com.example.assignmenttwo.R;

public class ViewActivity extends AppCompatActivity {

    private ListView listView1;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_view);

        listView1 = (ListView) findViewById(R.id.listView1);
        PersonAdapter adp = new PersonAdapter(this, R.layout.activity_view_row, MainActivity.Friends);
        listView1.setAdapter(adp);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_view_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuClear:
                listView1.setAdapter(null);
                MainActivity.Friends.clear();
                MainActivity.FileModified=true;
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
