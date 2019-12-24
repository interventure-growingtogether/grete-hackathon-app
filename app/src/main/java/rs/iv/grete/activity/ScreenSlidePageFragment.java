package rs.iv.grete.activity;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import rs.iv.grete.R;
import rs.iv.grete.model.Alert;

public class ScreenSlidePageFragment extends Fragment {
    private Alert alert;

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
        alertPriority.setText(alert.getPriority().toString());
        TextView alertTag = rootView.findViewById(R.id.alertTag);
        alertTag.setText(alert.getTag());
        return rootView;
    }
}