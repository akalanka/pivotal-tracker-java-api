/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pivotaltracker.api.domain.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import java.util.Calendar;

/**
 *
 * @author eivar
 */
@XStreamAlias("iteration")
public class Iteration
{

    private Integer id, number;
    private Calendar start, finish;
    @XStreamAlias("stories")
    private StoryGroup storyGroup;

    public Iteration()
    {
    }

    public Calendar getFinish()
    {
        return finish;
    }

    public void setFinish(Calendar finish)
    {
        this.finish = finish;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public Integer getNumber()
    {
        return number;
    }

    public void setNumber(Integer number)
    {
        this.number = number;
    }

    public Calendar getStart()
    {
        return start;
    }

    public void setStart(Calendar start)
    {
        this.start = start;
    }

    public StoryGroup getStoryGroup()
    {
        return storyGroup;
    }

    public void setStoryGroup(StoryGroup storyGroup)
    {
        this.storyGroup = storyGroup;
    }
}
