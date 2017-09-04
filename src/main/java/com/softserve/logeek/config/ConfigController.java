package com.softserve.logeek.config;

import com.google.gson.JsonObject;
import com.softserve.logeek.config.converter.JSONConverter;
import com.softserve.logeek.config.merge.ConfigMerger;
import com.softserve.logeek.config.request.ConfigRequester;
import com.softserve.logeek.config.request.ResponseParser;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.*;

@RestController
public class ConfigController {

    @Autowired
    ConfigRequester requester;
    @Autowired
    ResponseParser parser;
    @Autowired
    ConfigMerger merger;
    @Autowired
    JSONConverter converter;

    @RequestMapping(value = "/getConfig/{name}/{profiles}/{version}", method = RequestMethod.GET)
    public Map getVersioned(@PathVariable("name") String name, @PathVariable("profiles") String profiles, @PathVariable("version") String version) throws IOException {
        return get(name, profiles, version);
    }

    @RequestMapping(value = "/getConfig/{name}/{profiles}", method = RequestMethod.GET)
    public Map getFromMaster(@PathVariable("name") String name, @PathVariable("profiles") String profiles) throws IOException {
        return get(name, profiles, null);
    }

    private Map get(String name, String profiles, String version) throws IOException {
        JsonObject jsonObject = getJsonConfigs(name, profiles, version);
        List<Map> listForMerge = converter.convertToMap(jsonObject);
        return merger.merge(listForMerge);
    }

    private JsonObject getJsonConfigs(String name, String profiles, String version) throws IOException {
        HttpResponse response = requester.get(name, profiles, version);
        return parser.parse(response);
    }

}
