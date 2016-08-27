package com.example.qzl.qzlandroidrecyclerview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.qzl.qzlandroidrecyclerview.R;

import java.util.List;

/**
 * 适配器
 * Created by Qzl on 2016-08-26.
 */
public class QzlRecycleAdapter extends RecyclerView.Adapter<QzlRecycleAdapter.QzlViewHolder> implements View.OnClickListener {

    private Context mContext;
    private List<String> mDataList;

    private OnItemClickListener mOnItemClickListener;
    private OnchildClickListener mOnchildClickListener;
    private OnItemLongClickListener mOnItemLongClickListener;
    private RecyclerView mRecyclerView;
    private View mItemViewList,mItemViewGrid;

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        mOnItemLongClickListener = onItemLongClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public void setOnchildClickListener(OnchildClickListener onchildClickListener) {
        mOnchildClickListener = onchildClickListener;
    }

    public QzlRecycleAdapter(Context context, List<String> dataList) {
        this.mContext = context;
        this.mDataList = dataList;
    }

    /**
     * 创建itemview
     *
     * @param viewGroup
     * @return
     */
    @Override
    public QzlViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        if (viewType == 2) {
            mItemViewList = LayoutInflater.from(mContext).inflate(R.layout.recycle_list_item_layout, viewGroup, false);
            QzlViewHolder qzlViewHolder = new QzlViewHolder(mItemViewList);
            mItemViewList.setOnClickListener(this);
            return qzlViewHolder;
        }else if (viewType == 1){
            mItemViewGrid = LayoutInflater.from(mContext).inflate(R.layout.recycle_grid_item_layout, viewGroup, false);
            QzlViewHolder viewHolder = new QzlViewHolder(mItemViewGrid);
            mItemViewGrid.setOnClickListener(this);
            return viewHolder;
        }
        return null;
    }

    /**
     * 绑定数据到控件上
     *
     * @param qzlViewHolder
     * @param position
     */
    @Override
    public void onBindViewHolder(QzlViewHolder qzlViewHolder, final int position) {
        if (position == 0) {
            qzlViewHolder.image.setImageResource(R.drawable.b);
        } else {
            String text = mDataList.get(position - 1);
            qzlViewHolder.contentTv.setText(text);
        }

        //设置条目点击事件
        if (mOnItemClickListener != null) {
            qzlViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClick(v, position);
                }
            });
        }

        //设置条目长按事件
        if (mOnItemLongClickListener != null) {
            qzlViewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mOnItemLongClickListener.onItemLongClick(v, position);
                    return true;//可以屏蔽点击事件，事件不传递，自己处理掉
                }
            });
        }
    }

    /**
     * 条目的数量
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return this.mDataList == null ? 1 : this.mDataList.size()+1;
    }

    /**
     * item类型个数
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return 1;
        } else {
            return 2;
        }
    }

    /**
     * 自己提供一个viewHolder，让我们来实现
     */
    public class QzlViewHolder extends RecyclerView.ViewHolder {

        TextView contentTv;
        ImageView image;

        public QzlViewHolder(View itemView) {
            super(itemView);
            if (itemView != null) {
                if (itemView == mItemViewGrid){
                    System.out.println("mItemViewGrid");
                    image = (ImageView) itemView.findViewById(R.id.iv_image);
                } else {
                    contentTv = (TextView) itemView.findViewById(R.id.tv_content);
                }
            }
        }
    }

    @Override
    public void onClick(View v) {
        if (mRecyclerView != null && mOnchildClickListener != null) {
            int position = mRecyclerView.getChildPosition(v);
            mOnchildClickListener.onChildClick(mRecyclerView, v, position, mDataList.get(position));
        }
    }


    /**
     * 当他被连接到一个recycleView上为recycleView提供数据时会调用到
     *
     * @param recyclerView
     */
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.mRecyclerView = recyclerView;
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        this.mRecyclerView = null;
    }


    /**
     * 删除数据的方法
     *
     * @param position
     */
    public void remove(int position) {
        mDataList.remove(position);
        //notifyDataSetChanged();//刷新数据(在这里不能用此方法)
        notifyItemRemoved(position);
    }

    /**
     * 添加数据
     *
     * @param position
     * @param data
     */
    public void add(int position, String data) {
        mDataList.add(position, data);
        notifyItemInserted(position);
    }

    /**
     * 数据发生改变动画的方法
     *
     * @param position
     */
    public void changeAnim(int position, String data) {
        mDataList.remove(position);
        mDataList.add(position, data);
        notifyItemChanged(position);
    }

    //点击事件监听
    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }

    public interface OnchildClickListener {
        public void onChildClick(RecyclerView parent, View view, int position, String data);
    }

    //长按事件
    public interface OnItemLongClickListener {
        public void onItemLongClick(View view, int position);
    }

}
