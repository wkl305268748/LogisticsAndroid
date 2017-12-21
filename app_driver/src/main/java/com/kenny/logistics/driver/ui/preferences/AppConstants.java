package com.kenny.logistics.driver.ui.preferences;

import com.cocosw.favor.AllFavor;
import com.cocosw.favor.Default;

/**
 * Created by Kenny on 2017/9/13.
 */
@AllFavor
public interface AppConstants {
    void setFirst(Boolean first);

    @Default("false")
    Boolean getFirst();
}