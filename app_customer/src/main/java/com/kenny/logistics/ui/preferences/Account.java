package com.kenny.logistics.ui.preferences;

import com.cocosw.favor.AllFavor;
import com.cocosw.favor.Default;

/**
 * Created by Kenny on 2017/9/13.
 */
@AllFavor
public interface Account {
    void setUserName(String userName);

    @Default("")
    String getUserName();

    void setPassword(String password);

    @Default("")
    String getPassword();

    void setToken(String token);

    @Default("")
    String getToken();
}