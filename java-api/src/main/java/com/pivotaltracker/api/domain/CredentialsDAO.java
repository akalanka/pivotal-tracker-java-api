/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pivotaltracker.api.domain;

import com.pivotaltracker.api.http.HttpRequest;
import com.pivotaltracker.api.http.HttpGetCredentialsRequest;
import com.pivotaltracker.api.exceptions.TrackerException;
import com.pivotaltracker.api.domain.model.UserToken;
import com.pivotaltracker.api.http.HttpResponseParser;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.InputSource;

/**
 *
 * @author eivar
 */
public class CredentialsDAO implements TrackerDAO, APIConstants
{

    protected final Logger LOGGER = LoggerFactory.getLogger(CredentialsDAO.class);

    public String getUserToken(String username, String password) throws TrackerException {
        HttpRequest request = createCredentialsRequest(username, password);
        UserToken userToken = (UserToken) getTrackerEntity(request);

        return userToken.getGuid();
    }

    private HttpRequest createCredentialsRequest(String username, String password) throws TrackerException {
        return new HttpGetCredentialsRequest(username, password);
    }

    @Override
    public Object getTrackerEntity(HttpRequest request) throws TrackerException {
        return new HttpResponseParser(request).parseXMLResponse();
    }
}
