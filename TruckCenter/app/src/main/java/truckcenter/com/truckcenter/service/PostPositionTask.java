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
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

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
        String urlString = "http://truckcenter.martinleguillou.fr:8080/positionService";
        try
        {
            PositionNotification positionNotification = params[0];
            HttpClient client = new DefaultHttpClient();
            HttpPost post = new HttpPost(urlString);

            String base64EncodedCredentials = "Basic " + Base64.encodeToString(
                    ("android-truck-app:a4&f24E0!").getBytes(),
                    Base64.NO_WRAP);


            post.setHeader("Authorization", base64EncodedCredentials);

            List<NameValuePair> pairs = new ArrayList<NameValuePair>();
            pairs.add(new BasicNameValuePair("truck_id", String.valueOf(positionNotification.getTruck_id())));
            pairs.add(new BasicNameValuePair("latitude", String.valueOf(positionNotification.getLatitude())));
            pairs.add(new BasicNameValuePair("longitude", String.valueOf(positionNotification.getLongitude())));
            pairs.add(new BasicNameValuePair("date", String.valueOf(positionNotification.getDate())));
            post.setEntity(new UrlEncodedFormEntity(pairs));
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
