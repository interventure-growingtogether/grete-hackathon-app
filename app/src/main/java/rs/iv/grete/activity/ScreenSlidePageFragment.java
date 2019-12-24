package rs.iv.grete.activity;


import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;

import rs.iv.grete.R;
import rs.iv.grete.model.Alert;

public class ScreenSlidePageFragment extends Fragment {
    private Alert alert;
    private Button accept;

    public ScreenSlidePageFragment(Alert alert){
        super();
        this.alert = alert;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_screen_slide_page, container, false);
        TextView alertDesc = rootView.findViewById(R.id.alertDescription);
        alertDesc.setText(alert.getDescription());
        TextView alertTitle = rootView.findViewById(R.id.alertTitle);
        alertTitle.setText(alert.getTitle());
        TextView alertPriority = rootView.findViewById(R.id.alertPriority);
        alertPriority.setText(getPriority(alert.getPriority()));
        TextView alertTag = rootView.findViewById(R.id.alertTag);
        alertTag.setText(alert.getTag());
        ImageView iv = rootView.findViewById(R.id.imageView);
        Glide.with(rootView).load(getPriorityImage(alert.getPriority())).into(iv);
        accept = rootView.findViewById(R.id.accept);
        accept.setBackgroundColor(Color.RED);
        accept.setAlpha(0.6f);
        accept.setTextSize(23f);
        return rootView;
    }

    private int getPriorityImage(Integer priority) {
        switch (priority){
            case 1: return R.drawable.trivial;
            case 2: return R.drawable.minor;
            case 3: return R.drawable.normal;
            case 4: return R.drawable.high;
            case 5: return R.drawable.urgent;
        }
        return R.drawable.normal;
    }

    private String getPriority(Integer priority) {
        switch (priority){
            case 1: return "Trivial";
            case 2: return "Minor";
            case 3: return "Normal";
            case 4: return "High";
            case 5: return "Urgent";
        }
        return "Normal";
    }
}