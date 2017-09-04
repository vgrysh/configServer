package com.softserve.logeek.config.request;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.http.HttpResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ResponseParser {

    public JsonObject parse(HttpResponse response) throws IOException {
        BufferedReader rd = new BufferedReader
                (new InputStreamReader(response.getEntity().getContent()));
        String line = "";
        StringBuilder textView = new StringBuilder();
        while ((line = rd.readLine()) != null) {
            textView.append(line);
        }
        JsonParser parser = new JsonParser();
        return (JsonObject) parser.parse(textView.toString());
    }
}
