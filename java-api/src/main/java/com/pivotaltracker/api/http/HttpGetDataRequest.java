package com.pivotaltracker.api.http;

import com.pivotaltracker.api.exceptions.TrackerException;
import java.net.URI;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public final class HttpGetDataRequest extends HttpGetRequest
{

    private String userToken;
    private URI uri;

    public HttpGetDataRequest(String userToken, URI uri) {
        this.userToken = userToken;
        this.uri = uri;
    }

    public String getUserToken() {
        return userToken;
    }

    public URI getUri() {
        return uri;
    }

    @Override
    protected HttpClient createHttpClient() throws TrackerException {
        return new DefaultHttpClient();
    }

    @Override
    public HttpGet createHttpGetMethod() throws TrackerException {
        HttpGet httpGet = new HttpGet(uri);
        httpGet.setHeader(USER_TOKEN_PARAMETER_NAME, userToken);
        return httpGet;
    }
}
