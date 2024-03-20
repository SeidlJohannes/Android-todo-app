package com.example.presidentlist;


import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.disklrucache.DiskLruCache;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ItemMyApplication extends Application {

    private static List<Item> presidentList = new ArrayList<Item>();
    private static int nextId = 2;
    String itemName, itemValue, itemURL, itemDate;
    public ItemMyApplication() {
        fillPresidentList();
    }


    //Initiate SharedPreferences
    //SharedPreferences mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
    //SharedPreferences itemCount = getApplicationContext().getSharedPreferences("itemcount", 0);
    //SharedPreferences.Editor mEditor = itemCount.edit();


    //When adding an item notice nextId has to be changed
    private void fillPresidentList() {
        /*for(int id = 0; id < 2; id++){
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
            presidentList.add(id, loadedItem);
            //Log.d("FIND_MISTAKE", "i managed to load number " + id);
        }*/
        //Item item0 = new Item(0,"PushUps",0,"https://blog.nasm.org/hubfs/power-pushups.jpg", "Tag des jüngsten Gerichts");
        //Item item1 = new Item(1,"Kniebeugen",0,"https://www.fitundattraktiv.de/wp-content/uploads/2018/01/kniebeugen_muskelgruppen-kniebeugen_ohne_gewicht.jpg", "Tag des jüngsten Gerichts");
        //Item item2 = new Item(2,"Von Rico getötete Motten",1,"https://static.wikia.nocookie.net/penguinsofmadagascar/images/e/e4/Rico01.png/revision/latest/scale-to-width/360?cb=20150111190358", "Tag des jüngsten Gerichts");
        //presidentList.addAll(Arrays.asList(item0, item1));
    }



    public static List<Item> getPresidentList() {
        return presidentList;
    }

    public static void setPresidentList(List<Item> presidentList) {
        ItemMyApplication.presidentList = presidentList;
    }

    public static int getNextId() {
        return nextId;
    }

    public static void setNextId(int nextId) {
        ItemMyApplication.nextId = nextId;
    }
}
