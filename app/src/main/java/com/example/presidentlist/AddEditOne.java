package com.example.presidentlist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AddEditOne extends AppCompatActivity {

    Button btn_ok, btn_cancel;
    List<Item> presidentList;
    EditText et_itemValue, et_itemName, et_itemImageURL;
    TextView tv_itemId, tv_creationDate;
    int id, nextId, itemcount;
    String value_item, nameGiven, valueGiven, urlGiven;

    ItemMyApplication myApplication = (ItemMyApplication) this.getApplication();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_one);

        presidentList = myApplication.getPresidentList();

        //All views for item list
        btn_cancel = findViewById(R.id.btn_cancel);
        btn_ok = findViewById(R.id.btn_ok);
        et_itemValue = findViewById(R.id.et_dateElection);
        et_itemName = findViewById(R.id.et_presidentName);
        et_itemImageURL = findViewById(R.id.et_pictureURL);
        tv_itemId = findViewById(R.id.tv_presidentIdNumber);
        tv_creationDate = findViewById(R.id.tv_creationDate);

        //Initiate SharedPreferences
        SharedPreferences mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = mPreferences.edit();

        Intent intent = getIntent();
        id = intent.getIntExtra("id", -1);
        Item item = null;

        //Alle Werte setzen für das geöffnete item
        if(id >= 0){
            //edit the president
            for (Item p: presidentList){
                if (p.getId() == id){
                    item = p;
                }
            }
            et_itemName.setHint(item.getName());
            //Writes current value as hint in the field (commented it out cause it must be deleted before you can put in a new value)
            //et_itemValue.setText(String.valueOf(item.getValue()));
            et_itemImageURL.setHint(item.getImageURL());
            tv_itemId.setText(String.valueOf(id));
            tv_creationDate.setText(item.getCreationDate());
            //itemLogList.set(1, item.getItemLogList().set(1,item.getItemLogList().size()));
        }
        else{
            //create new president
            itemcount = mPreferences.getInt("ITEMADDED", 0);
            nextId = itemcount;
            tv_itemId.setText(String.valueOf(nextId));
        }

        value_item = mPreferences.getString("VALUE_" + id, "0");
        //Writes current value as hint in the field
        if(id >= 0){
            et_itemValue.setHint(value_item);
        }
        else{
            et_itemValue.setHint("0");
        }
        //Toast.makeText(AddEditOne.this, "itemvalue = " + value_item, Toast.LENGTH_SHORT).show();


        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddEditOne.this, MainActivity.class);
                startActivity(intent);
            }
        });

        Item finalItem = item;
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //check if NAME was put in by user
                if(et_itemName.getText().toString().length() > 0){
                    nameGiven = et_itemName.getText().toString();
                }
                else{
                    nameGiven = et_itemName.getHint().toString();
                }
                //check if VALUE was put in by user
                if(et_itemValue.getText().toString().length() > 0){
                    valueGiven = et_itemValue.getText().toString();
                }
                else{
                    valueGiven = et_itemValue.getHint().toString();
                }
                //check if PICTURE_URL was put in by user
                if(et_itemImageURL.getText().toString().length() > 0){
                    urlGiven = et_itemImageURL.getText().toString();
                }
                else{
                    urlGiven = et_itemImageURL.getHint().toString();
                }

                //update item
                if(id >= 0){
                    //Toast.makeText(AddEditOne.this, "UPDATE ITEM", Toast.LENGTH_SHORT).show();
                    //update
                    //Toast.makeText(AddEditOne.this, "Name = " + nameGiven + " Value = " + valueGiven + " URL = " + urlGiven, Toast.LENGTH_LONG).show();
                    Item updatePresident = new Item(id, nameGiven, Integer.parseInt(valueGiven), urlGiven, "is set in next line");
                    //save date of current item
                    updatePresident.setCreationDate(finalItem.getCreationDate());
                    presidentList.set(id, updatePresident);

                    //logic to check if each value has been changed
                    int didItemChange = 0;
                    if(et_itemName.getText().toString().equals(finalItem.getName())){
                        didItemChange++;
                    }
                    if(Integer.parseInt(et_itemValue.getHint().toString()) == finalItem.getValue()){
                        didItemChange = didItemChange + 2;
                    }
                    if(et_itemImageURL.getText().toString().equals(finalItem.getImageURL())){
                        didItemChange = didItemChange + 4;
                    }
                    if(didItemChange == 7){
                        Toast.makeText(AddEditOne.this, "Item has not been changed", Toast.LENGTH_SHORT).show();
                    }
                    editor.putString("NAME_" + id, nameGiven);
                    editor.putString("VALUE_" + id, valueGiven);
                    editor.putString("URL_" + id, urlGiven);
                    editor.putString("DATE_" + id, finalItem.getCreationDate());
                }
                //add new item
                else{
                    //Toast.makeText(AddEditOne.this, "NEW ITEM", Toast.LENGTH_SHORT).show();
                    /* Workaround because
                    String itemValue = et_itemValue.getText().toString();
                    if(itemValue.isEmpty()){
                        newItem.setValue(0);
                    }
                    didnt work for some reason
                    Now the item gets created with zero as value and only changes it if there is an input
                     */
                    //check if NAME was put in by user
                    if(et_itemName.getText().toString().length() > 0){
                        nameGiven = et_itemName.getText().toString();
                    }
                    else{
                        nameGiven = "unnamed";
;                   }
                    //check if PICTURE_URL was put in by user
                    if(et_itemImageURL.getText().toString().length() > 0){
                        urlGiven = et_itemImageURL.getText().toString();
                    }
                    else{
                        urlGiven = "https://static.vecteezy.com/ti/gratis-vektor/p1/7126739-fragezeichen-symbol-kostenlos-vektor.jpg";
                    }
                    Item newItem = new Item(nextId, nameGiven, Integer.parseInt(valueGiven), urlGiven, tv_creationDate.toString());
                    //create President object
                    //Item newItem = new Item(nextId, et_itemName.getText().toString(), Integer.parseInt(et_itemValue.getText().toString()), et_itemImageURL.getText().toString());

                    String imageURLtext = et_itemImageURL.getText().toString();
                    if(imageURLtext.isEmpty()){
                        newItem.setImageURL("https://static.vecteezy.com/ti/gratis-vektor/p1/7126739-fragezeichen-symbol-kostenlos-vektor.jpg");
                    }

                    //Set creation Date and Time
                    @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd 'um' HH:mm:ss");
                    String currentDateAndTime = sdf.format(new Date());
                    newItem.setCreationDate(currentDateAndTime);
                    //add the object to the global list of Presidents
                    //Toast.makeText(AddEditOne.this, "CLICKED", Toast.LENGTH_SHORT).show();
                    presidentList.add(newItem);
                    editor.putString("NAME_" + nextId, nameGiven);
                    editor.putString("VALUE_" + nextId, valueGiven);
                    editor.putString("URL_" + nextId, urlGiven);
                    editor.putString("DATE_" + nextId, currentDateAndTime);

                    itemcount = mPreferences.getInt("ITEMADDED", 0);
                    itemcount++;
                    editor.putInt("ITEMADDED", itemcount);

                    nextId++;
                    ItemMyApplication.setNextId(nextId);
                }
                editor.apply();
                //go back to Main activity
                Intent intent = new Intent(AddEditOne.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}