package com.wendy.medicalsystem.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wendy.medicalsystem.R;
import com.wendy.medicalsystem.medicalapplicition.AddVerdanaActivity;
import com.wendy.medicalsystem.medicalapplicition.ChartActivity;


/**
 * Created by Jay on 2015/8/28 0028.
 */
public class DataRecordFragment extends Fragment {

    public DataRecordFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.patient_record_page,container,false);
        TextView txt_content = (TextView) view.findViewById(R.id.txt_content);
        ImageView addData = (ImageView) view.findViewById(R.id.addData);
        ImageView chart = (ImageView) view.findViewById(R.id.chart);
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity() , AddVerdanaActivity.class);
                startActivity(intent);
            }
        });

        chart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity() , ChartActivity.class);
                startActivity(intent);
            }
        });
        txt_content.setText("第二个Fragment");
        return view;
    }
}
