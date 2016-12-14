package com.itheima31.jdmall.widgets.addressselector;


import com.itheima31.jdmall.widgets.addressselector.global.Database;
import com.itheima31.jdmall.widgets.addressselector.model.City;
import com.itheima31.jdmall.widgets.addressselector.model.County;
import com.itheima31.jdmall.widgets.addressselector.model.Province;

import java.util.List;


public interface AddressProvider {
    void provideProvinces(Database database, AddressReceiver<Province> addressReceiver);
    void provideCitiesWith(Database database, int provinceId, AddressReceiver<City> addressReceiver);
    void provideCountiesWith(Database database, int cityId, AddressReceiver<County> addressReceiver);

    interface AddressReceiver<T> {
        void send(List<T> data);
    }
}