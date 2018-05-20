package com.asiaikuba.facebookevents;

import android.util.Log;

import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.places.PlaceManager;
import com.facebook.places.model.PlaceFields;
import com.facebook.places.model.PlaceSearchRequestParams;

/**
 * Created by Kuba on 23.03.2018.
 * Class probably not usable for this project...
 */
class PlaceSearchRequestCallback
        implements PlaceManager.OnRequestReadyCallback, GraphRequest.Callback {

    @Override
    public void onRequestReady(GraphRequest graphRequest) {
        // The place search request is ready to be executed.
        // The request can be customized here if needed.

        // Sets the callback and executes the request.
        graphRequest.setCallback(this);
        graphRequest.executeAsync();
    }

    @Override
    public void onCompleted(GraphResponse response) {
        // This event is invoked when the place search response is received.
        // Parse the places from the response object.
        Log.wtf("aaa", "TAK!");
    }

    @Override
    public void onLocationError(PlaceManager.LocationError error) {
        // Invoked if the Places Graph SDK failed to retrieve
        // the device location.
    }

    private void searchFacebookPlaces() {
        PlaceSearchRequestParams.Builder builder =
                new PlaceSearchRequestParams.Builder();

        builder.setSearchText("Cafe");
        builder.setDistance(1000); // 1,000 m. max distance.
        builder.setLimit(10);
        builder.addField(PlaceFields.NAME);
        builder.addField(PlaceFields.LOCATION);
        builder.addField(PlaceFields.PHONE);

        PlaceSearchRequestCallback callback = new PlaceSearchRequestCallback();

// The SDK will automatically retrieve the device location and invoke
// the OnRequestReadyCallback when the request is ready to be executed.
        PlaceManager.newPlaceSearchRequest(builder.build(), callback);

    }
}