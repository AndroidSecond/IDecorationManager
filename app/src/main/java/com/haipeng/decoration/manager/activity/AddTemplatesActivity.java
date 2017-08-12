package com.haipeng.decoration.manager.activity;

import android.app.Activity;
import android.os.Bundle;

import com.haipeng.decoration.manager.R;
import com.haipeng.decoration.manager.listener.OnHttpPostListener;

import de.greenrobot.event.EventBus;

public class AddTemplatesActivity extends Activity implements OnHttpPostListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vendor);
        EventBus.getDefault().register(this);
    }

    @Override
    public void responsePostSuccess(int varl, String jsonStr) {

    }

    @Override
    public void responsePostFail(int varl) {

    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
