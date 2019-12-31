package com.kenny.logistics.service.preferences;

import com.cocosw.favor.AllFavor;
import com.cocosw.favor.Default;

/**
 * Created by Kenny on 2017/9/13.
 */
@AllFavor
public interface AppProferences {

    @Default("")
    String getToken();
    void setToken(String token);

    @Default("")
    String getPhone();
    void setPhone(String phone);

    @Default("shipper")
    String getType();
    void setType(String type);

    @Default("false")
    Boolean getFirst();
    void setFirst(Boolean first);
}