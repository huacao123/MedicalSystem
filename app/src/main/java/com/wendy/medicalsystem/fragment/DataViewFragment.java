package com.wendy.medicalsystem.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wendy.medicalsystem.R;


/**
 * Created by Jay on 2015/8/28 0028.
 */
public class DataViewFragment extends Fragment {

    public DataViewFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fg_content,container,false);
        TextView txt_content = (TextView) view.findViewById(R.id.txt_content);
        txt_content.setText("第四个Fragment");
        Log.e("HEHE", "4日狗");
        return view;
    }
}
