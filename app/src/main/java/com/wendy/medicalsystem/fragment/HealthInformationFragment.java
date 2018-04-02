package com.wendy.medicalsystem.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wendy.medicalsystem.R;
import com.wendy.medicalsystem.tools.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Jay on 2015/8/28 0028.
 */
public class HealthInformationFragment extends Fragment {

    public HealthInformationFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.health_information_page, container, false);

        List<String> images = new ArrayList<>();
        images.add("http://bmob-cdn-17711.b0.upaiyun.com/2018/03/31/b8c5f52e4014213680d7ae85f235e452.jpg");
        images.add("http://bmob-cdn-17711.b0.upaiyun.com/2018/03/31/777122cd40ce71ab801222c676c96230.jpg");
        images.add("http://bmob-cdn-17711.b0.upaiyun.com/2018/03/31/d837423940f28e578026c4656bbda6fc.jpg");
        images.add("http://bmob-cdn-17711.b0.upaiyun.com/2018/03/31/46ffb35140a9b94f80b0ee0064acb261.jpg");
        Banner banner = (Banner) view.findViewById(R.id.banner);
        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(images);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.DepthPage);
        //设置标题集合（当banner样式有显示title时）
        //banner.setBannerTitles("健康资讯");
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(1500);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
        return view;
    }
}
