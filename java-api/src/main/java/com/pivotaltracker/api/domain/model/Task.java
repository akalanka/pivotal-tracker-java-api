/**
 * 
 */
package com.pivotaltracker.api.domain.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author anders
 *
 */
@XStreamAlias("task")
public class Task
{

    private Integer id;
    private String description;
    private int position;
    private boolean complete;
    @XStreamAlias("created_at")
    private Calendar createdAt;

    public boolean isComplete()
    {
        return complete;
    }

    public void setComplete(boolean complete)
    {
        this.complete = complete;
    }

    public Calendar getCreatedAt()
    {
        return createdAt;
    }

    public void setCreatedAt(Calendar createdAt)
    {
        this.createdAt = createdAt;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getPosition()
    {
        return position;
    }

    public void setPosition(int position)
    {
        this.position = position;
    }
}
