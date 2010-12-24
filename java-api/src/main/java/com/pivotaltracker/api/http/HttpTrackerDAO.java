/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pivotaltracker.api.http;

import com.pivotaltracker.api.domain.APIConstants;
import com.pivotaltracker.api.exceptions.TrackerException;
import com.pivotaltracker.api.domain.TrackerDAO;
import com.pivotaltracker.api.http.HttpResponseParser;
import com.pivotaltracker.api.http.HttpRequest;
import com.pivotaltracker.api.http.URICreator;
import java.net.URI;

/**
 *
 * @author eivar
 */
public abstract class HttpTrackerDAO implements TrackerDAO, APIConstants
{

    private SupportedProtocol protocol = SupportedProtocol.HTTPS;
    private String userToken;

    @Override
    public Object getTrackerEntity(HttpRequest request) throws TrackerException
    {
        return new HttpResponseParser(request).parseXMLResponse();
    }

    public void setProtocol(SupportedProtocol protocol)
    {
        this.protocol = protocol;
    }

    public SupportedProtocol getProtocol()
    {
        return protocol;
    }

    public URI createURI(Object... pathElements) throws TrackerException
    {
        URICreator uriCreator = new URICreator(this.getProtocol());
        uriCreator.addPathElements(pathElements);
        return uriCreator.createPivotalTrackerURI();
    }

    public String getUserToken()
    {
        return userToken;
    }

    public void setUserToken(String userToken)
    {
        this.userToken = userToken;
    }
}
