package rs.iv.grete.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import rs.iv.grete.R;
import rs.iv.grete.model.Alert;
import rs.iv.grete.service.Client;

public class AlertSwipeActivity extends FragmentActivity {
    /**
     * The number of pages (wizard steps) to show in this demo.
     */
    private List<Alert> alertList;

    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private ViewPager mPager;

    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private PagerAdapter pagerAdapter;

    private Gson parser;

    @Override
    protected void onStop() {
        super.onStop();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mMessageReceiver);
    }

    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Alert a = new Alert(intent.getStringExtra("id"),intent.getStringExtra("id"),intent.getStringExtra("id"),intent.getStringExtra("id"),intent.getStringExtra("id"),intent.getIntExtra("id", 0));
            alertList.add(a);
            pagerAdapter.notifyDataSetChanged();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_slide);
        alertList = new ArrayList<>();
        LocalBroadcastManager.getInstance(this).registerReceiver((mMessageReceiver),
                new IntentFilter("MyData")
        );
        parser = new GsonBuilder().create();


        Client.getInstance(getApplicationContext()).addToRequestQueue(new JsonArrayRequest(Request.Method.GET, "http://192.168.22.112:8765/alerts?is_open=true", null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Type alertListType = new TypeToken<ArrayList<Alert>>(){}.getType();
                List<Alert> newAlertList = parser.fromJson(response.toString(), alertListType);
                alertList.addAll(newAlertList);
                pagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager(), alertList);
                mPager.setAdapter(pagerAdapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO: Handle error
                Log.d("my activity",error.getMessage());
            }
        }));

        mPager = findViewById(R.id.pager);
        pagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager(), alertList);
        mPager.setAdapter(pagerAdapter);

    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {

        List<Alert> alerts;
        List<ScreenSlidePageFragment> fragments;
        public ScreenSlidePagerAdapter(FragmentManager fm, List<Alert> alertList) {
            super(fm);
            alerts = alertList;
            fragments = new ArrayList<>();
            for (Alert a: alerts) {
                fragments.add(new ScreenSlidePageFragment(a));
            }
        }
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }
}