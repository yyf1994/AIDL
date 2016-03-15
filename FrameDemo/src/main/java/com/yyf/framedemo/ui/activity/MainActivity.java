package com.yyf.framedemo.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;

import com.yyf.framedemo.R;
import com.yyf.framedemo.ui.adapter.RecyclerViewAdapter;
import com.yyf.framedemo.ui.adapter.RecyclerViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abc on 2016/3/2.
 */
public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<String> mlist;
    private RecyclerViewAdapter adapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        mlist = new ArrayList();
        for(int i = 0;i<100;i++){
            mlist.add(String.valueOf(i));
        }

        //设置item动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter = new RecyclerViewAdapter(mlist,this) {
            @Override
            public int getItemLayoutId(int viewType) {

                return R.layout.recyclerview_item;

            }

            @Override
            public void bindData(RecyclerViewHolder holder, int position, Object item) {
                //调用holder.getView(),getXXX()方法根据id得到控件实例，进行数据绑定即可
                holder.setText(R.id.textview, (String) item).getTextView(R.id.textview,item).setText((Integer) item);
            }
        };
    }


    }
