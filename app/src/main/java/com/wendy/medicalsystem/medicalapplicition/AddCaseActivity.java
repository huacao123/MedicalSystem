package com.wendy.medicalsystem.medicalapplicition;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ImageView;

import com.wendy.medicalsystem.R;

public class AddCaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_case_history_page);

        EditText et_cease_history_title = findViewById(R.id.et_cease_history_title);
        ImageView iv_case_history_upload = findViewById(R.id.iv_case_history_upload);
        EditText et_sickness_describe = findViewById(R.id.et_sickness_describe);
    }
}
