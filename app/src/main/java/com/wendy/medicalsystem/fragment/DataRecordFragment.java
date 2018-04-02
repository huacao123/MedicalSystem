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
import com.wendy.medicalsystem.entity.BloodGlucoseValue;
import com.wendy.medicalsystem.entity.User;
import com.wendy.medicalsystem.medicalapplicition.AddVerdanaActivity;
import com.wendy.medicalsystem.medicalapplicition.ChartActivity;

import java.io.Serializable;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobPointer;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;


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
                Log.d("wenfang","chart");

                User user = BmobUser.getCurrentUser(User.class);
                BmobQuery<BloodGlucoseValue> query = new BmobQuery<>();
                query.addWhereEqualTo("user",new BmobPointer(user));
                query.setLimit(7);
                query.findObjects(new FindListener<BloodGlucoseValue>() {
                    @Override
                    public void done(List<BloodGlucoseValue> list, BmobException e) {
                        if (e == null){
                            Intent intent = new Intent();
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("User",(Serializable)list);
                            intent.setClass(getActivity(),ChartActivity.class);
                            intent.putExtras(bundle);
                            //Log.d("wenfang","list:" + list.size());
                            //Intent intent = new Intent(getActivity() , ChartActivity.class);
                            startActivity(intent);
                        }
                    }
                });


            }
        });
        txt_content.setText("第二个Fragment");
        return view;
    }
}
