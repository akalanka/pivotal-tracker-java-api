/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pivotaltracker.api.domain;

import com.pivotaltracker.api.http.HttpRequest;

/**
 *
 * @author eivar
 */
public interface APIConstants
{

    /**
     * URL sections
     */
    static final String URL_SEPARATOR = "/", PROCOTOL_SEPARATOR = "://";
    
    /**
     * API url parts
     */
    static final String PIVOTAL_TRACKER_HOST = "www.pivotaltracker.com",
            API_LOCATION_PATH = "services/v3", PROJECTS_PATH = "projects",
            MEMBERSHIPS_PATH = "memberships", ITERATIONS_PATH = "iterations",
            STORIES_PATH = "stories", TASKS_PATH="tasks",
            USER_TOKEN_PATH = "tokens/active"; // NOI18N
    
    /**
     * API url parameters
     */
    static final String OFFSET_PARAMETER = "offset", LIMIT_PARAMETER = "limit",
            FILTER_PARAMETER = "filter"; // NOI18N
    
    /**
     * API user token header
     */
    static final String  USER_TOKEN_PARAMETER_NAME = "X-TrackerToken"; // NOI18N

}
