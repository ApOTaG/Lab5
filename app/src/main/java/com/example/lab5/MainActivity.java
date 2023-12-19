package com.example.lab5;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private EditText searchEditText;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        searchEditText = findViewById(R.id.searchEditText);

        DataLoader dataLoader = new DataLoader(this, listView);
        dataLoader.execute("https://www.floatrates.com/daily/usd.xml");

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = (String) parent.getItemAtPosition(position);
                Toast.makeText(MainActivity.this, "Selected: " + selectedItem, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void filterList(View view) {
        String searchText = searchEditText.getText().toString().toUpperCase();

        if (adapter != null) {
            adapter.getFilter().filter(searchText);
        }
    }

    // Set the adapter in onPostExecute to ensure it is initialized
    public void setAdapter(ArrayList<String> data) {
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
        listView.setAdapter(adapter);
    }
}
