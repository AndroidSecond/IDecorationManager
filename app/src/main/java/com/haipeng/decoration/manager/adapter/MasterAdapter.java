package com.haipeng.decoration.manager.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.haipeng.decoration.manager.R;
import com.haipeng.decoration.manager.model.MasterResponeseModel;
import com.haipeng.decoration.manager.model.UserResponseModel;

import java.util.List;

/**
 * Created by Administrator on 2017/7/19.
 */

public class MasterAdapter extends RecyclerView.Adapter<MasterViewHolder> {

    private LayoutInflater mInflater;
    private List<MasterResponeseModel> mDatas;
    private Context mContext;


    public MasterAdapter(Context context) {
        this.mContext = context;
        mInflater = LayoutInflater.from(mContext);

    }

    public void setDatas(List<MasterResponeseModel> mDatas){
        this.mDatas = mDatas;
        notifyDataSetChanged();
    }

    @Override
    public MasterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_query_master, parent, false);
        MasterViewHolder viewHolder = new MasterViewHolder(view);
//        viewHolder.tv = (TextView) view.findViewById(R.id.tv_recommend);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MasterViewHolder holder, int position) {
//        holder.ivImageView.setImageResource(R.mipmap.ic_launcher);
//        holder.tv.setText(mDatas.get(position));

    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }
}
