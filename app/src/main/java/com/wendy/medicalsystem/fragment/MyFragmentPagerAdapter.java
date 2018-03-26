package com.wendy.medicalsystem.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.wendy.medicalsystem.function.UserInfo;
import com.wendy.medicalsystem.medicalapplicition.MainActivity;


/**
 * Created by Jay on 2015/8/31 0031.
 */
public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

    private final int PAGER_COUNT = 3;
    private HealthInformationFragment myFragment1 = null;
    private DataRecordFragment myFragment2 = null;
    private SettingFragment myFragment3 = null;
    private DataViewFragment myFragment4 = null;


    public MyFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
        myFragment1 = new HealthInformationFragment();
        myFragment2 = new DataRecordFragment();
        myFragment3 = new SettingFragment();
        myFragment4 = new DataViewFragment();
    }

    @Override
    public int getCount() {
        return PAGER_COUNT;
    }

    @Override
    public Object instantiateItem(ViewGroup vg, int position) {
        return super.instantiateItem(vg, position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        System.out.println("position Destory" + position);
        super.destroyItem(container, position, object);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case MainActivity.PAGE_ONE:
                fragment = myFragment1;
                break;
            case MainActivity.PAGE_TWO:
                if(UserInfo.user.doctor_Category.equals("医生")){
                    fragment = myFragment2;
                }else {
                    fragment = myFragment4;
                }
                break;
            case MainActivity.PAGE_FOUR:
                fragment = myFragment3;
                break;
          /*  case MainActivity.PAGE_FOUR:
                fragment = myFragment4;
                break;*/
        }
        return fragment;
    }


}

