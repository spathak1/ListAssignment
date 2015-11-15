package com.test.listapp.tasks;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.ListView;

import com.test.listapp.R;
import com.test.listapp.data.DataManager;
import com.test.listapp.ui.ListAdapter;
import com.test.listapp.utils.Logger;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by SPathak on 13-11-2015.
 */
public class DownloadTask extends AsyncTask<Void, Void, Void> {

    private String dataUrl;
    private InputStream inputStream = null;
    private String result = "";
    private StringBuilder responseStrBuilder = null;
    private BufferedReader streamReader = null;
    private String inputStr = null;
    ProgressDialog dialog;
    private Activity mActivity;
    private ListView listView;

    public DownloadTask(String jsonPath, Activity act, ListView list) {
        super();
        Logger.Debug("DownloadTask started");
        this.dataUrl = jsonPath;
        this.mActivity = act;
        this.listView = list;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        dialog = new ProgressDialog(mActivity);
        dialog.setMessage(mActivity.getString(R.string.progress_message));
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    @Override
    protected Void doInBackground(Void... params) {
        try {
            URL url = new URL(dataUrl);
            URLConnection connection = url.openConnection();
            HttpURLConnection httpConnection = (HttpURLConnection) connection;
            httpConnection.setRequestMethod("GET");
            httpConnection.connect();

            if (httpConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                inputStream = httpConnection.getInputStream();

                streamReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                responseStrBuilder = new StringBuilder();
                String line;
                while ((line = streamReader.readLine()) != null) {
                    responseStrBuilder.append(line);
                }
                new ItemParser().parseData(responseStrBuilder);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        Logger.Debug("DownloadTask completed");
        if (dialog != null & dialog.isShowing()) {
            dialog.dismiss();
            listView.setAdapter(new ListAdapter(DataManager.getInstance().getListItems(), mActivity));
        }
    }
}
