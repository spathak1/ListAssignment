package com.test.listapp.ui;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.test.listapp.R;
import com.test.listapp.data.DataManager;
import com.test.listapp.data.Item;
import com.test.listapp.utils.ImageLoader;

/**
 * Created by SPathak on 13-11-2015.
 */
public class DetailsActivity extends Activity {
    private TextView titleText, descText;
    private ImageView urlImage;
    private Item currentItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        currentItem = DataManager.getInstance().getClickedItem();
        int loader = R.mipmap.loader;

        titleText = (TextView) findViewById(R.id.tvTitle);
        descText = (TextView) findViewById(R.id.tvDescription);
        urlImage = (ImageView) findViewById(R.id.ivImage);

        titleText.setText(currentItem.getTitle());
        descText.setText(currentItem.getDescription());

        ImageLoader imgLoader = new ImageLoader(this);
        imgLoader.DisplayImage(currentItem.getImage(), loader, urlImage);
    }
}
