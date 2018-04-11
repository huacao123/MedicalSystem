package com.wendy.medicalsystem.medicalapplicition;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wendy.medicalsystem.R;
import com.wendy.medicalsystem.entity.User;
import com.wendy.medicalsystem.function.AppConfiguration;
import com.wendy.medicalsystem.function.UserInfo;
import com.wendy.medicalsystem.smart.SmartImageView;
import com.wendy.medicalsystem.tools.SelectPopupWindow;
import com.wendy.medicalsystem.tools.UploadDialog;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.QueryListener;


/**
 * Created by sjaiwl on 15/4/2.
 */
public class MineInformation extends Activity implements View.OnClickListener {
    private RelativeLayout userPhotoButton;
    private RelativeLayout userNameButton;
    private RelativeLayout userSexButton;
    private RelativeLayout userBirthdayButton;
    private RelativeLayout userDepartmentButton;
    private RelativeLayout userCategoryButton;
    private RelativeLayout userTelephoneButton;

    private SmartImageView userPhoto;
    private ImageView imageView;
    private TextView userName;
    private TextView userSex;
    private TextView userBirthday;
    private TextView userDepartment;
    private TextView userCategory;
    private TextView userTelephone;
    private TextView cancelButton;

    private SelectPopupWindow menuWindow;
    private static final int PHOTO_REQUEST_CAMERA = 1;// 拍照
    private static final int PHOTO_REQUEST_GALLERY = 2;// 从相册中选择
    private static final int PHOTO_REQUEST_CUT = 3;// 结果
    private static final String PHOTO_FILE_NAME = "temp_photo.jpg";
    private File tempFile;
    private File tempFile1;
    private File file;
    private Bitmap bitmap;
    private String doctor_url = null;
    private Dialog dialog;
    private Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.mine_information);
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

    private void initView() {
        imageView = findViewById(R.id.iv_test);
        dialog = new UploadDialog(this, R.style.UploadDialog, R.string.upload_dialog_textView);
        dialog.setCanceledOnTouchOutside(false);
        userPhotoButton = findViewById(R.id.mineInformation_userPhoto);
        userNameButton = findViewById(R.id.mineInformation_userName);
        userSexButton = findViewById(R.id.mineInformation_userSex);
        userBirthdayButton = findViewById(R.id.mineInformation_userBirthday);
       /* userDepartmentButton = findViewById(R.id.mineInformation_userDepartment);*/
        userCategoryButton = findViewById(R.id.mineInformation_userCategory);
        userTelephoneButton = findViewById(R.id.mineInformation_userTelephone);
        userPhoto = findViewById(R.id.mineInformation_userPhoto_realPhoto);
        userName = findViewById(R.id.mineInformation_userName_realName);
        userSex = findViewById(R.id.mineInformation_userSex_realSex);
        userBirthday = findViewById(R.id.mineInformation_userBirthday_realBirthday);
       /* userDepartment = findViewById(R.id.mineInformation_userDepartment_realDepartment);*/
        userCategory = findViewById(R.id.mineInformation_userCategory_realSex);
        userTelephone = findViewById(R.id.mineInformation_userTelephone_realTelephone);
        cancelButton = findViewById(R.id.mineInformation_cancelButton);
        cancelButton.setOnClickListener(this);
        userPhotoButton.setOnClickListener(this);
        userNameButton.setOnClickListener(this);
        userSexButton.setOnClickListener(this);
        userBirthdayButton.setOnClickListener(this);
        /*userDepartmentButton.setOnClickListener(this);*/
        userCategoryButton.setOnClickListener(this);
        userTelephoneButton.setOnClickListener(this);
    }

    private void initData() {
        /*User user = BmobUser.getCurrentUser(User.class);
        BmobQuery<User> query = new BmobQuery<>();
        query.getObject(user.getObjectId(), new QueryListener<User>() {
            @Override
            public void done(User user, BmobException e) {
                if(e==null) {
                    setTextFunction(userName, user.getUsername());
                    setTextFunction(userCategory, user.getCategory());
                    setTextFunction(userSex, user.getSex().toString());
                    setTextFunction(userBirthday, user.getBirthday());
                    setTextFunction(userTelephone, user.getTelephone());
                }
            }
        });*/
      if (UserInfo.user.getDoctor_url() != null) {
            userPhoto.setImageUrl(UserInfo.user.getDoctor_url(), 2);
        }
        setTextFunction(userName, UserInfo.user.getDoctor_name());
        setTextFunction(userSex, UserInfo.user.getDoctor_url());
        setTextFunction(userBirthday, UserInfo.user.getDoctor_birthday());
        setTextFunction(userCategory, UserInfo.user.getDoctor_Category());
        setTextFunction(userTelephone, UserInfo.user.getDoctor_telephone());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mineInformation_cancelButton:
                finish();
                break;
            case R.id.mineInformation_userPhoto:
                menuWindow = new SelectPopupWindow(this,
                        itemsOnClick);
                // 显示窗口
                menuWindow.showAtLocation(
                        this.findViewById(R.id.mineInformation_topText),
                        Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                // 设置layout在PopupWindow中显示的位置
                menuWindow.update();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        WindowManager.LayoutParams params = getWindow().getAttributes();
                        params.alpha = 0.5f;
                        getWindow().setAttributes(params);
                    }
                }, 50);
                break;
           /* case R.id.mineInformation_userName:
                updateInformation("updateName");
                break;*/
            case R.id.mineInformation_userSex:
                updateInformation("updateSex");
                break;
            case R.id.mineInformation_userBirthday:
                updateInformation("updateBirthday");
                break;
            /*case R.id.mineInformation_userDepartment:
                updateInformation("updateDepartment");
                break;*/
            /*case R.id.mineInformation_userCategory:
                updateInformation("userCategory");
                break;*/
            case R.id.mineInformation_userTelephone:
                updateInformation("updateTelephone");
                break;
            default:
                break;
        }
    }

    private void updateInformation(String object) {
        Intent updateInfo = new Intent();
        updateInfo.putExtra("index", object);
        updateInfo.setClass(this, UpdateInformation.class);
        startActivity(updateInfo);
    }

    private View.OnClickListener itemsOnClick = new View.OnClickListener() {
        public void onClick(View v) {
            menuWindow.dismiss();
            switch (v.getId()) {
                case R.id.mineInformation_selectWindow_takePhoto:
                    camera(v);
                    break;
                case R.id.mineInformation_selectWindow_takeGallery:
                    gallery(v);
                    break;
                default:
                    break;
            }
        }

    };

    private void setTextFunction(TextView textView, String string) {
        if (string == null || string.isEmpty()) {
            textView.setText("未设置");
        } else {
            textView.setText(string);
        }
    }

    /*
     * 相册
	 */
    public void gallery(View view) {
        //从相册选择照片
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        Intent wrapperIntent = Intent.createChooser(intent, null);
        startActivityForResult(wrapperIntent, PHOTO_REQUEST_GALLERY);
    }

    /*
     * 拍照
     */
    public void camera(View view) {
        int currentapiVersion = android.os.Build.VERSION.SDK_INT;

       // Intent wrapperIntent = Intent.createChooser(intent, null);

        // 拍照选择
        if (hasSdcard()) {
           // tempFile1 = new File(Environment.getExternalStorageDirectory().getAbsolutePath()
           //         + "/MedicalApplication/Camera/userImage/", PHOTO_FILE_NAME);

            tempFile1 = new File(getExternalCacheDir(),
                    "output_image.jpg");
            try{
                if (tempFile1.exists()){
                    tempFile1.delete();
                }
                tempFile1.createNewFile();
            }catch (IOException e){
                e.printStackTrace();
            }


            if(currentapiVersion < 24) {
                //intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempFile1));
                imageUri = Uri.fromFile(tempFile);
            }else {
                imageUri = FileProvider.getUriForFile(MineInformation.this,
                        "com.example.test",tempFile1);
            }
            Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
            intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
            Log.d("wenfang","----startActivityForResult");
            startActivityForResult(intent, PHOTO_REQUEST_CAMERA);
        }

    }

   // @SuppressLint("ShowToast")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("wenfang","----onActivityResult:"+requestCode);
        if (requestCode == PHOTO_REQUEST_GALLERY && resultCode == RESULT_OK) {
            if (data != null) {
                // 获取uri
                Uri uri = data.getData();
                crop(uri);
            }
        } else if (requestCode == PHOTO_REQUEST_CAMERA && resultCode == RESULT_OK) {
            Log.d("wenfang","----PHOTO_REQUEST_CAMERA");
            try{
                Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                imageView.setImageBitmap(bitmap);

            }catch (FileNotFoundException e){
                e.printStackTrace();
            }


            /*if (hasSdcard()) {
                tempFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath()
                        + "/MedicalApplication/Camera/userImage/", PHOTO_FILE_NAME);
                crop(Uri.fromFile(tempFile));
            } else {
                Toast.makeText(MineInformation.this, "未找到存储卡，无法存储照片", Toast.LENGTH_SHORT).show();
            }*/

        } else if (requestCode == PHOTO_REQUEST_CUT) {


            String dir = Environment.getExternalStorageDirectory()
                    .getAbsolutePath() + "/MedicalApplication/Camera/userImage/";
            file = new File(dir);

            if (!file.exists()) {
                // file不存在
                file.mkdirs();
            }
           // Bitmap bitmap2 = BitmapFactory.decodeStream(getContentResolver().openInputStream(Uri.fromFile(file));
            File picture = new File(file, "user_image.jpg");
            try {
                bitmap = data.getParcelableExtra("data");
                BufferedOutputStream bos = new BufferedOutputStream(
                        new FileOutputStream(picture));
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
                bos.flush();
                bos.close();
                upload(picture);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 裁剪
     *
     * @param uri
     * @function:
     * @author:Jerry
     * @date:2013-12-30
     */
    private void crop(Uri uri) {
        // 调用系统裁剪
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // 比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // 大小
        intent.putExtra("outputX", 300);
        intent.putExtra("outputY", 300);
        // 输出
        intent.putExtra("outputFormat", "JPEG");
        intent.putExtra("noFaceDetection", true);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, PHOTO_REQUEST_CUT);
    }

    private boolean hasSdcard() {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }

    //    /*
    //    * 上传照片
    //	 */
    @SuppressLint("ShowToast")
    public void upload(File file) {
        userPhoto.setImageUrl(file.toString(), 2, true);
/*        if (dialog != null) {
            dialog.show();
        }
        RequestParams params = new RequestParams();
        try {
            params.put("doctor_id", UserInfo.user.getDoctor_id().toString());
            params.put("doctor_url", file);
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
        String url = AppConfiguration.updateUserPictureUrl;
        AsyncHttpClient client = new AsyncHttpClient();
        client.post(url, params, new JsonHttpResponseHandler() {
            @SuppressLint("ShowToast")
            @Override
            public void onSuccess(JSONObject response) {
                try {
                    doctor_url = response.get("doctor_url").toString();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (doctor_url != null) {
                    UserInfo.user.setDoctor_url(doctor_url);
                    userPhoto.setImageUrl(UserInfo.user.getDoctor_url(), 2, true);
                    if (dialog != null && dialog.isShowing()) {
                        dialog.dismiss();
                    }
                    Toast.makeText(MineInformation.this, "头像修改成功", Toast.LENGTH_LONG).show();
                } else {
                    if (dialog != null && dialog.isShowing()) {
                        dialog.dismiss();
                    }
                    Toast.makeText(MineInformation.this, "头像修改失败", Toast.LENGTH_LONG).show();
                }
            }

            @SuppressLint("ShowToast")
            @Override
            public void onFailure(int statusCode, Header[] headers,
                                  byte[] responseBody, Throwable error) {
                if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                }
                Toast.makeText(MineInformation.this, "网络访问异常,请重试", Toast.LENGTH_LONG).show();

            }
        });*/
    }
}
