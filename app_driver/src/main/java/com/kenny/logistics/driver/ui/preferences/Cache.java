package com.kenny.logistics.driver.ui.preferences;

import android.content.Context;

import com.cocosw.favor.FavorAdapter;

/**
 * Created by Kenny on 2017/9/13.
 */

public class Cache {
    public static Account account;

    public static void Init(Context context){
        account = new FavorAdapter.Builder(context).build().create(Account.class);
    }
}
