package com.wendy.medicalsystem.medicalapplicition;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.wendy.medicalsystem.R;
import com.wendy.medicalsystem.adapter.MyDataAdapter;
import com.wendy.medicalsystem.entity.BloodGlucoseValue;
import com.wendy.medicalsystem.entity.User;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobPointer;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import lecho.lib.hellocharts.listener.ColumnChartOnValueSelectListener;
import lecho.lib.hellocharts.listener.ComboLineColumnChartOnValueSelectListener;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.ComboLineColumnChartData;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.SubcolumnValue;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.ColumnChartView;
import lecho.lib.hellocharts.view.ComboLineColumnChartView;

/**
 * Created by - on 2018/3/22.
 */

public class ChartActivity extends AppCompatActivity {

    private ColumnChartView mColumnChartView;
    List<BloodGlucoseValue> mBloodGlucoseValue;

    /*========== 数据相关 ==========*/
    private ColumnChartData mColumnChartData;               //柱状图数据
    public final static String[] xValues = new String[]{"1", "2", "3", "4", "5", "6","7"};
    public final static String[] yValues = new String[]{"1", "2", "3", "4", "5", "6","7"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_combo_line_column_chart);

        mBloodGlucoseValue = (List<BloodGlucoseValue>) this.getIntent().getSerializableExtra("User");


        Log.d("wenfang","mBloodGlucoseValue:" + mBloodGlucoseValue.size());

        mColumnChartView = (ColumnChartView) findViewById(R.id.ccv_main);
        LinearLayout ll_coordText = findViewById(R.id.ll_coordText);
        TextView tv_noChart = findViewById(R.id.tv_noChart);

        if(mBloodGlucoseValue.size() >= 7){
            for (int i = 0;i < mBloodGlucoseValue.size(); i++) {
            xValues[i] = mBloodGlucoseValue.get(i).getYear() + "-" + mBloodGlucoseValue.get(i).getMouth() + "-" + mBloodGlucoseValue.get(i).getDay();
            yValues[i] = mBloodGlucoseValue.get(i).getBGlucoseLevelValue();
            Log.d("wenfang", "xValues[i] : " + xValues[i] + "; yValues[i] : " + yValues[i]);
            }
            mColumnChartView.setVisibility(View.VISIBLE);
            ll_coordText.setVisibility(View.VISIBLE);
            tv_noChart.setVisibility(View.GONE);
            initView();
        }else {
            mColumnChartView.setVisibility(View.GONE);
            ll_coordText.setVisibility(View.GONE);
            tv_noChart.setVisibility(View.VISIBLE);
        }



       /* User user = BmobUser.getCurrentUser(User.class);
        BmobQuery<BloodGlucoseValue> query = new BmobQuery<>();
        query.addWhereEqualTo("user",new BmobPointer(user));
        query.setLimit(7);
        query.findObjects(new FindListener<BloodGlucoseValue>() {
            @Override
            public void done(List<BloodGlucoseValue> list, BmobException e) {
                if (e == null){
                    mBloodGlucoseValue = list;
                    for (int i = 0;i < mBloodGlucoseValue.size(); i++){
                        xValues[i] = mBloodGlucoseValue.get(i).getYear()+"-"+mBloodGlucoseValue.get(i).getMouth()+"-"+mBloodGlucoseValue.get(i).getDay();
                      //  yValues[i]  = mBloodGlucoseValue.get(i).getBGlucoseLevelValue();
                        Log.d("wenfang","xValues[i] : "+xValues[i]+"; yValues[i] : "+yValues[i]);
                    }

                }
            }
        });*/


       // android.util.Log.d("wenfang",""+);


    }
    private void initView() {

        Log.d("wenfang", "initView: "+"xValues[i] : " + xValues[1] + "; yValues[i] : " + yValues[1]);

        mColumnChartView.setOnValueTouchListener(new ValueTouchListener());
        /*========== 柱状图数据填充 ==========*/
        List<Column> columnList = new ArrayList<>(); //柱子列表
        List<SubcolumnValue> subcolumnValueList;     //子柱列表（即一个柱子，因为一个柱子可分为多个子柱）
        List<AxisValue> axisValues = new ArrayList<>();
        for (int i = 0; i < xValues.length; ++i) {
            subcolumnValueList = new ArrayList<>();
            subcolumnValueList.add(new SubcolumnValue(Float.parseFloat(yValues[i]), ChartUtils.pickColor()));
            //subcolumnValueList.add(new SubcolumnValue((float) Math.random() * 100f, ChartUtils.pickColor()));
            Log.d("wenfang","Float.parseFloat(yValues[i]= "+Float.parseFloat(yValues[i]));
            Column column = new Column(subcolumnValueList);
            column.setHasLabels(true);                    //设置列标签
//            column.setHasLabelsOnlyForSelected(true);       //只有当点击时才显示列标签

            columnList.add(column);

            //设置坐标值
            axisValues.add(new AxisValue(i).setLabel(xValues[i]));
        }

        mColumnChartData = new ColumnChartData(columnList);               //设置数据


        /*===== 坐标轴相关设置 =====*/
        Axis axisX = new Axis(axisValues); //将自定义x轴显示值传入构造函数
        Axis axisY = new Axis().setHasLines(true); //setHasLines是设置线条
        axisX.setName("日期");    //设置横轴名称
        axisY.setName("血糖值");    //设置竖轴名称
        mColumnChartData.setAxisXBottom(axisX); //设置横轴
        mColumnChartData.setAxisYLeft(axisY);   //设置竖轴

        //以上所有设置的数据、坐标配置都已存放到mColumnChartData中，接下来给mColumnChartView设置这些配置
        mColumnChartView.setColumnChartData(mColumnChartData);


        /*===== 设置竖轴最大值 =====*/
        //法一：
        Viewport v = mColumnChartView.getMaximumViewport();
        v.top = 20;
        mColumnChartView.setCurrentViewport(v);
        /*法二：
        Viewport v = mColumnChartView.getCurrentViewport();
        v.top = 100;
        mColumnChartView.setMaximumViewport(v);
        mColumnChartView.setCurrentViewport(v);*/
    }

    private class ValueTouchListener implements ColumnChartOnValueSelectListener {

        @Override
        public void onValueSelected(int columnIndex, int subcolumnIndex, SubcolumnValue value) {
            Toast.makeText(ChartActivity.this, xValues[columnIndex]+"成绩 : " +
                    (float)value.getValue(),Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onValueDeselected() {
            // TODO Auto-generated method stub

        }

    }

}
