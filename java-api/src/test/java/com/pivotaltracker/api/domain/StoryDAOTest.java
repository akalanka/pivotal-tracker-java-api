/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pivotaltracker.api.domain;

import com.pivotaltracker.api.exceptions.TrackerException;
import com.pivotaltracker.api.domain.model.Story;
import com.pivotaltracker.api.domain.model.StoryGroup;
import java.util.EnumMap;
import java.util.Map;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import static com.pivotaltracker.api.domain.model.StoryGroup.SupportedStoryFilter;

/**
 *
 * @author eivar
 */
public class StoryDAOTest {
//TODO create a dummy service implementation for request data and not ask to real data on the cloud.

    private StoryDAO daoInstance;

    @BeforeClass
    @Parameters({"usertoken"})
    public void setUp(String userToken) {
        daoInstance = new StoryDAO();
        daoInstance.setUserToken(userToken);
    }

    @Test
    @Parameters({"project_id"})
    public void getStoryTest(int projectId) throws TrackerException {
        System.out.println("getStory");
        int testStoryId = 6997717;
        Story story = daoInstance.getStory(projectId, testStoryId);
        Assert.assertEquals("A user can request his credentials", story.getName());
        Assert.assertEquals("Eivar Montenegro", story.getRequestedBy());
    }

    @Test
    @Parameters({"project_id"})
    public void getAllStoriesTest(int projectId) throws TrackerException {
        System.out.println("getAllStories");
        StoryGroup storyGroup = daoInstance.getStories(projectId);
        Assert.assertEquals(storyGroup.getStories().size(), 6);
        Assert.assertEquals("Eivar Montenegro", storyGroup.getStories().get(0).getRequestedBy());
    }

    @Test
    @Parameters({"project_id"})
    public void getAllStoriesByManyFiltersTest(int projectId) {
        try {
            System.out.println("getAllStoriesByManyFilters");
            Map<SupportedStoryFilter, String> params = new EnumMap<SupportedStoryFilter, String>(SupportedStoryFilter.class);
            params.put(SupportedStoryFilter.type, "feature");//Currently is not posible to pass more than one filter.
            StoryGroup storyGroup = daoInstance.getStories(projectId, params);
            Assert.assertTrue(storyGroup.getStories().size() >= 1);
        } catch (TrackerException ex) {
            Assert.fail(ex.getMessage(), ex);
        }
    }

    @Test
    @Parameters({"project_id"})
    public void getAllStoriesByOneFilterTest(int projectId) {
        try {
            System.out.println("getAllStoriesByOneFilter");
            StoryGroup storyGroup = daoInstance.getStories(projectId, SupportedStoryFilter.label, "read story");
            Assert.assertTrue(storyGroup.getStories().size() >= 1);
            Assert.assertEquals(storyGroup.getStories().get(0).getLabels(), "read story");
        } catch (TrackerException ex) {
            Assert.fail(ex.getMessage(), ex);
        }
    }

    @Test
    @Parameters({"project_id"})
    public void getAllStoriesByLimitAndOffsetTest(int projectId) throws TrackerException {
        System.out.println("getAllStoriesByLimitAndOffset");
        final int limit = 5;
        final int offset = 1;
        StoryGroup storyGroup = daoInstance.getStories(projectId, limit, offset);
        Assert.assertTrue(storyGroup instanceof StoryGroup);
        Assert.assertEquals(storyGroup.getCount(), limit);
        Assert.assertTrue(storyGroup.getStories().get(0) instanceof Story);
    }

    @AfterClass
    public void cleanUp() {
        daoInstance = null;
    }
}
