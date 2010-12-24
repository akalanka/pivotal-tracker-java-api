/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pivotaltracker.api.http;

import com.pivotaltracker.api.exceptions.TrackerException;
import com.pivotaltracker.api.domain.APIUtil;
//import com.wutka.jox.JOXBeanInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author eivar
 */
public class HttpResponseParser
{

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpResponseParser.class);
    private final HttpRequest trackerRequest;

    public HttpResponseParser(HttpRequest trackerRequest)
    {
        this.trackerRequest = trackerRequest;
    }

    public Object parseXMLResponse() throws TrackerException
    {
        LOGGER.debug("parse xml entity");
        return APIUtil.parseEntityFromXML(openRequestInputStream());
    }

    private InputStream openRequestInputStream() throws TrackerException
    {
        try {
            LOGGER.debug("processing pivotal tracker data.");
            return processResponse();
        } catch (IOException ex) {
            throw new TrackerException("Problem processing the response", ex);
        }
    }

    private InputStream processResponse() throws TrackerException, IOException
    {
        LOGGER.debug("requesting pivotal tracker data.");
        HttpResponse response = trackerRequest.executeRequest();
        return getResponeContent(response);
    }

    private InputStream getResponeContent(HttpResponse response) throws IOException, IllegalStateException
    {
        return response.getEntity().getContent();
    }
}
