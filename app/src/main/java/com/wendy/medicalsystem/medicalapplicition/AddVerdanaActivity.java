package com.wendy.medicalsystem.medicalapplicition;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.squareup.timessquare.CalendarPickerView;
import com.wendy.medicalsystem.R;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by - on 2018/3/21.
 */

public class AddVerdanaActivity extends Activity {

    private ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.add_patient_data_page);

        Calendar nextYear = Calendar.getInstance();
        nextYear.add(Calendar.YEAR, 1);
        CalendarPickerView calendar = (CalendarPickerView) findViewById(R.id.calendar_view);
        Date today = new Date();
        calendar.init(today, nextYear.getTime())
                .withSelectedDate(today);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        String[] testDate = {"午饭前" ,"午饭后"};
        adapter = new ArrayAdapter<String>(this , android.R.layout.simple_spinner_item , testDate);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);
    }

}
