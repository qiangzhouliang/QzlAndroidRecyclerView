package com.example.qzl.qzlandroidrecyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.example.qzl.qzlandroidrecyclerview.adapter.QzlRecycleAdapter;
import com.example.qzl.qzlandroidrecyclerview.animal.MyItemAnimator;
import com.example.qzl.qzlandroidrecyclerview.decoration.QzlItemDecoration;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private QzlRecycleView mQzlRecycleView;

    //1、 创建adapter
    //2、创建分割线
    //3、图片预览
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mQzlRecycleView = (QzlRecycleView) findViewById(R.id.qzl_rv);

        //设置布局管理器
        //1 listView的形式
        //参数二：展示方向，参数三：是否反转布局
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);

        // 2 gridView形式
        //参数二：一个有多少列，参数三：展示方向，参数四：是否反转布局
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,3,LinearLayoutManager.VERTICAL,false);
        //是否可以跨行
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position == 0){
                    return 3;
                }
                if (position == 1){
                    return 2;
                }
                return 1;
            }
        });

        //3 瀑布流形式
        //参数一：多少列  参数二：流向
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);

        //默认的动画系统
        MyItemAnimator animator = new MyItemAnimator();
        animator.setRemoveDuration(500);
        animator.setAddDuration(3000);

        animator.setSupportsChangeAnimations(true);//设置是否支持改变动画
        animator.setChangeDuration(3000);//设置改变数时间

        animator.setMoveDuration(3000);//删除动画的时间

        mQzlRecycleView.setLayoutManager(linearLayoutManager);
        mQzlRecycleView.setItemAnimator(animator);
        //添加分割线
        mQzlRecycleView.addItemDecoration(new QzlItemDecoration(this));
        List<String> dataList = getDataList();
        final QzlRecycleAdapter recycleAdapter = new QzlRecycleAdapter(this,dataList);
        mQzlRecycleView.setAdapter(recycleAdapter);

        //设置点击事件
//        recycleAdapter.setOnItemClickListener(new QzlRecycleAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, int position) {
//                Toast.makeText(MainActivity.this, "点击：position - "+position, Toast.LENGTH_SHORT).show();
//            }
//        });

        recycleAdapter.setOnchildClickListener(new QzlRecycleAdapter.OnchildClickListener() {
            @Override
            public void onChildClick(RecyclerView parent, View view, int position, String data) {
                Toast.makeText(MainActivity.this, "当前位置" + position+"  数据 ："+data, Toast.LENGTH_SHORT).show();
                //recycleAdapter.remove(position);
//                recycleAdapter.add(position,"新增数据");
                recycleAdapter.changeAnim(position,"数据发生改变了");

            }
        });

        //社会自长按事件
        recycleAdapter.setOnItemLongClickListener(new QzlRecycleAdapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(MainActivity.this, "长按：position :"+position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private List<String> getDataList(){
        List<String> dataLIST = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            dataLIST.add(String.format(Locale.CHINA,"第%03d条数据%s",i, i % 2 == 0 ? "" : "----"));
        }
        return dataLIST;
    }
}
