package com.softserve.logeek.config.merge;

import java.util.*;

public class ConfigMerger {

    public Map merge(List<Map> listForMerge) {
        Collections.reverse(listForMerge);
        Map resultMap = new HashMap();
        listForMerge.stream().forEach(it -> resultMap.putAll(it));
        return resultMap;
    }

}
