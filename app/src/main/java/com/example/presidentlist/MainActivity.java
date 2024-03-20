package com.example.presidentlist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    
    Button btn_addItem;
    Menu menu;

    ItemMyApplication myApplication = (ItemMyApplication) this.getApplication();

    List<Item> presidentList;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    String itemName, itemValue, itemURL, itemDate;
    Integer initialItems;
    Boolean itemsAdded = false;

    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        presidentList = myApplication.getPresidentList();

        mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mPreferences.edit();

        mEditor.putInt("ITEMADDED", 0);
        initialItems = mPreferences.getInt("ITEMADDED", 0);
        Log.d("FIND_MISTAKE", "Items " + initialItems);
        //This is basically just to reset items
        //itemcount = 2;

        //Creating a String with the key "VALUE_PUSHUPS"
        //Create loop here to initiate as many SharedPreferences as items are there with presidentList.size()

        //Add extra items (to the first 2) if they were added before
        if(!itemsAdded){
            //Log.d("ITEMS EXTRA", "initialItems = " + initialItems);
            for(int id = 0; id < initialItems; id++){
                //Log.d("FIND_MISTAKE", "ID " + id + " //Name " + itemName + " //Value " + itemValue + " //URL " + itemURL + " //Date " + itemDate);
                Item loadedItem = new Item(id,"no name",0,"no url", "no date");
                presidentList.add(id, loadedItem);
                Log.d("FIND_MISTAKE", "ICH ÄNDERE DIE LISTENGRÖßE ");
            }
            itemsAdded = true;
            //Log.d("LIST_SIZE", "Listsize = " + presidentList.size());
        }

        for(int id = 0; id < presidentList.size(); id++){
            Log.d("LIST_SIZE", "Listsize = " + presidentList.size());
            //Put and get NAME from item
            mEditor.putString("NAME_" + id, "0");
            itemName = mPreferences.getString("NAME_" + id, "no name");

            //Put and get VALUE from item
            mEditor.putString("VALUE_" + id, "0");
            itemValue = mPreferences.getString("VALUE_" + id, "no value");

            //Put and get URL from item
            mEditor.putString("URL_" + id, "0");
            itemURL = mPreferences.getString("URL_" + id, "https://static.vecteezy.com/ti/gratis-vektor/p1/7126739-fragezeichen-symbol-kostenlos-vektor.jpg");

            //Put and get DATE from item
            mEditor.putString("DATE_" + id, "0");
            itemDate = mPreferences.getString("DATE_" + id, "no date");

            //Log.d("FIND_MISTAKE", "ID " + id + " //Name " + itemName + " //Value " + itemValue + " //URL " + itemURL + " //Date " + itemDate);
            Item loadedItem = new Item(id,itemName,Integer.parseInt(itemValue),itemURL, itemDate);
            presidentList.set(id, loadedItem);
            //Log.d("FIND_MISTAKE", "i managed to load number " + id);
        }

        Log.d("Presidents APP", "onCreate: " + presidentList);
        //Toast.makeText(this, "Presidents count = " + presidentList.size(), Toast.LENGTH_SHORT).show();

        btn_addItem = findViewById(R.id.btn_addItem);

        btn_addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddEditOne.class);
                startActivity(intent);
            }
        });

        //Recyclerview for item list
        recyclerView = findViewById(R.id.lv_presidentList);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new RecyclerViewAdapter(presidentList, MainActivity.this);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.sort_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.menu_aToz) {
            //sort a to z
            Collections.sort(presidentList, Item.PresidentNameAZComparator);
            Toast.makeText(MainActivity.this, "Sort A to Z", Toast.LENGTH_SHORT).show();
            mAdapter.notifyDataSetChanged();
            return true;
        }
        if (item.getItemId() == R.id.menu_zToa) {
            //sort z to a
            Collections.sort(presidentList, Item.PresidentNameZAComparator);
            Toast.makeText(MainActivity.this, "Sort Z to A", Toast.LENGTH_SHORT).show();
            mAdapter.notifyDataSetChanged();
            return true;
        }
        if (item.getItemId() == R.id.menu_dateAscending) {
            //sort date ascending
            Collections.sort(presidentList, Item.PresidentDateAscendingComparator);
            Toast.makeText(MainActivity.this, "Sort date ascending", Toast.LENGTH_SHORT).show();
            mAdapter.notifyDataSetChanged();
            return true;
        }
        if (item.getItemId() == R.id.menu_dateDescending) {
            //sort date descending
            Collections.sort(presidentList, Item.PresidentDateDescendingComparator);
            Toast.makeText(MainActivity.this, "Sort date descending", Toast.LENGTH_SHORT).show();
            mAdapter.notifyDataSetChanged();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}