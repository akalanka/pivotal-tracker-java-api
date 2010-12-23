/**
 * 
 */
package com.pivotaltracker.api.domain.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import java.util.Calendar;

/**
 * @author anders
 *
 */
@XStreamAlias("story")
public class Story
{

//  <story>
//    <lighthouse_id>43</lighthouse_id>
//    <lighthouse_url>http://mylighthouseapp.com/projects/100/tickets/43</lighthouse_url>
    private Integer id;
    @XStreamAlias("project_id")
    private Integer projectId;
    @XStreamAlias("story_type")
    private StoryType storyType;
    private String url;
    private Integer estimate;
    @XStreamAlias("current_state")
    private String currentState;
    private String description;
    private String name;
    @XStreamAlias("requested_by")
    private String requestedBy;
    @XStreamAlias("owned_by")
    private String ownedBy;
    @XStreamAlias("created_at")
    private Calendar createdAt;
    @XStreamAlias("accepted_at")
    private Calendar acceptedAt;
    @XStreamAlias("updated_at")
    private Calendar updatedAt;
    private AttachmentGroup attachments;
    private String labels;
    private NoteGroup notes;
    private TaskGroup tasks;

    public NoteGroup getNotes()
    {
        return notes;
    }

    public void setNotes(NoteGroup notes)
    {
        this.notes = notes;
    }

    public TaskGroup getTasks()
    {
        return tasks;
    }

    public void setTasks(TaskGroup tasks)
    {
        this.tasks = tasks;
    }

    public String getLabels()
    {
        return labels;
    }

    public void setLabels(String labels)
    {
        this.labels = labels;
    }

    public AttachmentGroup getAttachments()
    {
        return attachments;
    }

    public void setAttachments(AttachmentGroup attachments)
    {
        this.attachments = attachments;
    }

    public Calendar getAcceptedAt()
    {
        return acceptedAt;
    }

    public void setAcceptedAt(Calendar acceptedAt)
    {
        this.acceptedAt = acceptedAt;
    }

    public Calendar getCreatedAt()
    {
        return createdAt;
    }

    public void setCreatedAt(Calendar createdAt)
    {
        this.createdAt = createdAt;
    }

    public String getCurrentState()
    {
        return currentState;
    }

    public void setCurrentState(String currentState)
    {
        this.currentState = currentState;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public int getEstimate()
    {
        return estimate;
    }

    public void setEstimate(int estimate)
    {
        this.estimate = estimate;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getOwnedBy()
    {
        return ownedBy;
    }

    public void setOwnedBy(String ownedBy)
    {
        this.ownedBy = ownedBy;
    }

    public Integer getProjectId()
    {
        return projectId;
    }

    public void setProjectId(Integer projectId)
    {
        this.projectId = projectId;
    }

    public String getRequestedBy()
    {
        return requestedBy;
    }

    public void setRequestedBy(String requestedBy)
    {
        this.requestedBy = requestedBy;
    }

    public StoryType getStoryType()
    {
        return storyType;
    }

    public void setStoryType(StoryType storyType)
    {
        this.storyType = storyType;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public Calendar getUpdatedAt()
    {
        return updatedAt;
    }

    public void setUpdatedAt(Calendar updatedAt)
    {
        this.updatedAt = updatedAt;
    }
}
