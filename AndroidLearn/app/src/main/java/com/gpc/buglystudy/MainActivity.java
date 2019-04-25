package com.gpc.buglystudy;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.gpc.buglystudy.base.utils.activity.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {


    @BindView(R.id.toBug_btn)
    Button toBugBtn;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext=this;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }


    @OnClick(R.id.toBug_btn)
    public void onViewClicked() {
        Intent intent = new Intent(mContext, HotfixActivity.class);
        startActivity(intent);
    }
}
