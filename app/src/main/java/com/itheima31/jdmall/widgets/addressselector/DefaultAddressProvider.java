package com.itheima31.jdmall.widgets.addressselector;


import com.itheima31.jdmall.widgets.addressselector.global.Database;
import com.itheima31.jdmall.widgets.addressselector.model.City;
import com.itheima31.jdmall.widgets.addressselector.model.County;
import com.itheima31.jdmall.widgets.addressselector.model.Province;

import java.util.ArrayList;
import java.util.List;


public class DefaultAddressProvider implements AddressProvider {
    @Override
    public void provideProvinces(Database database, final AddressReceiver<Province> addressReceiver) {
        final List<Province> provinceQueryList = database.getAllProvices();
        addressReceiver.send(new ArrayList<>(provinceQueryList));
    }

    @Override
    public void provideCitiesWith(Database database,int provinceId, final AddressReceiver<City> addressReceiver) {
        final List<City> cityQueryList = database.getAllCityByProviceId(provinceId);
        addressReceiver.send(new ArrayList<>(cityQueryList));
    }

    @Override
    public void provideCountiesWith(Database database,int cityId, final AddressReceiver<County> addressReceiver) {
        final List<County> countyQueryList = database.getAllCountyByCityId(cityId);
        addressReceiver.send(new ArrayList<>(countyQueryList));
    }

}
