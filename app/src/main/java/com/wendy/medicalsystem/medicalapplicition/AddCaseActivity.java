package com.wendy.medicalsystem.medicalapplicition;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.wendy.medicalsystem.R;
import com.wendy.medicalsystem.entity.CaseHistoryValue;
import com.wendy.medicalsystem.entity.User;
import com.wendy.medicalsystem.tools.ExitApplication;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UploadFileListener;

public class AddCaseActivity extends AppCompatActivity implements View.OnClickListener{
    public static final int TAKE_PHOTO = 1;
    private static final int  UPLOAD_OK = 2;
    private static final int  UPLOAD_FAIL = 3;
    private ImageView iv_case_history_upload;
    private Uri imageUri;
    private File outputImage;
    private BmobFile bmobFile;
    private TextView tv_case_history_date;
    private EditText et_case_history_title;
    private EditText et_sickness_describe;
    private TextView tv_ensure;
    private TextView tv_cancel;
    private Button bt_upload_ensure;
    private ImageView iv_sign_check_icon;

    private String mCaseHistoryDate;
    private String mCaseHistoryTitle;
    private String mSicknessDescribe;
    private String mCaseHistoryPictureUrl;

    private boolean isUploadEnsure = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcase);

        tv_case_history_date = findViewById(R.id.tv_case_history_date);
        et_case_history_title = findViewById(R.id.et_case_history_title);
        iv_case_history_upload = findViewById(R.id.iv_case_history_upload);
        et_sickness_describe = findViewById(R.id.et_sickness_describe);
        bt_upload_ensure = findViewById(R.id.bt_upload_ensure);
        tv_ensure = findViewById(R.id.tv_ensure);
        tv_cancel = findViewById(R.id.tv_cancel);
        tv_cancel.setOnClickListener(this);
        tv_ensure.setOnClickListener(this);
        tv_case_history_date.setOnClickListener(this);
        iv_case_history_upload.setOnClickListener(this);
        bt_upload_ensure.setOnClickListener(this);
        iv_sign_check_icon = findViewById(R.id.iv_sign_check_icon);
        bt_upload_ensure.setEnabled(false);
        ExitApplication.getInstance().addActivity(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_cancel:
                finish();
                break;
            case R.id.tv_ensure:
                if(!checkData()){
                    break;
                }
                mCaseHistoryDate = tv_case_history_date.getText().toString();
                mCaseHistoryTitle = et_case_history_title.getText().toString();
                mSicknessDescribe = et_sickness_describe.getText().toString();
                User user = BmobUser.getCurrentUser(User.class);
                CaseHistoryValue caseHistoryValue = new CaseHistoryValue();
                caseHistoryValue.setUser(user);
                caseHistoryValue.setCaseHistoryDate(mCaseHistoryDate);
                caseHistoryValue.setCaseHistoryTitle(mCaseHistoryTitle);
                caseHistoryValue.setCaseHistoryContent(mSicknessDescribe);
                caseHistoryValue.setCaseHistoryPictureUrl(mCaseHistoryPictureUrl);
                caseHistoryValue.save(new SaveListener<String>() {
                    @Override
                    public void done(String s, BmobException e) {
                        if(e==null){
                            Log.i("done","成功");
                        }else{
                            Log.i("done","失败："+e.getMessage());
                        }
                    }
                });


                startActivity(new Intent(AddCaseActivity.this,AddCaseHistoryActivity.class));
                Toast.makeText(AddCaseActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_case_history_date:
                AlertDialog.Builder builder = new AlertDialog.Builder(AddCaseActivity.this);
                builder.setTitle("请选择时间").setIcon(R.mipmap.ic_launcher);
                final LinearLayout layout_alert = (LinearLayout)getLayoutInflater().inflate(R.layout.datepicker_dialog,null);
                builder.setView(layout_alert);
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DatePicker datePicker = layout_alert.findViewById(R.id.date_picker);
                        Integer data1 = datePicker.getYear();
                        Integer data2 = datePicker.getMonth()+1;
                        Integer data3 = datePicker.getDayOfMonth();
                        tv_case_history_date.setText(data1+"-"+data2+"-"+data3);
                    }
                }).create().show();
                break;
            case R.id.iv_case_history_upload:
                isUploadEnsure = false;
                iv_sign_check_icon.setImageResource(R.drawable.alert);
                bt_upload_ensure.setText(R.string.case_history_upload_ensure);
                bt_upload_ensure.setEnabled(true);
                outputImage = new File(getExternalCacheDir(),
                        "output_image.jpg");
                try{
                    if (outputImage.exists()){
                        outputImage.delete();
                    }
                    outputImage.createNewFile();
                }catch (IOException e){
                    e.printStackTrace();
                }
                if(Build.VERSION.SDK_INT >= 24){
                    imageUri = FileProvider.getUriForFile(AddCaseActivity.this,
                            "com.wendy.fileprovider",outputImage);
                }else {
                    imageUri = Uri.fromFile(outputImage);
                }
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
                startActivityForResult(intent,TAKE_PHOTO);
                break;
            case R.id.bt_upload_ensure:
                bt_upload_ensure.setEnabled(false);
                bmobFile = new BmobFile(outputImage);
                bmobFile.uploadblock(new UploadFileListener() {
                    @Override
                    public void done(BmobException e) {
                        if(e==null){
                            Log.d("wenfang","---UPLOAD_OK---");
                            mCaseHistoryPictureUrl =  bmobFile.getFileUrl();//返回的上传文件的完整地址
                            bt_upload_ensure.setText(R.string.case_history_upload_ok);
                            iv_sign_check_icon.setImageResource(R.drawable.sign_check_icon);
                            isUploadEnsure = true;
                            Toast.makeText(AddCaseActivity.this, "上传文件成功 ", Toast.LENGTH_SHORT).show();
                        }else{
                            bt_upload_ensure.setEnabled(true);
                            iv_sign_check_icon.setImageResource(R.drawable.sign_error_icon);
                            isUploadEnsure = false;
                            Toast.makeText(AddCaseActivity.this, "上传文件失败 ", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onProgress(Integer value) {
                        // 返回的上传进度（百分比）
                        Log.d("wenfang","onProgress"+value);
                    }
                });
                break;
        }

    }

   /** private Handler mHandler = new Handler(){

        /*new Thread(new Runnable() {
                    @Override
                    public void run() {
                       // mHandler.sendEmptyMessage(UPLOAD_OK);
                        Message message = new Message();
                        message.what = UPLOAD_OK;
                        mHandler.sendMessage(message);
                    }
                }).start();
        @Override
        public void handleMessage(Message msg) {
            //super.handleMessage(msg);
            Log.d("wenfang","handleMessage"+msg.what);
            switch (msg.what){
                case UPLOAD_OK:
                   // iv_sign_check_icon.setBackground(getResources().getDrawable(R.drawable.sign_check_icon));
                    iv_sign_check_icon.setImageResource(R.drawable.sign_error_icon);
                    break;
                case UPLOAD_FAIL:

                    //iv_sign_check_icon.setBackground(getResources().getDrawable(R.drawable.sign_error_icon));
                    break;

            }
        }
    };**/

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case TAKE_PHOTO:
                if(resultCode == RESULT_OK){
                    try{
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver()
                                .openInputStream(imageUri));
                        iv_case_history_upload.setImageBitmap(bitmap);
                    }catch (FileNotFoundException e){
                        e.printStackTrace();
                    }
                }
                break;
            default:
                break;
        }
    }

    private boolean checkData(){
        if(! isUploadEnsure){
            Toast.makeText(this, "请上传图片！", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(tv_case_history_date.getText().toString().isEmpty()){
            Toast.makeText(this, "请输入日期！", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(et_case_history_title.getText().toString().isEmpty()){
            Toast.makeText(this, "请输入标题！", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(et_sickness_describe.getText().toString().isEmpty()){
            Toast.makeText(this, "请描述症状! ", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
