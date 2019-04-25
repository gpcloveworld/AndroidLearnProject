package com.gpc.buglystudy;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.hotfixlibrary.utils.Contants;
import com.example.hotfixlibrary.utils.FileUtils;
import com.example.hotfixlibrary.utils.HotFixUtils;
import com.gpc.buglystudy.base.utils.activity.BaseActivity;
import com.gpc.buglystudy.err.ParamSort;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;

public class HotfixActivity extends BaseActivity {
    @BindView(R.id.createBug_btn)
    Button createBugBtn;
    @BindView(R.id.fixBug_btn)
    Button fixBugBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_hotfix;
    }


    @OnClick({R.id.createBug_btn, R.id.fixBug_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.createBug_btn:
                ParamSort paramSort = new ParamSort();
                paramSort.math(this);
                break;
            case R.id.fixBug_btn:
                fix();
                break;
        }
    }

    private void fix() {
//将下载的修复包复制到私有目录，然后再做解压工作
        File sourceFile = new File(Environment.getExternalStorageDirectory(), Contants.DEX_PREFIX);
        File targetFile = getDir(Contants.DEX_DIR, Context.MODE_PRIVATE);

        //如果存在之前修复过的dex需要删除
        if (targetFile.exists()) {
            targetFile.delete();
        }
        boolean iret = FileUtils.copyDirectory(sourceFile, targetFile);
        if (iret){
            Toast.makeText(this, "复制dex文件完成", Toast.LENGTH_SHORT).show();
        }

        //开始修复dex
        HotFixUtils.loadFixedDex(this);
    }
}
