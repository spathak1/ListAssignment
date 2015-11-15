package com.test.listapp.ui;

import android.app.Fragment;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.test.listapp.R;
import com.test.listapp.tasks.DownloadTask;


/**
 * Created by SPathak on 13-11-2015.
 */
public class ListFragment extends Fragment {

    private Button download;
    private Context ctx;
    private MainActivity activity;
    private ListView itemsListView;

    public ListFragment(MainActivity ref) {
        this.activity = ref;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list, container, false);
        itemsListView = (ListView) rootView.findViewById(R.id.listView);
        ctx = getActivity().getApplicationContext();
        ConnectivityManager connMgr = (ConnectivityManager)ctx.getSystemService(ctx.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            new DownloadTask(getString(R.string.json_path), getActivity(), itemsListView).execute();
        } else {
            activity.showAlert(getString(R.string.no_nw_message));
        }

        return rootView;
    }

}
