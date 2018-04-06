package com.wendy.medicalsystem.medicalapplicition;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.wendy.medicalsystem.R;

/**
 * Created by Administrator on 2018/4/3.
 */

public class AddCaseHistoryActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcasehistory);

        Button button1 = findViewById(R.id.test);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddCaseHistoryActivity.this,AddCaseActivity.class);
                startActivity(intent);
            }
        });



    }
}
