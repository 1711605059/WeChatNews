package cn.edu.gdpt.wechatnews;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ViewPager vp;
    private TabLayout tab;

    List<String> titles = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        getWindow().setStatusBarColor(0xff66ccff);

        // new Gson();
        titles.add("女人");
        titles.add("娱乐");
        titles.add("热点");

        final List<Fragment> fragments = new ArrayList<>();
        for (int i = 0; i < titles.size(); i++) {
            fragments.add(new BlankFragment(titles.get(i)));
        }

        tab.setupWithViewPager(vp);
        vp.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return fragments.get(i);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return titles.get(position);
            }
        });
     /*   new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                        }
                    });
                } catch (Exception e) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this, "网络错误", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        }).start();*/
    }

    private String get(String type) throws IOException {
        OkHttpClient okHttpClient = new OkHttpClient();
        String url = "http://apicloud.mob.com/wx/article/search?key=25c1f67d8be28&cid=" + type;
        Request request = new Request.Builder().get().url(url).build();
        return okHttpClient.newCall(request).execute().body().string();
    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        vp = (ViewPager) findViewById(R.id.vp);
        tab = (TabLayout) findViewById(R.id.tab);
      //  tab.setOnClickListener(this);
    }
}
