package cn.edu.gdpt.wechatnews;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.ArrowRefreshHeader;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;


/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class BlankFragment extends Fragment {

    WechatDataBean wechatDataBean;
    private XRecyclerView rec;
    //  private SwipeRefreshLayout sw;

    String base_url = "http://apicloud.mob.com/wx/article/search?key=25c1f67d8be28&cid=";
    String url = "";
    List<WechatDataBean.ResultBean.ListBean> list = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        activity = getActivity();
        View view = inflater.inflate(R.layout.fragment_blank, container, false);


        initView(view);
        rec.setLimitNumberToCallLoadMore(5);
        rec.setLayoutManager(new LinearLayoutManager(activity));
/*        sw.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                sw.setRefreshing(false);
            }
        });*/

        initData();

        rec.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                initData();
            }

            @Override
            public void onLoadMore() {

            }
        });
        rec.setRefreshHeader(new ArrowRefreshHeader(activity));
        // rec.addHeaderView(LayoutInflater.from(activity).inflate(R.layout.item, null, false));
        return view;
    }

    private void initData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    final WechatDataBean wechatDataBean = new Gson().fromJson(get(url), WechatDataBean.class);
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                list = wechatDataBean.getResult().getList();
                                rec.setAdapter(new IAdapter(list));
                                rec.refreshComplete();
                            } catch (Exception e) {
                                e.printStackTrace();
                                Toast.makeText(getActivity(), "XX", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } catch (Exception e) {

                }

            }
        }).start();
    }

    private void initView(View view) {
        rec = view.findViewById(R.id.rec);
        // sw = (SwipeRefreshLayout) view.findViewById(R.id.sw);
    }

    class IAdapter extends RecyclerView.Adapter<IAdapter.ViewHolder> {
        List<WechatDataBean.ResultBean.ListBean> list;

        public IAdapter(List<WechatDataBean.ResultBean.ListBean> list) {
            this.list = list;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new ViewHolder(LayoutInflater.from(activity).inflate(R.layout.item, viewGroup, false));
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
            viewHolder.tiile.setText("" + list.get(i).getTitle());
            viewHolder.message.setText("" + list.get(i).getSubTitle());
            Glide.with(activity).load(list.get(i).getThumbnails()).into(viewHolder.bg);
            final String sourceUrl = list.get(i).getSourceUrl();
            viewHolder.rootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    WebActivity.url = sourceUrl;
                    startActivity(new Intent(activity, WebActivity.class));
                }
            });
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            public View rootView;
            public ImageView bg;
            public TextView tiile;
            public TextView message;

            public ViewHolder(View rootView) {
                super(rootView);
                this.rootView = rootView;
                this.bg = (ImageView) rootView.findViewById(R.id.bg);
                this.tiile = (TextView) rootView.findViewById(R.id.tiile);
                this.message = (TextView) rootView.findViewById(R.id.message);
            }

        }
    }

    FragmentActivity activity;

    private String get(String url) throws IOException {
        OkHttpClient okHttpClient = new OkHttpClient();
        //   String url = "http://apicloud.mob.com/wx/article/search?key=25c1f67d8be28&cid=" + id;
        Request request = new Request.Builder().get().url(url).build();
        return okHttpClient.newCall(request).execute().body().string();
    }

    @SuppressLint("ValidFragment")
    public BlankFragment(String title) {
        switch (title) {
            case "时尚":
                this.url = base_url + "1";
                break;
            case "热点":
                this.url = base_url + "2";
                break;
            case "健康":
                this.url = base_url + "3";
                break;
            case "百科":
                this.url = base_url + "5";
                break;
            case "娱乐":
                this.url = base_url + "7";
                break;
            case "美文":
                this.url = base_url + "8";
                break;
            case "旅行":
                this.url = base_url + "9";
                break;
            case "媒体达人":
                this.url = base_url + "10";
                break;
            case "搞笑":
                this.url = base_url + "11";
                break;
            case "影视音乐":
                this.url = base_url + "12";
                break;
            case "互联网":
                this.url = base_url + "13";
                break;
            case "文史":
                this.url = base_url + "14";
                break;
            case "金融":
                this.url = base_url + "15";
                break;
            case "体育":
                this.url = base_url + "16";
                break;
            case "游戏":
                this.url = base_url + "17";
                break;
            case "两性":
                this.url = base_url + "18";
                break;
            case "社交交友":
                this.url = base_url + "19";
                break;
            case "女人":
                this.url = base_url + "20";
                break;
            case "购物":
                this.url = base_url + "23";
                break;
            case "美女":
                this.url = base_url + "24";
                break;
            case "微信技巧":
                this.url = base_url + "25";
                break;
            case "星座":
                this.url = base_url + "26";
                break;
            case "美食":
                this.url = base_url + "27";
                break;
            case "教育":
                this.url = base_url + "28";
                break;
            case "职场":
                this.url = base_url + "29";
                break;
            case "酷品":
                this.url = base_url + "30";
                break;
            case "母婴":
                this.url = base_url + "31";
                break;
            case "摄影":
                this.url = base_url + "32";
                break;
            case "创投":
                this.url = base_url + "33";
                break;
            case "典藏":
                this.url = base_url + "34";
                break;
            case "家装":
                this.url = base_url + "35";
                break;
            case "汽车":
                this.url = base_url + "36";
                break;
            case "段子":
                this.url = base_url + "37";
                break;
            default:
                this.url = base_url + "37";
                break;

        }

    }

}
