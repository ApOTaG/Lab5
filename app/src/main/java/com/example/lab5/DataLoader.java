package com.example.lab5;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class DataLoader extends AsyncTask<String, Void, String> {

    private Context context;
    private ListView listView;

    public DataLoader(Context context, ListView listView) {
        this.context = context;
        this.listView = listView;
    }

    @Override
    protected String doInBackground(String... urls) {
        String joke = "";

        try {
            URL url = new URL(urls[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0");

            InputStream stream = connection.getInputStream();

            Scanner scanner = new Scanner(stream);
            StringBuilder result = new StringBuilder();

            while (scanner.hasNextLine()) {
                result.append(scanner.nextLine());
            }

            joke = result.toString();

            scanner.close();
            stream.close();
            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return joke;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        try {
            JSONObject jsonObject = new JSONObject(result);
            String joke = jsonObject.getString("joke");

            ArrayList<String> jokeList = new ArrayList<>();
            jokeList.add(joke);

            ((MainActivity) context).setAdapter(jokeList);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
