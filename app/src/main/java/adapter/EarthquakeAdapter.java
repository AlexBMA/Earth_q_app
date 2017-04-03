package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.alexandru.earth_q_app.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import model.Earthquake;

/**
 * Created by Alexandru on 4/3/2017.
 */

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    public EarthquakeAdapter(Context context, List<Earthquake> list) {


        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, list);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;

        // Check if the existing view is being reused, otherwise inflate the view
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.earth_quake_item, parent, false);
        }

        // get the current item
        Earthquake currentEarthQuake = getItem(position);

        //get the Text view
        TextView textViewMagnitude = (TextView) listItemView.findViewById(R.id.eq_mag_number);

        //set the text in the Text view
        textViewMagnitude.setText(currentEarthQuake.getMagnitude() + "");

        //get the Text view
        TextView textViewTime = (TextView) listItemView.findViewById(R.id.eq_time);

        //format the date
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date tempDate = currentEarthQuake.getDateOfEarthquake();

        //set the text in the Text view
        textViewTime.setText(sdf.format(tempDate));


        //get the Text view
        TextView textViewLocation = (TextView) listItemView.findViewById(R.id.eq_location);
        //set the text in the Text view
        textViewLocation.setText(currentEarthQuake.getLocation().toString());


        return listItemView;
    }
}
