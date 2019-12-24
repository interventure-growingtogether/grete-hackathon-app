package rs.iv.grete.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import rs.iv.grete.R;
public class CreateAlertActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_alert);

        final EditText titleText = findViewById(R.id.title);
        final EditText descriptionText = findViewById(R.id.description);
        final Button sendButton = findViewById(R.id.send);
        final ProgressBar loadingProgressBar = findViewById(R.id.loading);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingProgressBar.setVisibility(View.VISIBLE);

            }
        });

        }

}
