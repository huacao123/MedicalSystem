package com.wendy.medicalsystem.medicalapplicition;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.wendy.medicalsystem.R;
import com.wendy.medicalsystem.adapter.MyDataAdapter;
import com.wendy.medicalsystem.entity.BloodGlucoseValue;
import com.wendy.medicalsystem.function.UserInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by - on 2018/3/21.
 */

public class AddVerdanaActivity extends Activity {

    private TextView tv_addData;
    private ListView lv_myDataList;
    private MyDataAdapter myDataAdapter;
    private List<BloodGlucoseValue> mBGValueList;
    private UserInfo userInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.add_patient_data_page);

        tv_addData = findViewById(R.id.tv_addData);
        tv_addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddVerdanaActivity.this,AddDataActivity.class));
            }
        });

        /*userInfo = DataSupport.find(UserInfo.class,UserInfo.user.doctor_id,true);
        lv_myDataList = findViewById(R.id.lv_myDataList);
        mBGValueList = new ArrayList<>();
        Log.d("wenfang","getmBGValueList"+userInfo.getmBGValueList().size());
        mBGValueList = userInfo.getmBGValueList();
        myDataAdapter = new MyDataAdapter(this,mBGValueList);
        lv_myDataList.setAdapter(myDataAdapter);*/


    }

}
