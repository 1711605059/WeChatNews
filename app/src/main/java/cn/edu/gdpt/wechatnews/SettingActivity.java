package cn.edu.gdpt.wechatnews;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
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
        initView();  getWindow().setStatusBarColor(0xffFA7399);
        toolbar.setNavigationIcon(R.mipmap.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        List<String> list1 = Data.list1;
        List<String> list2 = Data.list2;
        iaDapter11 = new IADapter(list1,list2,R.drawable.bgjian,"－");
        iaDapter222 = new IADapter(list2,list1,R.drawable.bgjia,"+");
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
        int bg;
        String text;

        public IADapter(List<String> list, List<String> duifang, int bg, String text) {
            this.list = list;
            this.duifang = duifang;
            this.bg = bg;
            this.text = text;
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
            TextView textViewbg = convertView.findViewById(R.id.bg);
            textView.setText(list.get(position)+"");
            textViewbg.setBackgroundResource(bg);
            textViewbg.setText(""+text);
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   if (list.size()==1){
                       //Toast.makeText(SettingActivity.this, "最少一个啊亲", Toast.LENGTH_SHORT).show();
                        Snackbar.make(SettingActivity.this.getWindow().getDecorView().getRootView(),"最少一个啊亲",1000).show();
                   }else {
                       duifang.add(list.get(position));
                       list.remove(position);

                       iaDapter11.notifyDataSetChanged();
                       iaDapter222.notifyDataSetChanged();
                   }
                }
            });
            return convertView;
        }
    }


}
