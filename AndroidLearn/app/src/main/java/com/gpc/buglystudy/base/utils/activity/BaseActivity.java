package com.gpc.buglystudy.base.utils.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.gpc.buglystudy.R;
import com.gpc.buglystudy.base.utils.ExitAppUtils;

import butterknife.ButterKnife;

public abstract class BaseActivity  extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        ExitAppUtils.getInstance().addActivity(this);
    }

    protected  abstract int getLayoutId() ;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ExitAppUtils.getInstance().delActivity(this);
    }
}
