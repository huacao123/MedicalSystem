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
import com.wendy.medicalsystem.entity.User;
import com.wendy.medicalsystem.function.UserInfo;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by - on 2018/3/21.
 */

public class AddVerdanaActivity extends Activity {

    private TextView tv_addData;
    private ListView lv_myDataList;
    private MyDataAdapter myDataAdapter;

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

        lv_myDataList = findViewById(R.id.lv_myDataList);
        User user = BmobUser.getCurrentUser(User.class);
        BmobQuery<BloodGlucoseValue> query = new BmobQuery<>();
        query.addWhereEqualTo("username",user);
        query.findObjects(new FindListener<BloodGlucoseValue>() {
            @Override
            public void done(List<BloodGlucoseValue> list, BmobException e) {
                myDataAdapter = new MyDataAdapter(AddVerdanaActivity.this,list);
                lv_myDataList.setAdapter(myDataAdapter);
            }
        });



    }

}
