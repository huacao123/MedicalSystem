package com.wendy.medicalsystem.adapter;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wendy.medicalsystem.R;
import com.wendy.medicalsystem.entity.CaseHistoryValue;

import java.io.File;
import java.util.List;

import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.DownloadFileListener;

public class MyCaseHistoryAdapter extends BaseAdapter {
    private List<CaseHistoryValue> mCaseHistoryValue;
    private Context context;
    private LayoutInflater layoutInflater;
    private String mCaseHistoryPictureUrl;

    public MyCaseHistoryAdapter(Context context,List<CaseHistoryValue> mCaseHistoryValue){
        this.mCaseHistoryValue = mCaseHistoryValue;
        this.context = context;
    }

    @Override
    public int getCount() {
        return mCaseHistoryValue.size();
    }

    @Override
    public Object getItem(int i) {
        return mCaseHistoryValue.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final MyCaseHistoryAdapter.MyViewHolder holder;
        layoutInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(view == null){
            view = layoutInflater.inflate(R.layout.item_lv,viewGroup,false);
            holder = new MyCaseHistoryAdapter.MyViewHolder();
            holder.tv_date = view.findViewById(R.id.tv_date);
            holder.tv_title = view.findViewById(R.id.tv_title);
            holder.iv_case = view.findViewById(R.id.iv_case);
            holder.tv_content = view.findViewById(R.id.tv_content);
            view.setTag(holder);
        } else {
            holder = (MyViewHolder) view.getTag();
        }
        if(!mCaseHistoryValue.isEmpty()){
            holder.tv_date.setText(mCaseHistoryValue.get(i).getCaseHistoryDate()+"  ");
            holder.tv_title.setText(mCaseHistoryValue.get(i).getCaseHistoryTitle());
            holder.iv_case.setBackgroundResource(R.drawable.mess2);
            holder.tv_content.setText("  " + mCaseHistoryValue.get(i).getCaseHistoryContent());
            mCaseHistoryPictureUrl = mCaseHistoryValue.get(i).getCaseHistoryPictureUrl();

            //BmobFile bmobfile =new BmobFile("test.png","",mCaseHistoryPictureUrl);
            //downloadFile(bmobfile);
        }
        return view;
    }

    private void downloadFile(BmobFile file){
        //允许设置下载文件的存储路径，默认下载文件的目录为：context.getApplicationContext().getCacheDir()+"/bmob/"
        File saveFile = new File(Environment.getExternalStorageDirectory(), file.getFilename());
        file.download(saveFile, new DownloadFileListener() {

            @Override
            public void onStart() {
               // Toast.makeText(MyCaseHistoryAdapter.this, "开始下载...", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void done(String savePath,BmobException e) {
                if(e==null){
                 //   toast("下载成功,保存路径:"+savePath);
                }else{
                 //   toast("下载失败："+e.getErrorCode()+","+e.getMessage());
                }
            }

            @Override
            public void onProgress(Integer value, long newworkSpeed) {
                Log.i("bmob","下载进度："+value+","+newworkSpeed);
            }

        });
    }

    public static class MyViewHolder{
        TextView tv_date;
        TextView tv_title;
        ImageView iv_case;
        TextView tv_content;
    }
}
