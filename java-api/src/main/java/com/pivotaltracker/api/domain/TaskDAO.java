/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pivotaltracker.api.domain;

import com.pivotaltracker.api.http.HttpTrackerDAO;
import com.pivotaltracker.api.domain.model.Task;
import com.pivotaltracker.api.domain.model.TaskGroup;
import com.pivotaltracker.api.http.HttpGetDataRequest;
import com.pivotaltracker.api.http.HttpRequest;
import com.pivotaltracker.api.exceptions.TrackerException;
import java.net.URI;

/**
 *
 * @author eivar
 */
public class TaskDAO extends HttpTrackerDAO
{

    public Task getStory(int projectId, int storyId, int taskId) throws TrackerException {
        URI uri = createURI(PROJECTS_PATH, projectId, STORIES_PATH, storyId, TASKS_PATH, taskId);
        HttpRequest request = new HttpGetDataRequest(getUserToken(), uri);
        return (Task) getTrackerEntity(request);
    }

    public TaskGroup getTasks(int projectId, int storyId) throws TrackerException {
        URI uri = createURI(PROJECTS_PATH, projectId, STORIES_PATH, storyId, TASKS_PATH);
        HttpRequest request = new HttpGetDataRequest(getUserToken(), uri);
        return (TaskGroup) getTrackerEntity(request);
    }
}
