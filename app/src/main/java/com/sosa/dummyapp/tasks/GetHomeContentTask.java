package com.sosa.dummyapp.tasks;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.sosa.dummyapp.contentresources.HomeResource;
import com.sosa.dummyapp.ui.home.HomeFragment;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetHomeContentTask extends AsyncTask<String, Integer, String> {

    private static final String TAG = "GetHomeContentTask";

    //We need to pass in the HomeFragment so we send it commands from this Task
    public GetHomeContentTask(HomeFragment fragment){
        super();
        this.setContext(fragment);
        Log.i(TAG, "HomeFragment called constructor");
    }

    //This is the reference to the MainActivity. To use, do mainActivity.get()
    private WeakReference<HomeFragment> homeFragmentWeakReference;

    public void setContext(HomeFragment fragment){ homeFragmentWeakReference = new WeakReference<>(fragment);}

    @Override
    protected String doInBackground(String... urls) {
        String content = "";
        HttpURLConnection connection = null;
        boolean connected = false;
        try{
            URL url = new URL(urls[0]);
            Log.i(TAG, url.toString());
            connection = (HttpURLConnection) url.openConnection();
            prepareConnection(connection);
            connection.connect();
            connected = true;

            //status 200 : Ok       status 404 : Not found
            int status = connection.getResponseCode();
            Log.i(TAG, "ResponseCode : " + status);     //Learn about response codes : https://developer.mozilla.org/en-US/docs/Web/HTTP/Status

            content = readResponse(connection);         //Get the HTTP response content
            Log.i(TAG, content);

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if (connected){
                connection.disconnect();
            }
        }

        return content;
    }

    /**
     * Once connection is connected, this reads in the data from the URL connection
     * @param connection Object for the HTTP connection
     * @return String of data from the connection
     * @throws IOException handled by caller
     */
    private String readResponse(HttpURLConnection connection) throws IOException {
        String line;
        StringBuilder sb = new StringBuilder();
        BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        while ((line = rd.readLine()) != null) {
            sb.append(line).append("\n");
        }
        rd.close();
        return sb.toString();
    }

    /**
     * Prepares the connection object to send a GET request to the url
     * @param connection object that will execute the request
     * @throws IOException handled by caller
     */
    private void prepareConnection(HttpURLConnection connection) throws IOException {
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(5000);
        connection.setReadTimeout(5000);
        Log.i(TAG, "Connection prepared...");
    }

    @Override
    protected void onProgressUpdate(Integer... progress) {
        super.onProgressUpdate(progress);
    }

    @Override
    protected void onPostExecute(String result) {
        Log.i(TAG, "onPostExec :: " + result);
        HomeResource homeResource = new Gson().fromJson(result, HomeResource.class);
        if (homeResource == null){
            Log.i(TAG, "onPostExec :: NULL content - ABORTING");        //avoid crash if connection failed
            Toast.makeText(homeFragmentWeakReference.get().getContext(),
                    "Failed to get Home Resource data", Toast.LENGTH_SHORT).show();
            return;
        }
        homeFragmentWeakReference.get().updateHomeViews(homeResource);
    }

}
