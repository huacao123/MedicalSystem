package com.wendy.medicalsystem.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.wendy.medicalsystem.R;
import com.wendy.medicalsystem.entity.CaseHistoryValue;

import java.io.File;
import java.util.List;

public class MyCaseHistoryAdapter extends BaseAdapter {
    private List<CaseHistoryValue> mCaseHistoryValue;
    private Context context;
    private LayoutInflater layoutInflater;

    public MyCaseHistoryAdapter(Context context,List<CaseHistoryValue> mCaseHistoryValue){
        this.mCaseHistoryValue = mCaseHistoryValue;
        this.context = context;
    }

    @Override
    public int getCount() {
        return mCaseHistoryValue.size();
    }

    @Override
    public Object getItem(int i) {
        return mCaseHistoryValue.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final MyCaseHistoryAdapter.MyViewHolder holder;
        layoutInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(view == null){
            view = layoutInflater.inflate(R.layout.item_lv,viewGroup,false);
            holder = new MyCaseHistoryAdapter.MyViewHolder();
            holder.tv_date = view.findViewById(R.id.tv_date);
            holder.tv_title = view.findViewById(R.id.tv_title);
            holder.iv_case = view.findViewById(R.id.iv_case);
            holder.tv_content = view.findViewById(R.id.tv_content);
            view.setTag(holder);
        } else {
            holder = (MyViewHolder) view.getTag();
        }
        if(!mCaseHistoryValue.isEmpty()){
            holder.tv_date.setText(mCaseHistoryValue.get(i).getCaseHistoryDate()+"  ");
            holder.tv_title.setText(mCaseHistoryValue.get(i).getCaseHistoryTitle());
            holder.iv_case.setBackgroundResource(R.drawable.mess2);
            holder.tv_content.setText("  " + mCaseHistoryValue.get(i).getCaseHistoryContent());
        }
        return view;
    }

    public static class MyViewHolder{
        TextView tv_date;
        TextView tv_title;
        ImageView iv_case;
        TextView tv_content;
    }
}
