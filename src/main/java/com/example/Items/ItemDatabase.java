package com.example.Items;

import org.controlsfx.control.PropertySheet;

import java.util.ArrayList;

public class ItemDatabase {

    public static ArrayList<Items> itemData = new ArrayList<>();

    public static void addItems(){
        ItemDatabase.itemData.add(new Items("Zlato", 778.8f));
        ItemDatabase.itemData.add(new Items("Diamant", 1100.8f));
        ItemDatabase.itemData.add(new Items("Strieborna podkolienka", 11111.8f));
    }

}
