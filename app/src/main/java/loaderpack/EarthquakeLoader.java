package loaderpack;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.example.alexandru.earth_q_app.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Earthquake;

/**
 * Created by Alexandru on 4/20/2017.
 */

public class EarthquakeLoader extends AsyncTaskLoader<List<Earthquake>> {


    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    private String myUrl = "";
    private String myMinMag = "";

    public EarthquakeLoader(Context context, String url) {

        super(context);
        myUrl = url;

    }


    @Override
    protected void onStartLoading() {
        forceLoad();
    }


    @Override
    public List<Earthquake> loadInBackground() {


        URL url = createUrl(myUrl);

        // Perform HTTP request to the URL and receive a JSON response back
        String jsonResponse = "";


        try {
            jsonResponse = getInfo(url);
        } catch (IOException e) {
            e.printStackTrace();

        }


        try {
            List<Earthquake> listInformation = new ArrayList<>();
            extractInfoFromJSON(jsonResponse, listInformation);

            Log.v(LOG_TAG, "Here");
            return listInformation;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }


    private void extractInfoFromJSON(String jsonResponseEarthquake, List<Earthquake> listInformation) throws JSONException {

        if (TextUtils.isEmpty(jsonResponseEarthquake)) {
            return;
        }

        try {
            JSONObject objectRoot = new JSONObject(jsonResponseEarthquake);
            JSONArray earthQuakeArray = objectRoot.getJSONArray("features");

            JSONObject temp;
            int size = earthQuakeArray.length();

            Earthquake tempEarthquake;
            for (int i = 0; i < size; i++) {
                temp = earthQuakeArray.getJSONObject(i).getJSONObject("properties");
                tempEarthquake = new Earthquake();
                tempEarthquake.setMagnitude(temp.getDouble("mag"));
                tempEarthquake.setLocation(temp.getString("place"));
                tempEarthquake.setDateOfEarthquake(new Date(temp.getLong("time")));
                tempEarthquake.setUrl(temp.getString("url"));
                listInformation.add(tempEarthquake);
            }
        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e(LOG_TAG, "Problem parsing the earthquake JSON results", e);
        }


    }


    private String getInfo(URL url) throws IOException {
        String jsonResponse = "";

        if (url == null) {
            return jsonResponse;
        }

        jsonResponse = doInBackground(new URL[]{url});

        return jsonResponse;

    }


    private String doInBackground(URL... params) {
        String jsonResponse = "";

        if (params == null || params[0] == null) {
            return jsonResponse;
        }


        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;

        try {
            urlConnection = (HttpURLConnection) params[0].openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setInstanceFollowRedirects(true);
            urlConnection.connect();


            //if it was successful response code 200
            // the read the input steam
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "STATUS CODE: " + urlConnection.getResponseCode());
            }


        } catch (IOException e) {

            Log.e(LOG_TAG, "Problem getting the earthquake JSON results", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                // function must handle java.io.IOException here
                try {
                    inputStream.close();
                } catch (IOException e) {
                    Log.e(LOG_TAG, "STATUS CODE: " + e);
                }
            }
        }

        return jsonResponse;
    }


    private String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    private URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
            Log.e("URL to get from", url.toString());
        } catch (MalformedURLException exception) {
            Log.e(LOG_TAG, "Error with creating URL", exception);
            return null;
        }
        return url;
    }
}
