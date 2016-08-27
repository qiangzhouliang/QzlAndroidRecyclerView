package com.example.qzl.qzlandroidrecyclerview.decoration;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.qzl.qzlandroidrecyclerview.R;

/**
 * 分割线
 * Created by Qzl on 2016-08-26.
 */
public class QzlItemDecoration extends RecyclerView.ItemDecoration {

    private Paint mPaint = new Paint();//初始化一个画笔
    private Activity mActivity;

    public QzlItemDecoration(Activity activity) {
        mPaint.setColor(Color.BLUE);
        this.mActivity = activity;
    }

    /**
     * 第二种 添加线
     *
     * @param c
     * @param parent
     * @param state
     */
    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        c.drawColor(Color.BLACK);
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        Bitmap bitmap = BitmapFactory.decodeResource(mActivity.getResources(), R.mipmap.ic_launcher);
        c.drawBitmap(bitmap,400,400,null);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int position = parent.getChildAdapterPosition(view);
        outRect.set(0, 5 * position, 0, 5 * position);
    }

    /**
     * 第一种  添加分割线
     * @param c
     * @param parent
     */
//    @Override
//    public void onDrawOver(Canvas c, RecyclerView parent) {
//        super.onDrawOver(c, parent);
//        for (int i = 0; i < parent.getChildCount(); i++) {
//            View childAt = parent.getChildAt(i);
//
//            int left = childAt.getLeft();
//            int top = childAt.getBottom();
//            int right = childAt.getRight();
//            int buttom = childAt.getBottom();
//
//            //画线
//            c.drawLine(left, top, right, buttom, mPaint);
//        }
//    }
}
