package rs.iv.grete.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import rs.iv.grete.R;
import rs.iv.grete.model.Alert;
import rs.iv.grete.service.Client;

public class CreateAlertActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_alert);

        final EditText titleText = findViewById(R.id.title);
        final EditText descriptionText = findViewById(R.id.description);
        final Button sendButton = findViewById(R.id.send);
        final ProgressBar loadingProgressBar = findViewById(R.id.loading);
        sendButton.setAlpha(0.6f);
        sendButton.setBackgroundColor(Color.RED);
        sendButton.setTextSize(23f);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendButton.setEnabled(false);

                Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
                Alert a = new Alert();
                a.setDescription(descriptionText.getText().toString());
                a.setTitle(titleText.getText().toString());
                a.setCreatorId("3");
                a.setTag("java");
                a.setPriority(0);

                try {
                    Client.getInstance(CreateAlertActivity.super.getApplicationContext()).addToRequestQueue(new JsonObjectRequest(
                            Request.Method.POST, "http://192.168.22.112:8765/alerts", new JSONObject(gson.toJson(a)),
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    Log.d("asd", "msg");
                                    sendButton.setEnabled(true);
                                    loadingProgressBar.setVisibility(View.INVISIBLE);
                                    Toast.makeText(CreateAlertActivity.this.getApplicationContext(), "Successful created alert", Toast.LENGTH_LONG).show();
                                    CreateAlertActivity.this.finish();
                                    try {
                                        FirebaseMessaging.getInstance().subscribeToTopic(response.getString("id"))
                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {

                                                    }
                                                });
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Log.d("tag", "onErrorResponse: asddsa");
                                }
                            }
                    ));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                loadingProgressBar.setVisibility(View.VISIBLE);

            }
        });

        }

}
