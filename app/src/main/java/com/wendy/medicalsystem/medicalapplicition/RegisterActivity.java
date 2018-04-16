package com.wendy.medicalsystem.medicalapplicition;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.wendy.medicalsystem.R;
import com.wendy.medicalsystem.entity.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;


/**
 * Created by sjaiwl on 15/3/28.
 */
public class RegisterActivity extends Activity {

    private TextView cancelButton;
//    private TextView userSexManImage;
//    private TextView userSexWomanImage;
//    private LinearLayout manButton;
//    private LinearLayout womanButton;
    private EditText userName;
    private EditText userPass;
    private EditText userConfirmPass;
    private RelativeLayout registerButton;
    private String category = null;
    private ArrayAdapter<String> adapter;
    private Spinner spinner;
    private String community ;
    private String username;
    private String userPassword;

    private final String PREFERENCE_NAME = "userInfo";
    private String successResponse = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.register_page);
        Bmob.initialize(this, "dd4cb01f5cd8540023d02f6fdd8220c4");
        //  User user = new User("1", "1", "1", "1");
        // Toast.makeText(getApplicationContext() , ""+user.save(), Toast.LENGTH_LONG).show();

        //  User user1 = DataSupport.find(User.class, 1);
        //Toast.makeText(getApplicationContext() , ""+user1.getLevel(), Toast.LENGTH_LONG).show();
        // Log.i("zzl" , user1.getCategory());
        initView();
        initData();
        category = "用户";
    }
    final String  s1 = null;
    private void initView() {
        cancelButton = (TextView) findViewById(R.id.registerPage_cancelButton);
//        userSexManImage = (TextView) findViewById(R.id.registerPage_userSexManImage);
//        userSexWomanImage = (TextView) findViewById(R.id.registerPage_userSexWomanImage);
//        manButton = (LinearLayout) findViewById(R.id.registerPage_userSexMan);
//        womanButton = (LinearLayout) findViewById(R.id.registerPage_userSexWoman);
        userName = (EditText) findViewById(R.id.registerPage_userName);
        userPass = (EditText) findViewById(R.id.registerPage_userPassword);
        userConfirmPass = (EditText) findViewById(R.id.registerPage_userConfirmPassword);
        registerButton = (RelativeLayout) findViewById(R.id.registerPage_registerButton);
        spinner = (Spinner) findViewById(R.id.spin);
        final String[] testDate = {"A社区" ,"B社区"};
        //adapter = new ArrayAdapter<String>(this , android.R.layout.simple_spinner_item , testDate);
        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        //spinner.setAdapter(adapter);
		adapter = new ArrayAdapter<String>(this , android.R.layout.simple_spinner_dropdown_item , testDate);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

    }

    private void initData() {
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
//        manButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                userSexManImage.setBackground(getResources().getDrawable(R.mipmap.r_man_after));
//                userSexWomanImage.setBackground(getResources().getDrawable(R.mipmap.r_woman_before));
//                category = "医生";
//            }
//        });
//
//        womanButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                userSexManImage.setBackground(getResources().getDrawable(R.mipmap.r_man_before));
//                userSexWomanImage.setBackground(getResources().getDrawable(R.mipmap.r_woman_after));
//                category = "用户";
//            }
//        });
//
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkData()) {
                    postData();
                }
            }
        });
    }

    private boolean checkData() {
        community = spinner.getSelectedItem().toString();
        if (userName.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "请输入姓名！", Toast.LENGTH_SHORT).show();
            return false;
        }
//        if (category == null) {
//            Toast.makeText(this, "请选择类别！", Toast.LENGTH_SHORT).show();
//            return false;
//        }
        if (community == null) {
            Toast.makeText(this, "社区不能为空！", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (userPass.getText().toString().trim().isEmpty() || userConfirmPass.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "密码不能为空！", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!(userPass.getText().toString().trim().equals(userConfirmPass.getText().toString().trim()))) {
            Toast.makeText(this, "输入密码不一致！", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void postData() {
//        if ("医生".equals(category)) {
//            User user = new User();
//            user.setUsername(userName.getText().toString().trim());
//            user.setPassword(userPass.getText().toString().trim());
//            user.setCategory(category);
//            user.setCommunity(community);
//            user.setLevel(1);
//            user.signUp(new SaveListener<String>() {
//                @Override
//                public void done(String s, BmobException e) {
//                    if (e == null) {
//                        Toast.makeText(getApplicationContext(), "医生注册成功", Toast.LENGTH_LONG).show();
//                    } else {
//                        Toast.makeText(getApplicationContext(), "用户已注册：", Toast.LENGTH_LONG).show();
//                    }
//                }
//            });
//        } else {
            User user = new User();
            user.setUsername(userName.getText().toString().trim());
            user.setPassword(userPass.getText().toString().trim());
            user.setCategory(category);
            user.setCommunity(community);
            user.setLevel(2);
            user.signUp(new SaveListener<String>() {
                @Override
                public void done(String s, BmobException e) {
                    if (e == null) {
                        Toast.makeText(getApplicationContext(), "用户注册成功", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "用户已注册：", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }

//}
