package truckcenter.com.truckcenter.service;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import truckcenter.com.truckcenter.model.PositionNotification;

/**
 * Created by martin on 30/03/15.
 */
public class PostPositionTask extends AsyncTask<PositionNotification, Void, HttpResponse> {

    Context mContext;

    public PostPositionTask(Context context) {
        super();
        mContext = context;
    }

    @Override
    protected HttpResponse doInBackground(PositionNotification... params) {
        String urlString = "http://truckcenter.martinleguillou.fr:8081/positionService";
        try
        {
            PositionNotification positionNotification = params[0];
            HttpClient client = new DefaultHttpClient();
            HttpPost post = new HttpPost(urlString);

            String base64EncodedCredentials = "Basic " + Base64.encodeToString(
                    ("android-truck-app:a4&f24E0!").getBytes(),
                    Base64.NO_WRAP);

            post.setHeader("Authorization", base64EncodedCredentials);

            String json = "";

            JSONObject jsonObject = new JSONObject();
            jsonObject.accumulate("driverId", positionNotification.getDriverId());
            jsonObject.accumulate("latitude", String.valueOf(positionNotification.getLatitude()).replace(".", ""));
            jsonObject.accumulate("longitude", String.valueOf(positionNotification.getLongitude()).replace(".", ""));
            jsonObject.accumulate("date", String.valueOf(positionNotification.getDate()));

            json = jsonObject.toString();

            StringEntity se = new StringEntity(json);

            post.setEntity(se);

            post.setHeader("Content-type", "application/json");

            return client.execute(post);
        }
        catch (Exception ex){
            Log.e("Debug", "error: " + ex.getMessage(), ex);
        }
        return null;
    }

    @Override
    protected void onPostExecute(HttpResponse response) {
        if (response != null) {
            Toast.makeText(mContext, String.valueOf(response.getStatusLine().getStatusCode()), Toast.LENGTH_SHORT).show();
        }
    }
}
