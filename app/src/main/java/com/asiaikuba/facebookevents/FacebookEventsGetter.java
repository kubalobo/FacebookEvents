package com.asiaikuba.facebookevents;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphRequestAsyncTask;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.Profile;

import org.json.JSONException;
import org.json.JSONObject;

import static com.facebook.FacebookSdk.getApplicationContext;

public class FacebookEventsGetter {
    /* make the API call */
    public void getEvents(final MainActivity activity) {
        String userId = Profile.getCurrentProfile().getId();
        Log.wtf("FB_Response", userId);

        Bundle params = new Bundle();
        params.putString("include_canceled", "true");

        GraphRequestAsyncTask request = new GraphRequest(
                AccessToken.getCurrentAccessToken(),
                "/"+ userId +"/events",
                params,
                HttpMethod.GET,
                new GraphRequest.Callback() {
                    public void onCompleted(GraphResponse response) {
                        Toast.makeText(getApplicationContext(), "Cosik pobrano!", Toast.LENGTH_SHORT).show();
                        Log.wtf("FB_Response", response.toString());

                        JSONObject object = response.getJSONObject();
                        try {
                            String data = object.getString("data");
                            activity.events = JsonParser.parse(data);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
        ).executeAsync();
    }
}
