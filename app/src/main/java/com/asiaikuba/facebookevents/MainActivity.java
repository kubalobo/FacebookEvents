package com.asiaikuba.facebookevents;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.facebook.FacebookSdk.getApplicationContext;

public class MainActivity extends AppCompatActivity implements FragmentLogin.OnLoginFragmentInteractionListener {

    public static final String DELETED_EVENTS = "deleted_events";
    List<Event> events;
    Set<String> deletedEvents = new HashSet<String>();

    public List<Event> getEvents() {
        return events;
    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            if (events == null) {
                Toast.makeText(getApplicationContext(), "Najpierw kliknij przycisk!", Toast.LENGTH_SHORT).show();
                return false;
            }
            switch (item.getItemId()) {
                case R.id.navigation_home:

                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragmentContainer, new FragmentLogin());
                    transaction.addToBackStack(null);
                    transaction.commit();

                    return true;
                case R.id.navigation_dashboard:

                    transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragmentContainer, new FragmentMap());
                    transaction.addToBackStack(null);
                    transaction.commit();

                    return true;

                case R.id.navigation_notifications:
                    transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragmentContainer, new FragmentEventsList());
                    transaction.addToBackStack(null);
                    transaction.commit();

                    return true;

            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentContainer, new FragmentLogin());
        transaction.commit();

//        mTextMessage = findViewById(R.id.message);
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        String deletedEventsString = sharedPref.getString(DELETED_EVENTS, "aaa");

        Log.wtf("CCC", deletedEventsString);

        String[] ids = deletedEventsString.split("\\s+");

        deletedEvents.addAll(Arrays.asList(ids));

        printKeyHash();
    }

    private void printKeyHash() {
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.asiaikuba.facebookevents",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.wtf("Problem", "NameNotFoundException");

        } catch (NoSuchAlgorithmException e) {
            Log.wtf("Problem", "NoSuchAlgorithmException");

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragmentContainer);
        fragment.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onLoginFragmentInteraction() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        String deletedEventsString = "";

        for (String eventId : deletedEvents) {
            deletedEventsString += eventId + " ";
        }

        Log.wtf("BBB", deletedEventsString);

        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(DELETED_EVENTS, deletedEventsString);
        editor.commit();

    }
}
