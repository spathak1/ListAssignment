package com.test.listapp.data;

import java.util.ArrayList;

/**
 * Created by SPathak on 13-11-2015.
 */
public class DataManager {
    private static DataManager instance = null;
    private static ArrayList<Item> listItems = null;

    private static Item clickedItem = null;

    private DataManager() {
    }

    public static DataManager getInstance() {
        if (null == instance) {
            instance = new DataManager();
        }
        return instance;
    }

    public static ArrayList<Item> getListItems() {
        return DataManager.listItems;
    }

    public static void setListItems(ArrayList<Item> parsedList) {
        DataManager.listItems = parsedList;
    }

    public static Item getClickedItem() {
        return DataManager.clickedItem;
    }

    public static void setClickedItem(Item clickedItem) {
        DataManager.clickedItem = clickedItem;
    }
}
