package com.pivotaltracker.api.http;

import com.pivotaltracker.api.exceptions.TrackerException;
import org.apache.http.HttpResponse;

public interface HttpRequest
{

    HttpResponse executeRequest() throws TrackerException;
}
