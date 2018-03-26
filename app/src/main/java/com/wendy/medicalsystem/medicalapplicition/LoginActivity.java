package com.wendy.medicalsystem.medicalapplicition;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.wendy.medicalsystem.R;
import com.wendy.medicalsystem.entity.User;
import com.wendy.medicalsystem.function.UserInfo;
import com.wendy.medicalsystem.tools.ExitApplication;
import com.wendy.medicalsystem.tools.UploadDialog;

import java.util.HashMap;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;


/**
 * Created by sjaiwl on 15/3/19.
 */
public class LoginActivity extends Activity {

    private EditText username;
    private EditText password;
    private RelativeLayout loginButton;
    private TextView registerButton;
    private TextView forgetPass;

    private final String PREFERENCE_NAME = "userInfo";
    private String userName, passWord;
    private UserInfo userInfo = null;
    private static final int REQUEST_CODE_FOR_LOGIN = 1;//登录
    private Dialog dialog;
    private static boolean isShowNetWorkState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.login_page);
        Bmob.initialize(this , "dd4cb01f5cd8540023d02f6fdd8220c4");
        initView();
        initData();
        ExitApplication.getInstance().addActivity(this);
    }


    private void initView() {
        username = (EditText) this.findViewById(R.id.login_usr);
        password = (EditText) this.findViewById(R.id.login_pass);
        loginButton = (RelativeLayout) this.findViewById(R.id.login_button);
        registerButton = (TextView) this.findViewById(R.id.login_register);
        forgetPass = (TextView) this.findViewById(R.id.login_forget);
        loginButton.setOnClickListener(onClickListener);
        registerButton.setOnClickListener(onClickListener);
        forgetPass.setOnClickListener(onClickListener);
        isShowNetWorkState = true;
    }

    private void initData() {
        dialog = new UploadDialog(this, R.style.UploadDialog, R.string.login_dialog_textView);
        dialog.setCanceledOnTouchOutside(false);

        SharedPreferences preferences = getSharedPreferences(PREFERENCE_NAME, Activity.MODE_PRIVATE);
        username.setText(preferences.getString("UserName", null));
        password.setText(preferences.getString("PassWord", null));

       /* if (isShowNetWorkState) {
            //判断网络接入状态
            if (NetworkUtils.isConnectInternet(this)) {
                if (NetworkUtils.isConnectWifi(this)) {
                    Toast.makeText(this, "当前接入的是wifi网络，请放心使用", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "当前接入的是移动网络，请注意流量", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "无法接入网络，请接入网络后重试", Toast.LENGTH_SHORT).show();
            }
            isShowNetWorkState = false;
        }*/
    }


    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.login_button:
                    doLogin();
                    break;
                case R.id.login_forget:
                    doForget();
                    break;
                case R.id.login_register:
                    doRegister();
                    break;
                default:
                    break;

            }
        }
    };

    private void doLogin() {
        if (checkData()) {
            if (dialog != null) {
                dialog.show();
            }
            postData();
        }
    }

    private void doRegister() {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }

    private void doForget() {
        /*ForgetPasswordActivity forgetPasswordActivity = new ForgetPasswordActivity();
        forgetPasswordActivity.show(LoginActivity.this);*/
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

    @Override
    protected void onPause() {
        super.onPause();

        SharedPreferences sharedPreferences = getSharedPreferences(PREFERENCE_NAME, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        userName = username.getText().toString();
        passWord = password.getText().toString();
        editor.putString("UserName", userName);
        editor.putString("PassWord", passWord);
        editor.commit();

    }

    private void postData() {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("doctor_name", username.getText().toString().trim());
        map.put("doctor_password", password.getText().toString().trim());
        //UserInfo.setUserInfo(userInfo);
        User user = new User();
        user.setUsername(username.getText().toString().trim());
        user.setPassword(password.getText().toString().trim());
        user.login(new SaveListener<User>() {
            @Override
            public void done(User user, BmobException e) {
                if (e == null){
                    if (dialog != null && dialog.isShowing()) {
                        dialog.dismiss();
                    }
                    Toast.makeText(getApplicationContext(), "登录成功", Toast.LENGTH_SHORT).show();
                    userInfo = new UserInfo();
                    //userInfo.setDoctor_id(1);
                    userInfo.setDoctor_name(user.getUsername());
                    userInfo.setDoctor_Category(user.getCategory());
                    UserInfo.setUserInfo(userInfo);
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivityForResult(intent, REQUEST_CODE_FOR_LOGIN);
                }else {
                    Toast.makeText(getApplicationContext(), "登录失败"+e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });


    }



    private boolean checkData() {
        if (username.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "请输入用户名", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (password.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        /*if (requestCode == REQUEST_CODE_FOR_LOGIN && resultCode == RESULT_OK) {
            WebImageCache webImageCache = new WebImageCache(getApplicationContext());
            webImageCache.clear();
            if (dialog != null && dialog.isShowing()) {
                dialog.dismiss();
                dialog = null;
            }
            finish();
        }*/
        super.onActivityResult(requestCode, resultCode, data);
    }
}
