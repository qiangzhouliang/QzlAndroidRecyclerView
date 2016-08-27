package com.example.qzl.qzlandroidrecyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;

import com.example.qzl.qzlandroidrecyclerview.adapter.QzlGridAdapter;

import java.util.ArrayList;
import java.util.List;

public class GridActivity extends AppCompatActivity {

    private List<Integer> mDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);
        final ImageView qzlIV = (ImageView) findViewById(R.id.qzlimageview);

        QzlRecycleView recycleView = (QzlRecycleView) findViewById(R.id.qzl_rv);
        //设置布局管理器
        //参数二：展示方向，参数三：是否反转布局
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //默认是垂直，在此处设置为水平
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recycleView.setLayoutManager(layoutManager);

        //创建gridadapter
        mDataList = getDataImage();
        QzlGridAdapter gridAdapter = new QzlGridAdapter(this, mDataList);
        recycleView.setAdapter(gridAdapter);


        //设置单击事件
        gridAdapter.setOnItemClickListener(new QzlGridAdapter.OnItemClickListener() {
            @Override
            public void onClickListener(View view, int position) {
                qzlIV.setImageResource(mDataList.get(position));
            }
        });

        //设置滚动事件
        recycleView.setOnItemScrollChangeListener(new QzlRecycleView.OnItemScrollChangeListener() {
            @Override
            public void onItemScrollChange(View view, int position) {
                qzlIV.setImageResource(mDataList.get(position));
            }
        });
    }

    private List<Integer> getDataImage(){
        List<Integer> dataList = new ArrayList<>();
        dataList.add(R.drawable.a);
        dataList.add(R.drawable.b);
        dataList.add(R.drawable.c);
        dataList.add(R.drawable.d);
        dataList.add(R.drawable.e);
        return dataList;
    }
}
