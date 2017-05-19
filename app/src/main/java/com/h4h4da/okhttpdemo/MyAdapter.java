package com.h4h4da.okhttpdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by h4h4da on 2017/3/24.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private Context context;
    private List<TestBean.ResultBean.ListBean> lists;

    public MyAdapter(Context context, List<TestBean.ResultBean.ListBean> lists) {
        this.context = context;
        this.lists = lists;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item, null);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Picasso.with(context).load(lists.get(position).getFirstImg()).into(holder.img);
        holder.tv.setText(lists.get(position).getTitle());

    }



    @Override
    public int getItemCount() {
        return lists.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView tv;
        public ImageView img;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.txt);
            img = (ImageView) itemView.findViewById(R.id.img);
        }
    }
}
