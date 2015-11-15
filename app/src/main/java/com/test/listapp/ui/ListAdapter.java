package com.test.listapp.ui;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.test.listapp.R;
import com.test.listapp.data.DataManager;
import com.test.listapp.data.Item;

import java.util.ArrayList;

/**
 * Created by SPathak on 13-11-2015.
 */
public class ListAdapter extends BaseAdapter {

    private ArrayList<Item> dataList;
    private Context mContext;

    public ListAdapter(ArrayList<Item> data, Context ctx) {
        this.dataList = data;
        this.mContext = ctx;
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Item getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder = new Holder();
        View rowView;
        rowView = ((LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE))
                .inflate(R.layout.list_item, null);
        holder.title = (TextView) rowView.findViewById(R.id.tvTitle);
        holder.description = (TextView) rowView.findViewById(R.id.tvDescription);
        final Item item = getItem(position);
        holder.title.setText(item.getTitle());
        holder.description.setText(item.getDescription());
        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataManager.getInstance().setClickedItem(item);
                mContext.startActivity(new Intent(mContext, DetailsActivity.class));
            }
        });
        return rowView;
    }

    public class Holder {
        TextView title;
        TextView description;
    }
}
