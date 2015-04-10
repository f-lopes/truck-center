package truckcenter.com.truckcenter.service;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import truckcenter.com.truckcenter.model.PositionNotification;

/**
 * Created by martin on 10/04/15.
 */
public class HelpTask extends AsyncTask<String, Void, HttpResponse> {

    Context mContext;

    public HelpTask(Context context) {
        super();
        mContext = context;
    }

    @Override
    protected HttpResponse doInBackground(String... params) {
        String urlString = "http://truck-center.herokuapp.com/rest/alerts";
        try
        {
            String driverId = params[0];
            HttpClient client = new DefaultHttpClient();
            HttpPost post = new HttpPost(urlString);

            String base64EncodedCredentials = "Basic " + Base64.encodeToString(
                    ("admin:admin").getBytes(),
                    Base64.NO_WRAP);

            post.setHeader("Authorization", base64EncodedCredentials);

            String json = "";

            JSONObject jsonObject = new JSONObject();
            jsonObject.accumulate("driverId", driverId);
            jsonObject.accumulate("date", String.valueOf(System.currentTimeMillis()));

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
        if (response != null && (response.getStatusLine().getStatusCode() == 200 || response.getStatusLine().getStatusCode() == 201)) {
            Toast.makeText(mContext, "Un technicien va vous rappeler.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(mContext, "Got a bug", Toast.LENGTH_SHORT).show();
        }
    }
}
