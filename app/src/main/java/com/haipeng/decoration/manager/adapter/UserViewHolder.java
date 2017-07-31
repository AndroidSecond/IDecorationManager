package com.haipeng.decoration.manager.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/7/19.
 */

public class UserViewHolder extends RecyclerView.ViewHolder{

    public UserViewHolder(View itemView) {
        super(itemView);
    }

    public ImageView ivImageView;
    public TextView tvName;
    public TextView tvPhone;
    public TextView tvEmail;
    public TextView tvAddress;

}
