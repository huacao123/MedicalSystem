package com.wendy.medicalsystem.medicalapplicition;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.wendy.medicalsystem.R;

/**
 * Created by Wendy on 2018/4/24.
 */

public class HealthInfoDetail extends Activity {
    private TextView title;
    private TextView con;
    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.healthinformation_detail);

        title = findViewById(R.id.detail_text_title);
        con = findViewById(R.id.detail_text_con);
        img = findViewById(R.id.detail_imageView2);


    }
}
