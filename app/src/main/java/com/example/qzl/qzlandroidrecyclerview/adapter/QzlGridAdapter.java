package com.example.qzl.qzlandroidrecyclerview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.qzl.qzlandroidrecyclerview.R;

import java.util.List;

/**
 * Created by Qzl on 2016-08-26.
 */
public class QzlGridAdapter extends RecyclerView.Adapter<QzlGridAdapter.QzlViewHolder> {

    private Context mContext;
    private List<Integer> dataList;

    private OnItemClickListener mOnItemClickListener;
    private OnItemLongClockListener mOnItemLongClockListener;
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClockListener(OnItemLongClockListener onItemLongClockListener) {
        mOnItemLongClockListener = onItemLongClockListener;
    }

    public QzlGridAdapter(Context context, List<Integer> dataList){
        this.mContext = context;
        this.dataList = dataList;
    }
    /**
     * 创建itemview
     * @param viewGroup
     * @return
     */
    @Override
    public QzlViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.recycle_grid_item_layout,viewGroup,false);
        QzlViewHolder viewHolder = new QzlViewHolder(itemView);
        return viewHolder;
    }

    /**
     * 做绑定
     */
    @Override
    public void onBindViewHolder(QzlViewHolder viewHolder, final int position) {
        Integer id = dataList.get(position);
        viewHolder.image.setImageResource(id);//设置图片

        //设置他的点击事件
        if (mOnItemClickListener != null){
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onClickListener(v,position);
                }
            });
        }

        if (mOnItemLongClockListener != null){
            viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mOnItemLongClockListener.onLongClickListener(v,position);
                    return true;//自己处理事件，屏蔽单击事件
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return dataList == null ? 0 : dataList.size();
    }

    public class QzlViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        public QzlViewHolder(View itemView) {
            super(itemView);
            if (itemView != null){
                image = (ImageView) itemView.findViewById(R.id.iv_image);
            }
        }
    }


    //单击接口回调
    public interface OnItemClickListener{
        public void onClickListener(View view,int position);
    }

    //长按接口回调
    public interface OnItemLongClockListener{
        public void onLongClickListener(View view,int position);
    }
}
