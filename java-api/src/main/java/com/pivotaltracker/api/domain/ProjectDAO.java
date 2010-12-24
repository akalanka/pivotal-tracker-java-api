/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pivotaltracker.api.domain;

import com.pivotaltracker.api.exceptions.TrackerException;
import com.pivotaltracker.api.http.HttpTrackerDAO;
import com.pivotaltracker.api.domain.model.IterationGroup;
import com.pivotaltracker.api.domain.model.Membership;
import com.pivotaltracker.api.domain.model.MembershipGroup;
import com.pivotaltracker.api.domain.model.Project;
import com.pivotaltracker.api.domain.model.ProjectGroup;
import com.pivotaltracker.api.http.HttpGetDataRequest;
import com.pivotaltracker.api.http.HttpRequest;
import com.pivotaltracker.api.http.URICreator;

/**
 *
 * @author eivar
 */
public class ProjectDAO extends HttpTrackerDAO
{

    public Project getProject(int projectId) throws TrackerException {
        HttpRequest request = new HttpGetDataRequest(getUserToken(), createURI(PROJECTS_PATH, projectId));
        return (Project) getTrackerEntity(request);
    }

    public ProjectGroup getProjects() throws TrackerException {
        HttpRequest request = new HttpGetDataRequest(getUserToken(), createURI(PROJECTS_PATH));
        return (ProjectGroup) getTrackerEntity(request);
    }

    public MembershipGroup getMemberships(int projectId) throws TrackerException {
        HttpRequest request = new HttpGetDataRequest(getUserToken(), createURI(PROJECTS_PATH,
                projectId, MEMBERSHIPS_PATH));
        return (MembershipGroup) getTrackerEntity(request);
    }

    public Membership getMembership(int projectId, int membershipId) throws TrackerException {
        HttpRequest request = new HttpGetDataRequest(getUserToken(), createURI(PROJECTS_PATH, projectId,
                MEMBERSHIPS_PATH, membershipId));
        return (Membership) getTrackerEntity(request);
    }

    /**
     * Get stories by iteration. The example below shows how to retrieve all iterations, with stories.
     * @param project
     * @return
     */
    public IterationGroup getIterations(int projectId) throws TrackerException {
        HttpRequest request = new HttpGetDataRequest(getUserToken(), createURI(PROJECTS_PATH, projectId,
                ITERATIONS_PATH));
        return (IterationGroup) getTrackerEntity(request);
    }

    /**
     * From PivotalTracker API:
     * You can specify offset and limit as parameters (except for current,
     * because there is always only one current iteration).
     * The limit parameter controls the max number of iterations returned,
     * and the offset parameter controls the number of iterations to skip from the beginning of the result.
     * To only return iteration 2 and iteration 3 from our project pass:
     * offset = 1
     * limit = 2
     * For done iterations, offset should be negative, relative to the most recent done iteration.
     * @param project
     * @param limit
     * @param offset
     * @return
     */
    public IterationGroup getIterations(int projectId, int limit, int offset) throws TrackerException {
        URICreator uriCreator = new URICreator(this.getProtocol());
        uriCreator.addPathElements(PROJECTS_PATH, projectId, ITERATIONS_PATH);
        uriCreator.addOptionalQueryParameter(OFFSET_PARAMETER, offset);
        if (limit > 0) { 
            uriCreator.addOptionalQueryParameter(LIMIT_PARAMETER, limit);
        }
        HttpRequest request = new HttpGetDataRequest(getUserToken(), uriCreator.createPivotalTrackerURI());
        return (IterationGroup) getTrackerEntity(request);

    }

    public IterationGroup getIterationsByGroup(int projectId, IterationGroup.GroupName groupName) throws TrackerException {
        HttpRequest request = new HttpGetDataRequest(getUserToken(), createURI(PROJECTS_PATH, projectId,
                ITERATIONS_PATH, groupName));
        return (IterationGroup) getTrackerEntity(request);
    }
}
