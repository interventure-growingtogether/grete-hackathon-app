package rs.iv.grete.activity;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import rs.iv.grete.R;
import rs.iv.grete.model.Alert;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_slide);
        alertList = new ArrayList<>();
        alertList.add(createMockAlert());
        alertList.add(createMockAlert());
        alertList.add(createMockAlert());
        alertList.add(createMockAlert());
        alertList.add(createMockAlert());
        alertList.add(createMockAlert());
        alertList.add(createMockAlert());
        alertList.add(createMockAlert());

        mPager = findViewById(R.id.pager);
        pagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager(), alertList);
        mPager.setAdapter(pagerAdapter);

    }

    private Alert createMockAlert() {
        Alert alert =  new Alert();
        alert.setId(UUID.randomUUID().toString());
        alert.setTitle("Alert! ID: " + alert.getId());
        alert.setDescription("some random stuff");
        alert.setPriority(1);
        alert.setTag("Java");
        return alert;
    }

    @Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
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