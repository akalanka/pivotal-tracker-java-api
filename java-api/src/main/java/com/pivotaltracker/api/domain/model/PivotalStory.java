/**
 * 
 */
package com.pivotaltracker.api.domain.model;

import java.util.Date;


/**
 * @author anders
 *
 */
public class PivotalStory {
    private PivotalStoryType storyType;

    private String name;

    private String description;

    private String requestor;

    private String labels;

    private int estimate;

    private String url;

    /// <remarks/>
//    [System.Xml.Serialization.XmlElementAttribute("current_state")]
    private String currentState;

    /// <remarks/>
//    [System.Xml.Serialization.XmlElementAttribute("creation_date", DataType = "date")]
    private Date CreationDate;

    public PivotalStoryType getStoryType() {
        return storyType;
    }

    public void setStoryType(PivotalStoryType storyType) {
        this.storyType = storyType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRequestor() {
        return requestor;
    }

    public void setRequestor(String requestor) {
        this.requestor = requestor;
    }

    public String getLabels() {
        return labels;
    }

    public void setLabels(String labels) {
        this.labels = labels;
    }

    public int getEstimate() {
        return estimate;
    }

    public void setEstimate(int estimate) {
        this.estimate = estimate;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCurrentState() {
        return currentState;
    }

    public void setCurrentState(String currentState) {
        this.currentState = currentState;
    }

    public Date getCreationDate() {
        return CreationDate;
    }

    public void setCreationDate(Date creationDate) {
        CreationDate = creationDate;
    }

    public int getStoryId() {
        return StoryId;
    }

    public void setStoryId(int storyId) {
        StoryId = storyId;
    }

    private int StoryId;
}
