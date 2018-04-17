package com.wendy.medicalsystem.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wendy.medicalsystem.R;

import java.util.List;

public class MyCaseHistoryAdapter extends BaseAdapter {
    private List<String> list;
    private Context context;
    private LayoutInflater layoutInflater;

    public MyCaseHistoryAdapter(List<String> list,Context context){
        this.list = list;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        MyViewHolder holder;
        if(view == null){
            view = layoutInflater.inflate(R.layout.item_lv,viewGroup,false);
            holder = new MyViewHolder();
            holder.tv_content = view.findViewById(R.id.tv_content);
            view.setTag(holder);
        } else {
            holder = (MyViewHolder) view.getTag();
        }
        String data = list.get(i);
        holder.tv_content.setText(data);
        return view;
    }

    class MyViewHolder{
        TextView tv_content;
    }

}
