package com.softserve.logeek.config.request;


import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;

public class ConfigRequester {

    private static final String HTTP_LOCALHOST = "http://localhost:";
    private static final String SlASH = "/";

    @Value("${server.port}")
    private String port;

    public HttpResponse get(String name, String profiles, String version) throws IOException {
        String uri = buildURI(name, profiles, version);
        RequestBuilder builder = RequestBuilder.get(uri);
        HttpClient client = new DefaultHttpClient();
        return client.execute(builder.build());
    }

    private String buildURI(String name, String profiles, String version) {
        String uri = HTTP_LOCALHOST + port + SlASH + name + SlASH + profiles;
        if (version != null) {
            uri += SlASH + version;
        }
        return uri;
    }
}
