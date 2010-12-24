/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pivotaltracker.api.domain;

import com.pivotaltracker.api.exceptions.TrackerException;
import com.pivotaltracker.api.http.HttpRequest;

/**
 *
 * @author eivar
 */
public interface TrackerDAO {
    Object getTrackerEntity(HttpRequest request) throws TrackerException;

}
