package adapter;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.alexandru.earth_q_app.R;

import java.text.DecimalFormat;
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

        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) textViewMagnitude.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(currentEarthQuake.getMagnitude());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);

        //set the text in the Text view
        textViewMagnitude.setText(simpleMagFormat(currentEarthQuake));


        //get the Text view
        TextView textViewDate = (TextView) listItemView.findViewById(R.id.eq_date);
        //set the text in the Text view
        textViewDate.setText(simpleDateFormat(currentEarthQuake));


        // get the text view
        TextView textViewTime = (TextView) listItemView.findViewById(R.id.eq_time);
        //set the text in the Text view
        textViewTime.setText(simpleTimeFormat(currentEarthQuake));


        //get the Text view
        TextView textViewLocation = (TextView) listItemView.findViewById(R.id.eq_location);
        //set the text in the Text view
        textViewLocation.setText(simpleTextFormatLocationPosition(currentEarthQuake));


        //get the Text view
        TextView textViewLocationPlace = (TextView) listItemView.findViewById(R.id.eq_location_place);
        //set the text in  the Text view
        textViewLocationPlace.setText(simpleTextFormatLocationName(currentEarthQuake));


        return listItemView;
    }


    private String simpleMagFormat(Earthquake currentEarthQuake) {
        DecimalFormat formatter = new DecimalFormat("0.0");
        String output = formatter.format(currentEarthQuake.getMagnitude());
        return output;
    }

    private int getMagnitudeColor(double magnitude) {

        int magnitude1Color;

        int magnitudeFloor = (int) Math.floor(magnitude);

        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitude1Color = ContextCompat.getColor(getContext(), R.color.magnitude1);
                break;

            case 2:
                magnitude1Color = ContextCompat.getColor(getContext(), R.color.magnitude2);
                break;

            case 3:
                magnitude1Color = ContextCompat.getColor(getContext(), R.color.magnitude3);
                break;

            case 4:
                magnitude1Color = ContextCompat.getColor(getContext(), R.color.magnitude4);
                break;

            case 5:
                magnitude1Color = ContextCompat.getColor(getContext(), R.color.magnitude5);
                break;

            case 6:
                magnitude1Color = ContextCompat.getColor(getContext(), R.color.magnitude5);
                break;

            case 7:
                magnitude1Color = ContextCompat.getColor(getContext(), R.color.magnitude5);
                break;

            case 8:
                magnitude1Color = ContextCompat.getColor(getContext(), R.color.magnitude5);
                break;

            case 9:
                magnitude1Color = ContextCompat.getColor(getContext(), R.color.magnitude5);
                break;

            default:
                magnitude1Color = ContextCompat.getColor(getContext(), R.color.magnitude10plus);
                break;
        }


        return magnitude1Color;
    }

    private String simpleDateFormat(Earthquake currentEarthQuake) {
        SimpleDateFormat sdf = new SimpleDateFormat("LLL dd, yyyy");
        Date tempDate = currentEarthQuake.getDateOfEarthquake();

        return sdf.format(tempDate);
    }

    private String simpleTimeFormat(Earthquake currentEarthQuake) {
        //format the date
        SimpleDateFormat sdf2 = new SimpleDateFormat("h:mm a");
        Date tempTime = currentEarthQuake.getDateOfEarthquake();

        return sdf2.format(tempTime);
    }

    private String simpleTextFormatLocationName(Earthquake currentEarthQuake) {
        //format the location string
        String location = currentEarthQuake.getLocation();
        String regex = "(of)+";
        String[] splitString = location.split(regex);
        //System.out.print(" && "+splitString.length);


        if (splitString.length > 1)
            return splitString[1];
        else return splitString[0];
    }

    private String simpleTextFormatLocationPosition(Earthquake currentEarthQuake) {
        //format the location string
        String location = currentEarthQuake.getLocation();
        String regex = "(of)+";
        String[] splitString = location.split(regex);

        // Log.v("**", splitString[0] + " of");
        if (splitString.length > 1)
            return splitString[0] + " of";
        else return "Near the";
    }

}
