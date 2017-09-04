package com.softserve.logeek.config.converter;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.StreamSupport;

public class JSONConverter {

    private static final String PROPERTY_SOURCES = "propertySources";
    private static final String SOURCE = "source";

    public List<Map> convertToMap(JsonObject jsonObject) {
        List<Map> listForMerge = new ArrayList<>();
        jsonObject.entrySet().stream().filter(it -> it.getKey().equals(PROPERTY_SOURCES)).findFirst().ifPresent(it ->
                merge((JsonArray) it.getValue(), listForMerge));
        return listForMerge;
    }


    private void merge(JsonArray array, List<Map> listForMerge) {
        if (array != null) {

            Iterable iterable = () -> array.iterator();
            StreamSupport.stream(iterable.spliterator(), false).forEach(it -> addConfigProfileValues((JsonObject) it, listForMerge));
        }

    }

    private void addConfigProfileValues(JsonElement element, List<Map> listForMerge) {
        JsonObject it = (JsonObject) ((JsonObject) element).get(SOURCE);
        if (it != null) {
            Map map = new HashMap();
            listForMerge.add(map);
            it.entrySet().forEach(t -> addConfigurationProperty(t, map));
        }
    }

    private void addConfigurationProperty(Map.Entry<String, JsonElement> element, Map map) {
        map.put(element.getKey().toString(), element.getValue().toString().replaceAll("\"", ""));
    }
}
