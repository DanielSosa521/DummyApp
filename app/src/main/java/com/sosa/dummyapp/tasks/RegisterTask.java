package com.sosa.dummyapp.tasks;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.sosa.dummyapp.RegisterFragment;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * This task takes care of executing a register USER action
 * It sends a request to the server which returns a status reflecting a successful registration or failed registration
 * Reference :  https://stackoverflow.com/questions/49234055/sending-post-request-in-android-studio
 * UPDATED REFERENCE : https://stackoverflow.com/questions/35390928/how-to-send-json-object-to-the-server-from-my-android-app
 */
public class RegisterTask extends AsyncTask<String, String, String> {

    //TODO : Figure this shit out bruh damn

    private static final String TAG = "RegisterTask";
    private WeakReference<RegisterFragment> registerFragmentWeakReference;
    private static String localhost; //emulator host loopback url
    private static final String REGISTERENDPOINT = "/register";

    public RegisterTask(RegisterFragment frag){
        super();
        this.setContext(frag);
        Log.i(TAG, "NOTE : Using localhost 10.0.2.2:5000 for this");
        localhost = "http://10.0.2.2:5000";
    }
    public void setContext(RegisterFragment frag) {registerFragmentWeakReference = new WeakReference<>(frag);}

    @Override
    protected String doInBackground(String... strings) {
        Log.i(TAG, "Executing with params " + Arrays.toString(strings));

        String content = "";
        HttpURLConnection connection = null;
        boolean connected = false;
        String data = "";

        try {
            URL url = new URL("http://ptsv2.com/t/0c7zd-1664227906");
            Log.i(TAG, url.toString());
            connection = (HttpURLConnection) url.openConnection();
//            prepareConnection(connection);
            connection.setConnectTimeout(5000);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);

            String jsonInputString = "{\"username\":\"Daniel\", \"password\":\"dallas\"}";

            Log.i(TAG, "Sending json : " + jsonInputString);
//            try (OutputStream os = connection.getOutputStream()) {
//                byte[] input = jsonInputString.getBytes("utf-8");
//                os.write(input, 0, input.length);
//            }
//
//            try(BufferedReader br = new BufferedReader(
//                    new InputStreamReader(connection.getInputStream(), "utf-8"))) {
//                StringBuilder response = new StringBuilder();
//                String responseLine = null;
//                while ((responseLine = br.readLine()) != null) {
//                    response.append(responseLine.trim());
//                }
//                System.out.println(response.toString());
//            }
//

            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            wr.writeBytes("PostData=" + strings[0]);
            wr.flush();
            wr.close();

            InputStream in = connection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(in);
            int inputStreamData = inputStreamReader.read();
            while (inputStreamData != 1){
                char current = (char) inputStreamData;
                inputStreamData = inputStreamReader.read();
                data += current;
            }
//            connection.connect();
//            connected = true;
//
//            int status = connection.getResponseCode();
//            Log.i(TAG, "ResponseCode : " + status);
//            content = readResponse(connection);
//            Log.i(TAG, content);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(registerFragmentWeakReference.get().getContext(),
                    "Something went wrong with LoginTask", Toast.LENGTH_SHORT).show();
        } finally {
            if (connected)
                connection.disconnect();
        }

        return data;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Log.i(TAG, s);  //this is expecting a response code to be sent from server
    }

    private void prepareConnection(HttpURLConnection connection) throws IOException {
        connection.setDoOutput(true);
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8"); // here you are setting the `Content-Type` for the data you are sending which is `application/json`

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
}
