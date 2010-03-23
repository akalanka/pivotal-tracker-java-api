/**
 * 
 */
package com.pivotaltracker.api.domain.model;

import java.util.Date;
import java.util.List;

/**
 * @author anders
 *
 */
public class PivotalTask {
    private String storyId;

    private int taskId;

    private int position;

    private String description;

    private Boolean complete;

    public Date CreationDate;

    public List<PivotalTask> tasks;

    public String getStoryId() {
        return storyId;
    }

    public void setStoryId(String storyId) {
        this.storyId = storyId;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getComplete() {
        return complete;
    }

    public void setComplete(Boolean complete) {
        this.complete = complete;
    }

    public Date getCreationDate() {
        return CreationDate;
    }

    public void setCreationDate(Date creationDate) {
        CreationDate = creationDate;
    }

    public List<PivotalTask> getTasks() {
        return tasks;
    }

    public void setTasks(List<PivotalTask> tasks) {
        this.tasks = tasks;
    }

}
