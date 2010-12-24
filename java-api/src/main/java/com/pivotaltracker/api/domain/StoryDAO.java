/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pivotaltracker.api.domain;

import com.pivotaltracker.api.http.HttpGetDataRequest;
import com.pivotaltracker.api.exceptions.TrackerException;
import com.pivotaltracker.api.http.HttpTrackerDAO;
import com.pivotaltracker.api.domain.model.Story;
import com.pivotaltracker.api.domain.model.StoryGroup;
import java.util.EnumMap;
import static com.pivotaltracker.api.domain.model.StoryGroup.SupportedStoryFilter;
import com.pivotaltracker.api.http.HttpRequest;
import com.pivotaltracker.api.http.URICreator;
import java.util.Map;

/**
 *
 * @author eivar
 */
public class StoryDAO extends HttpTrackerDAO
{

    /**
     * Get a single story on a project
     * @param projectId
     * @param storyId
     * @return a single story
     * @throws TrackerException
     */
    public Story getStory(int projectId, int storyId) throws TrackerException {
        HttpRequest request = new HttpGetDataRequest(getUserToken(), createURI(PROJECTS_PATH, projectId,
                STORIES_PATH, storyId));
        return (Story) getTrackerEntity(request);
    }

    /**
     * This command retrieves all stories for a project.
     * @param projectId
     * @return a group of stories
     * @throws TrackerException
     */
    public StoryGroup getStories(int projectId) throws TrackerException {
        HttpRequest request = new HttpGetDataRequest(getUserToken(), createURI(PROJECTS_PATH, projectId,
                STORIES_PATH));
        return (StoryGroup) getTrackerEntity(request);
    }

    /**
     * You can paginate through stories by optionally passing a limit and offset.
     * @param projectId
     * @param limit
     * @param offset
     * @return
     * @throws TrackerException
     */
    public StoryGroup getStories(int projectId, int limit, int offset) throws TrackerException {
        URICreator uriCreator = new URICreator(getProtocol());
        uriCreator.addPathElements(PROJECTS_PATH, projectId, STORIES_PATH);
        uriCreator.addOptionalQueryParameter(OFFSET_PARAMETER, offset);
        if (limit > 0) {
            uriCreator.addOptionalQueryParameter(LIMIT_PARAMETER, limit);
        }
        HttpRequest request = new HttpGetDataRequest(getUserToken(), uriCreator.createPivotalTrackerURI());
        return (StoryGroup) getTrackerEntity(request);
    }

    /**
     * Get stories by many filters.
     * If no filter is provided (empty map) then this method returns all project's stories
     * (Be aware that this is not working at the moment by unknown reasons to me, Eivar.)
     * @param projectId
     * @param filterParams not null filters Map.
     * @return a group of stories
     * @throws TrackerException
     */
    @Deprecated
    public StoryGroup getStories(int projectId, Map<SupportedStoryFilter, String> filterParams) throws TrackerException {
        //FIXME retrieving stories by more than one filter.
        URICreator uriCreator = new URICreator(this.getProtocol());
        uriCreator.addPathElements(PROJECTS_PATH, projectId, STORIES_PATH);
        if (!filterParams.isEmpty()) {
            uriCreator.addOptionalQueryParameter(FILTER_PARAMETER, createFilterParamsString(filterParams));
        }
        HttpRequest request = new HttpGetDataRequest(getUserToken(), uriCreator.createPivotalTrackerURI());
        return (StoryGroup) getTrackerEntity(request);
    }

    /**
     * Get stories by one filter.
     * @param projectId
     * @param storyFilter
     * @return a group of stories.
     * @throws TrackerException
     */
    public StoryGroup getStories(int projectId, SupportedStoryFilter filterName, String filterValue) throws TrackerException {
        Map<SupportedStoryFilter, String> filterParams = new EnumMap<SupportedStoryFilter, String>(SupportedStoryFilter.class);
        filterParams.put(filterName, filterValue);
        URICreator uriCreator = new URICreator(getProtocol());
        uriCreator.addPathElements(PROJECTS_PATH, projectId, STORIES_PATH);
        uriCreator.addOptionalQueryParameter(FILTER_PARAMETER, createFilterParamsString(filterParams));
        HttpRequest request = new HttpGetDataRequest(getUserToken(), uriCreator.createPivotalTrackerURI());
        return (StoryGroup) getTrackerEntity(request);
    }

    private String createFilterParamsString(Map<SupportedStoryFilter, String> filterParams) throws TrackerException {
        StringBuilder builder = new StringBuilder();
        for (SupportedStoryFilter filterKey : filterParams.keySet()) {
            builder.append(filterKey).append(":");
            builder.append(addQuoteIfNecesary(filterParams.get(filterKey))).append(" ");
        }
        return builder.toString().trim();
    }

    private String addQuoteIfNecesary(String filterValue) {
        return filterValue.contains(" ") || filterValue.isEmpty() ? "\"" + filterValue + "\"" : filterValue;
    }
}
