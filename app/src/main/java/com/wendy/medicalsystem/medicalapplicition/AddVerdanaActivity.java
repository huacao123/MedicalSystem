package com.wendy.medicalsystem.medicalapplicition;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ListView;
import android.widget.TextView;

import com.wendy.medicalsystem.R;
import com.wendy.medicalsystem.adapter.MyDataAdapter;
import com.wendy.medicalsystem.entity.BloodGlucoseValue;
import com.wendy.medicalsystem.entity.User;

import java.io.Serializable;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobPointer;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by - on 2018/3/21.
 */

public class AddVerdanaActivity extends Activity {

    private TextView tv_data_cancel;
    private ListView lv_myDataList;
    private MyDataAdapter myDataAdapter;
    private TextView tv_Datashow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_addverdana);

        FloatingActionButton fab_addData = findViewById(R.id.fab_addData);
        fab_addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddVerdanaActivity.this,AddDataActivity.class));
            }
        });

        tv_Datashow = findViewById(R.id.tv_Datashow);
        tv_Datashow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(AddVerdanaActivity.this,ChartActivity.class));
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
                            intent.setClass(AddVerdanaActivity.this,ChartActivity.class);
                            intent.putExtras(bundle);
                            //Log.d("wenfang","list:" + list.size());
                            //Intent intent = new Intent(getActivity() , ChartActivity.class);
                            startActivity(intent);
                        }
                    }
                });
            }
        });

        tv_data_cancel = findViewById(R.id.tv_data_cancel);
        tv_data_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        /*tv_addData = findViewById(R.id.tv_addData);
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
        updateData();

    }

    @Override
    protected void onResume() {
        super.onResume();
        updateData();

    }

    private void updateData(){
        User user = BmobUser.getCurrentUser(User.class);
        BmobQuery<BloodGlucoseValue> query = new BmobQuery<>();
        query.addWhereEqualTo("user",new BmobPointer(user));
        query.findObjects(new FindListener<BloodGlucoseValue>() {
            @Override
            public void done(List<BloodGlucoseValue> list, BmobException e) {
                if (e == null){
                    Log.d("wenfang","list-size:"+list.size());
                    myDataAdapter = new MyDataAdapter(AddVerdanaActivity.this,list);
                    lv_myDataList.setAdapter(myDataAdapter);
                }
            }
        });
    }
}
