package cn.edu.gdpt.wechatnews;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import java.util.List;

public class SettingActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private GridView g11;
    private GridView g22;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        initView();
        toolbar.setNavigationIcon(R.mipmap.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        List<String> list1 = Data.list1;
        List<String> list2 = Data.list2;
        iaDapter11 = new IADapter(list1,list2);
        iaDapter222 = new IADapter(list2,list1);
        g11.setAdapter(iaDapter11);
        g22.setAdapter(iaDapter222);

    }
    IADapter iaDapter11,iaDapter222;
    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        g11 = (GridView) findViewById(R.id.g11);
        g22 = (GridView) findViewById(R.id.g22);
    }

    class  IADapter  extends BaseAdapter{
        List<String> list;
        List<String> duifang;

        public IADapter(List<String> list, List<String> duifang) {
            this.list = list;
            this.duifang = duifang;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            convertView = LayoutInflater.from(SettingActivity.this).inflate(R.layout.itemsetting,parent,false);
            TextView textView = convertView.findViewById(R.id.ttt);
            textView.setText(list.get(position)+"");

            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    duifang.add(list.get(position));
                    list.remove(position);

                    iaDapter11.notifyDataSetChanged();
                    iaDapter222.notifyDataSetChanged();
                }
            });
            return convertView;
        }
    }


}
