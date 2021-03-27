package sw.iot.droid.coordinatorlayout;

import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 */
public class MainActivity3 extends AppCompatActivity {
    private List<TabData> mTabData = new ArrayList<>();
    private List<View> mTabView = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initTabData();
        Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        ViewPager viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(new TempAdapter(this.getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);
        for (int i = 0; i < viewPager.getAdapter().getCount(); i++) {
            final TabLayout.Tab tab = tabLayout.getTabAt(i);
            tab.setCustomView(mTabView.get(i));
        }
    }

    private void initTabData() {
        mTabData.add(new TabData(R.mipmap.ic_launcher, R.string.title0));
        mTabData.add(new TabData(R.mipmap.ic_launcher, R.string.title1));
        mTabData.add(new TabData(R.mipmap.ic_launcher, R.string.title2));
        mTabData.add(new TabData(R.mipmap.ic_launcher, R.string.title3));
        for (int i = 0; i < mTabData.size(); i++) {
            mTabView.add(getTabView(i));
        }
    }

    private View getTabView(int i) {
        View view = getLayoutInflater().inflate(R.layout.tab, null);
        TextView textView = view.findViewById(R.id.tab_title);
        textView.setText(mTabData.get(i).stringId);
        ImageView imageView = view.findViewById(R.id.tab_icon);
        imageView.setImageResource(mTabData.get(i).drawableId);
        return view;
    }

    private final static class TabData {
        @DrawableRes
        int drawableId;
        @StringRes
        int stringId;

        TabData(int drawableId, int stringId) {
            this.drawableId = drawableId;
            this.stringId = stringId;
        }
    }


    private class TempAdapter extends FragmentStatePagerAdapter {
        final int newFragment = 1;

        TempAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            if (i == newFragment) {
                return NewFragment.newInstance();
            }
            return TempFragment.newInstance(i);
        }

        @Override
        public int getCount() {
            return 4;
        }
    }

    @Override
    public boolean onCreatePanelMenu(int featureId, Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem menuItem = menu.findItem(R.id.searchView);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setMaxWidth(2000);
        return true;
    }
}
