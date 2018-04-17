package com.wendy.medicalsystem.medicalapplicition;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.wendy.medicalsystem.R;
import com.wendy.medicalsystem.adapter.MyCaseHistoryAdapter;
import com.wendy.medicalsystem.entity.CaseHistoryValue;
import com.wendy.medicalsystem.entity.User;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobPointer;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by Administrator on 2018/4/3.
 */

public class AddCaseHistoryActivity extends AppCompatActivity {

    private TextView tv_cancel;
    private FloatingActionButton fab_addCaseHistory;
    private ListView lv_myCaseHistoryList;
    private MyCaseHistoryAdapter myCaseHistoryAdapter;
    private List<String> list = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcasehistory);

        fab_addCaseHistory = findViewById(R.id.fab_addCaseHistory);

        fab_addCaseHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddCaseHistoryActivity.this,AddCaseActivity.class);
                startActivity(intent);
            }
        });

        tv_cancel = findViewById(R.id.tv_cancel);
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        lv_myCaseHistoryList = findViewById(R.id.lv_myCaseHistoryList);
        for (int i = 1; i < 32; i++) {
            list.add("2016-12-" + i);
        }
        myCaseHistoryAdapter = new MyCaseHistoryAdapter(list,AddCaseHistoryActivity.this);
        lv_myCaseHistoryList.setAdapter(myCaseHistoryAdapter);
        lv_myCaseHistoryList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(AddCaseHistoryActivity.this, "您点击了" + i, Toast.LENGTH_LONG).show();
            }
        });

/*        private void updateData(){
            User user = BmobUser.getCurrentUser(User.class);
            BmobQuery<CaseHistoryValue> query = new BmobQuery<>();
            query.addWhereEqualTo("user",new BmobPointer(user));
            query.findObjects(new FindListener<CaseHistoryValue>() {
                @Override
                public void done(List<CaseHistoryValue> mlist, BmobException e) {
                    if (e == null){
                        //myCaseHistoryAdapter = new MyCaseHistoryAdapter(mlist,AddCaseHistoryActivity.this);
                        //lv_myCaseHistoryList.setAdapter(myCaseHistoryAdapter);
                    }
                }
            });
        }*/

    }
}
