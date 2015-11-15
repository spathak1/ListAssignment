package com.test.listapp.tasks;

import com.test.listapp.data.DataManager;
import com.test.listapp.data.Item;
import com.test.listapp.utils.Logger;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by SPathak on 13-11-2015.
 */
public class ItemParser {
    private String TAG_IMAGE = "image";
    private String TAG_DESCRIPTION = "description";
    private String TAG_TITLE = "title";
    private Item itemObj = null;
    private ArrayList<Item> parsedList = null;

    public void parseData(StringBuilder jsonSb) {
        try {
            JSONArray jsonArray = new JSONArray(jsonSb.toString());
            Logger.Debug("ItemParser length : " + jsonArray.length());
            parsedList = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                itemObj = new Item();
                itemObj.setImage(object.getString(TAG_IMAGE));
                itemObj.setDescription(object.getString(TAG_DESCRIPTION));
                itemObj.setTitle(object.getString(TAG_TITLE));
                parsedList.add(itemObj);
            }
            DataManager.getInstance().setListItems(parsedList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
