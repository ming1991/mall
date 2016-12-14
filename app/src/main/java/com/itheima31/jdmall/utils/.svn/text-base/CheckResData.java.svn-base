package com.itheima31.jdmall.utils;

import com.itheima31.jdmall.base.LoadingPager;

import java.util.List;
import java.util.Map;

public class CheckResData {
    public static LoadingPager.LoadedResultEnum checkResData(Object resObj) {
        if (resObj == null) {
            return LoadingPager.LoadedResultEnum.EMPTY;
        }

        if (resObj instanceof List) {
            if (((List) resObj).size() == 0) {
                return LoadingPager.LoadedResultEnum.EMPTY;
            }
        }
        if (resObj instanceof Map) {
            if (((Map) resObj).size() == 0) {
                return LoadingPager.LoadedResultEnum.EMPTY;
            }
        }

        return LoadingPager.LoadedResultEnum.SUCCESS;
    }
}
