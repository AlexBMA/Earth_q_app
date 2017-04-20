package com.example.alexandru.earth_q_app;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import adapter.EarthquakeAdapter;
import loaderpack.EarthquakeLoader;
import model.Earthquake;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Earthquake>> {

    ArrayAdapter<Earthquake> adapter1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //list of earthquakes
        //List<Earthquake> listEarthquake = extractEarthquakes();



        // Find a reference to the {@link ListView} in the layout
        ListView earthquakeListView = (ListView) findViewById(R.id.list);

        // Create a new {@link ArrayAdapter} of earthquakes
        adapter1 = new EarthquakeAdapter(this, new ArrayList<Earthquake>());

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        earthquakeListView.setAdapter(adapter1);

        getLoaderManager().initLoader(0, null, this);

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

    @Override
    public Loader<List<Earthquake>> onCreateLoader(int id, Bundle args) {

        return new EarthquakeLoader(MainActivity.this);
    }

    @Override
    public void onLoadFinished(Loader<List<Earthquake>> loader, List<Earthquake> data) {
        adapter1.addAll(data);
    }

    @Override
    public void onLoaderReset(Loader<List<Earthquake>> loader) {
        adapter1.clear();
    }
}
