package com.asiaikuba.facebookevents;

import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphRequestAsyncTask;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;

import static com.facebook.FacebookSdk.getApplicationContext;

public class FacebookEventsGetter {
    /* make the API call */
    GraphRequestAsyncTask request = new GraphRequest(
            AccessToken.getCurrentAccessToken(),
            "/{user-id}/events",
            null,
            HttpMethod.GET,
            new GraphRequest.Callback() {
                public void onCompleted(GraphResponse response) {
                    Toast.makeText(getApplicationContext(), "Cosik pobrano!", Toast.LENGTH_SHORT).show();
                }
            }
    ).executeAsync();
}
