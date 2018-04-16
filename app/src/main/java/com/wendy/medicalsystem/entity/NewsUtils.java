package com.wendy.medicalsystem.entity;

import com.wendy.medicalsystem.R;
import com.wendy.medicalsystem.fragment.HealthInformationFragment;

import java.util.ArrayList;

/**
 * Created by Wendy on 2018/4/13.
 */

public class NewsUtils {
    public static ArrayList<NewsBean> getAllNews(HealthInformationFragment context) {
        ArrayList<NewsBean> arrayList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            NewsBean newsBean1 = new NewsBean();
            newsBean1.title = "糖尿病预防小常识";
            newsBean1.des = "糖尿病是一种慢性疾病，当胰腺产生不了足够的胰岛素或者人体无法有效地利用所产生的胰岛素时，就会出现糖尿病。";
            newsBean1.icon = context.getResources().getDrawable(R.drawable.mess3);
            newsBean1.news_url = "http://slide.news.sina.com.cn/s/slide_1_2841_101020.html#p=1";
            arrayList.add(newsBean1);

            NewsBean newsBean2 = new NewsBean();
            newsBean2.title = "我的糖尿病自我管理之路";
            newsBean2.des = "首先,糖尿病治疗不仅需要药物更需要知识,更需要行为的改变。治疗糖尿病需要医生和病人两方面积极性。病人的积极性更为重要。";
            newsBean2.icon = context.getResources().getDrawable(R.drawable.mess2);
            newsBean2.news_url = "http://slide.news.sina.com.cn/s/slide_1_2841_101024.html#p=1";
            arrayList.add(newsBean2);

            NewsBean newsBean3 = new NewsBean();
            newsBean3.title = "远离糖尿病，从健康饮食开始";
            newsBean3.des = "对糖尿病人来说，米饭不能吃饱，水果不能吃多，甜品基本不碰。那他们到底能吃什么？要对哪些食物忌口？营养专家为糖尿病人开出了“三宜三不宜”的健康食谱。";
            newsBean3.icon = context.getResources().getDrawable(R.drawable.mess1);
            newsBean3.news_url = "http://slide.news.sina.com.cn/s/slide_1_2841_101010.html#p=1";
            arrayList.add(newsBean3);
        }
        return arrayList;
    }
}
