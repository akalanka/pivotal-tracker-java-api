package com.pivotaltracker.api.http;

import com.pivotaltracker.api.exceptions.TrackerException;
import com.pivotaltracker.api.domain.APIConstants;
import java.io.IOException;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class HttpGetRequest implements HttpRequest, APIConstants
{

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpGetRequest.class);

    @Override
    public HttpResponse executeRequest() throws TrackerException {
        try {
            return executeHttpGet();
        } catch (ClientProtocolException cpex) {
            throw new TrackerException("Protocol error", cpex);
        } catch (IOException ioex) {
            throw new TrackerException("Can not read the server response", ioex);
        }
    }

    private HttpResponse executeHttpGet() throws ClientProtocolException, IOException, TrackerException {
        HttpClient httpClient = null;
        try {
            httpClient = createHttpClient();
            HttpGet httpGetMethod = createHttpGetMethod();
            LOGGER.debug("Executing request: {}", httpGetMethod.getRequestLine());
            HttpResponse response = createHttpClient().execute(httpGetMethod);
            checkErrorsOnResponse(response);
            return response;
        } finally {
            if (httpClient != null) {
                httpClient.getConnectionManager().shutdown();
            }
        }
    }

    protected void checkErrorsOnResponse(HttpResponse response) throws TrackerException {
        int statusCode = response.getStatusLine().getStatusCode();
        if (statusCode == HttpStatus.SC_UNAUTHORIZED) {
            LOGGER.debug("Response contains HttpStatus.SC_UNAUTHORIZED({})", HttpStatus.SC_UNAUTHORIZED);
            throw new TrackerException("Access denied.");
        }
        if (statusCode == HttpStatus.SC_INTERNAL_SERVER_ERROR) {
            LOGGER.debug("Internal server error reported. This is most likely caused by an invalid request or wrong query string parameters ");
        }
        if (statusCode == HttpStatus.SC_OK && response.getEntity().getContentLength() < 1) {
            LOGGER.debug("Response contains HttpStatus.SC_OK({}) but is empty", HttpStatus.SC_OK);
            throw new TrackerException("Invalid or truncated response.");
        }
    }

    protected abstract HttpClient createHttpClient() throws TrackerException;

    protected abstract HttpGet createHttpGetMethod() throws TrackerException;
}
