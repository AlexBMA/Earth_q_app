package com.example.alexandru.earth_q_app;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import adapter.EarthquakeAdapter;
import model.Earthquake;

import static json.QueryUtils.extractEarthquakes;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //list of earthquakes
        List<Earthquake> listEarthquake = extractEarthquakes();


        // Find a reference to the {@link ListView} in the layout
        ListView earthquakeListView = (ListView) findViewById(R.id.list);

        // Create a new {@link ArrayAdapter} of earthquakes
        final ArrayAdapter<Earthquake> adapter1 = new EarthquakeAdapter(this, listEarthquake);

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        earthquakeListView.setAdapter(adapter1);

        earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //get the current selected item
                Earthquake earthquake = adapter1.getItem(position);

                //creates the intent
                Intent intent = new Intent(Intent.ACTION_VIEW);

                //sets the data
                intent.setData(Uri.parse(earthquake.getUrl()));

                //stat the intent
                startActivity(intent);
            }

        });
    }
}
