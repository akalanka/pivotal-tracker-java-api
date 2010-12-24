package com.pivotaltracker.api.http;

import com.pivotaltracker.api.exceptions.TrackerException;
import java.net.URI;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public final class HttpGetCredentialsRequest extends HttpGetRequest
{

    private String username;
    private String password;

    public HttpGetCredentialsRequest(String username, String password) throws TrackerException {
        this.username = username;
        this.password = password;
    }

    @Override
    public HttpClient createHttpClient() {
        DefaultHttpClient httpClient = new DefaultHttpClient();
        httpClient.getCredentialsProvider().setCredentials(new AuthScope(null, 443),
                new UsernamePasswordCredentials(username, password));
        return httpClient;
    }

    @Override
    protected HttpGet createHttpGetMethod() throws TrackerException {
        return new HttpGet(createValidationURI());
    }

    private static URI createValidationURI() throws TrackerException {
        URICreator uriCreator = new URICreator(SupportedProtocol.HTTPS);
        uriCreator.addPathElement(USER_TOKEN_PATH);
        return uriCreator.createPivotalTrackerURI();
    }
}
