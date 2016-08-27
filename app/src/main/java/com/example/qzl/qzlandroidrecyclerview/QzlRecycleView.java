package com.example.qzl.qzlandroidrecyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

/**
 * 自定义视图
 *
 * 1 想清楚recycleView的基本使用
 * 2 分析listView与recycleView的基本区别
 * 3 实现与listView相同的列表显示功能
 *     3.1 创建adapter
 *     3.2 创建分割线
 * 4 实现类似于listView的点击事件和长按事件
 * 5 自定义组件
 *   5.1 实现scrollListener监听
 *   5.2 绑定scrollView监听
 *   5.3 实现scrollView滚动切换图片
 * Created by Qzl on 2016-08-26.
 */
public class QzlRecycleView extends RecyclerView {

    //1 添加滚动事件
    //2 绑定滚动事件
    //3 图片预览

    private OnItemScrollChangeListener mOnItemScrollChangeListener;
    private View mCurrentView;

    public void setOnItemScrollChangeListener(OnItemScrollChangeListener onItemScrollChangeListener) {
        mOnItemScrollChangeListener = onItemScrollChangeListener;
    }

    public QzlRecycleView(Context context) {
        super(context);
        initListener();
    }

    public QzlRecycleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initListener();
    }

    public QzlRecycleView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initListener();
    }

    /**
     * 设置滚动监听，初始化滑动监听
     */
    private void initListener(){
        this.setOnScrollListener(mOnScrollListener);
    }

    /**
     * 布局发生改变就会调运
     * @param changed
     * @param l
     * @param t
     * @param r
     * @param b
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        //获取当前显示的view，并且获取可显示区域的第一个
        mCurrentView = getChildAt(0);
    }

    OnScrollListener mOnScrollListener = new OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);

        }

        /**
         * 正在滚动
         * @param recyclerView
         * @param dx
         * @param dy
         */
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            //保存当前显示的第一试图
            View newChildView = getChildAt(0);
            if (mOnItemScrollChangeListener != null){
                if (newChildView != null && newChildView != mCurrentView){
                    mCurrentView = newChildView;
                    mOnItemScrollChangeListener.onItemScrollChange(mCurrentView,getChildPosition(mCurrentView));
                }
            }
        }
    };

    /**
     * 滑动条目的监听
     */
    public interface OnItemScrollChangeListener{
        public void onItemScrollChange(View view,int position);
    }
}
