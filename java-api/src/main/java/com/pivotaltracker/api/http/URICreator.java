/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pivotaltracker.api.http;

import com.pivotaltracker.api.exceptions.TrackerException;
import com.pivotaltracker.api.domain.APIConstants;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.http.NameValuePair;
import org.apache.http.annotation.ThreadSafe;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

/**
 *
 * @author eivar
 */
@ThreadSafe
public class URICreator implements APIConstants
{

    private SupportedProtocol protocol;
    private List<String> pathElements;
    private Map<String, String> optionalQueryParameters;

    public URICreator(SupportedProtocol protocol) {
        this.protocol = protocol;
        optionalQueryParameters = new HashMap<String, String>();
        pathElements = new ArrayList<String>();
    }

    public URICreator(URICreator uriCreator, SupportedProtocol newProtocol) {
        this(newProtocol);
        this.optionalQueryParameters.putAll(uriCreator.getOptionalQueryParameters());
        this.pathElements.addAll(uriCreator.pathElements);
    }

    public SupportedProtocol getProtocol() {
        return protocol;
    }

    public void clearPathElements() {
        pathElements.clear();
    }

    public void addPathElements(Object... pathElements) {
        for (Object element : pathElements) {
            addPathElement(element);
        }
    }

    public void addPathElement(Object pathElement) {
        this.pathElements.add(String.valueOf(pathElement));
    }

    public void addOptionalQueryParameter(String paramName, Object paramValue) {
        this.optionalQueryParameters.put(paramName, String.valueOf(paramValue));
    }

    /**
     * @return Read-only view of the current path elements defined.
     */
    public List<String> getPathElements() {
        return Collections.unmodifiableList(this.pathElements);
    }

    /**
     * @return Read-only view of the current query string elements defined.
     */
    public Map<String, String> getOptionalQueryParameters() {
        return Collections.unmodifiableMap(optionalQueryParameters);
    }

    /**
     * create a Pivotal Tracker URI using the path elements and query string parameters. 
     * @return URI instance pointing to pivotal tracker api internet address.
     * @throws TrackerException
     */
    public URI createPivotalTrackerURI() throws TrackerException {
        try {
            return URIUtils.createURI(protocol.toString(), PIVOTAL_TRACKER_HOST,
                    -1, createRequestPath(), createQueryString(), null);
        } catch (URISyntaxException ex) {
            throw new TrackerException("URI construction failure", ex);
        }
    }

    private String createRequestPath() {
        StringBuilder builder = new StringBuilder();
        builder.append(URL_SEPARATOR).append(API_LOCATION_PATH).append(URL_SEPARATOR);
        if (pathElements.size() >= 1) {
            int superiorLimit = pathElements.size() - 1;
            for (int i = 0; i < superiorLimit; i++) {
                builder.append(pathElements.get(i)).append(URL_SEPARATOR);
            }
            builder.append(pathElements.get(superiorLimit));
        }
        return builder.toString();
    }

    private String createQueryString() {
        List<NameValuePair> queryParams = createNameValueList();
        String queryString = URLEncodedUtils.format(queryParams, "UTF-8");
        if (queryParams.size() <= 0) {
            queryString = "";
        }
        queryString = queryString.replace("+", "%20");
        return queryString;
    }

    private List<NameValuePair> createNameValueList() {
        List<NameValuePair> qparams = new ArrayList<NameValuePair>();
        for (String key : optionalQueryParameters.keySet()) {
            qparams.add(new BasicNameValuePair(key, String.valueOf(optionalQueryParameters.get(key))));
        }
        return qparams;
    }
}
