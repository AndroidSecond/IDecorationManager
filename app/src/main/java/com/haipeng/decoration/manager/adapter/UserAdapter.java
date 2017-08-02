package com.haipeng.decoration.manager.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.haipeng.decoration.manager.R;
import com.haipeng.decoration.manager.model.UserResponseModel;

import java.util.List;

/**
 * Created by Administrator on 2017/7/19.
 */

public class UserAdapter extends RecyclerView.Adapter<UserViewHolder> {

    private LayoutInflater mInflater;
    private List<UserResponseModel> mDatas;
    private Context mContext;


    public UserAdapter(Context context) {
        this.mContext = context;
        mInflater = LayoutInflater.from(mContext);

    }

    public void setDatas(List<UserResponseModel> mDatas) {
        this.mDatas = mDatas;
        notifyDataSetChanged();
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_query_master, parent, false);
        UserViewHolder viewHolder = new UserViewHolder(view);
        viewHolder.tvName = (TextView) view.findViewById(R.id.name);
        viewHolder.tvPhone = (TextView) view.findViewById(R.id.phone);
        viewHolder.tvEmail = (TextView) view.findViewById(R.id.email);
        viewHolder.tvAddress = (TextView) view.findViewById(R.id.address);
//        viewHolder.tv = (TextView) view.findViewById(R.id.tv_recommend);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
//        holder.ivImageView.setImageResource(R.mipmap.ic_launcher);
//        holder.tv.setText(mDatas.get(position));
        holder.tvName.setText(mDatas.get(position).getName());
        holder.tvPhone.setText(mDatas.get(position).getPhone());
        holder.tvEmail.setText(mDatas.get(position).getEmail());
        holder.tvAddress.setText(mDatas.get(position).getAddress());
    }

    @Override
    public int getItemCount() {
        return null == mDatas ? 0 : mDatas.size();
    }
}
