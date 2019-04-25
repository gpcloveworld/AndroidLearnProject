package com.gpc.buglystudy.err;

import android.content.Context;
import android.widget.Toast;

/**
 * author : Administrator
 * date : 2019/4/21 0021 19:22
 * description :
 */
public class ParamSort {

    public   void   math(Context  context){
        int  a=10;
        int  b=0;
        Toast.makeText(context, "math>>"+(a/b), Toast.LENGTH_SHORT).show();
    }
}
