package com.example.lab5;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
    }

    // This is the correct method signature for the onClick attribute in XML
    public void generateJoke(View view) {
        // Your implementation here
        long timestamp = System.currentTimeMillis();
        String apiUrl = "https://icanhazdadjoke.com/?timestamp=" + timestamp;

        DataLoader dataLoader = new DataLoader(this, listView);
        dataLoader.execute(apiUrl);
    }


    public void setAdapter(ArrayList<String> data) {
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
        listView.setAdapter(adapter);
    }

    public void onJokeItemClick(String joke) {
        Toast.makeText(this, "Clicked Joke: " + joke, Toast.LENGTH_SHORT).show();
    }
}

