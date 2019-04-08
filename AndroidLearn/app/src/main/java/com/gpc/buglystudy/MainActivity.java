package com.gpc.buglystudy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.gpc.buglystudy.base.utils.activity.BaseActivity;
import com.tencent.bugly.crashreport.CrashReport;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.createBug_btn)
    Button createBugBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @OnClick(R.id.createBug_btn)
    public void onViewClicked() {
        Toast.makeText(MainActivity.this,"dfhsag",Toast.LENGTH_LONG).show();
    }
}
