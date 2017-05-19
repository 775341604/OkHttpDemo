package com.h4h4da.okhttpdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ListView;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    String TAG = "MainActivity";

    private List<TestBean.ResultBean.ListBean> lists = new ArrayList<>();
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recycler = (RecyclerView) findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyAdapter(this, lists);
        recycler.setAdapter(adapter);


        final Request request = new Request.Builder()
                .url("http://v.juhe.cn/weixin/query?key=4275d4bbb3acbcddb65999fcab1736ac").build();
        OkHttpClient client = new OkHttpClient();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Gson gson = new Gson();

                final TestBean tb = gson.fromJson(response.body().string(), TestBean.class);
                Log.d(TAG, "onResponse: " + tb.getResult().getList().size());


                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        lists.clear();
                        lists.addAll(tb.getResult().getList());
                        adapter.notifyDataSetChanged();


                    }
                });


            }
        });


    }

}
