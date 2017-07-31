package com.haipeng.decoration.manager.constant;

import com.haipeng.decoration.manager.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

/**
 * Created by Administrator on 2017/7/29.
 */

public class CommonConstant {

    public static String UploadQiniuToken = "";

    public DisplayImageOptions galleryOptions = new DisplayImageOptions.Builder()
            .showImageOnLoading(R.mipmap.ic_launcher) // 设置图片下载期间显示的图片
            .resetViewBeforeLoading(true)  // default 设置图片在加载前是否重置、复位
            .imageScaleType(ImageScaleType.IN_SAMPLE_INT) // default 设置图片以如何的编码方式显示
            .cacheInMemory(true)
            .build();
}
