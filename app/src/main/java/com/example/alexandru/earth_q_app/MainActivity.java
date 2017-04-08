package com.example.alexandru.earth_q_app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.text.SimpleDateFormat;
import java.util.List;

import adapter.EarthquakeAdapter;
import json.QueryUtils;
import model.Earthquake;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //list of earthquakes
        List<Earthquake> listEarthquake = QueryUtils.extractEarthquakes();


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");







        // Find a reference to the {@link ListView} in the layout
        ListView earthquakeListView = (ListView) findViewById(R.id.list);

        // Create a new {@link ArrayAdapter} of earthquakes
        ArrayAdapter<Earthquake> adapter1 = new EarthquakeAdapter(this, listEarthquake);

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        earthquakeListView.setAdapter(adapter1);
    }
}
