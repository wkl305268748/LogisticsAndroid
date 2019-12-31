package com.kenny.logistics.ui.component;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;

import com.kenny.logistics.R;

/**
 * Created by Kenny on 2017/12/28.
 */

public class DialogLoading extends Dialog {

    public DialogLoading(@NonNull Context context) {
        super(context);
        /**设置对话框背景透明*/
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setContentView(R.layout.dialog_loading);
        setCanceledOnTouchOutside(false);
    }
}
