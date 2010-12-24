/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pivotaltracker.api.domain;

import com.pivotaltracker.api.domain.model.Task;
import com.pivotaltracker.api.domain.model.TaskGroup;
import com.pivotaltracker.api.exceptions.TrackerException;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 *
 * @author eivar
 */
public class TaskDAOTest
{

    private TaskDAO daoInstance;

    @BeforeTest
    @Parameters({"usertoken"})
    public void setUp(String userToken) {
        daoInstance = new TaskDAO();
        daoInstance.setUserToken(userToken);
    }

    //Get a single task
    @Test
    @Parameters({"project_id", "story_id", "task_id"})
    public void getSingleTaskTest(int projectId, int storyId, int taskId) throws TrackerException {
        System.out.println("getSingleTask");
        Task task = daoInstance.getStory(projectId, storyId, taskId);
        Assert.assertTrue(task instanceof Task);
        Assert.assertEquals(task.getDescription(), "show story tasks state.");
        Assert.assertEquals(task.getId(), taskId);
    }

    //Get all tasks for a story
    @Test
    @Parameters({"project_id", "story_id"})
    public void getAllTasksTest(int projectId, int storyId) throws TrackerException {
        System.out.println("getAllTasks");
        TaskGroup tasks = daoInstance.getTasks(projectId, storyId);
        Assert.assertTrue(tasks instanceof TaskGroup);
        Assert.assertEquals(tasks.getTasks().size(), 2);
    }

    @AfterTest
    public void cleanUp(){
        daoInstance = null;
    }
}
