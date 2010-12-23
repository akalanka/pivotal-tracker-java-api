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
public class Note
{

    private Integer id;
    private String text, author;
    @XStreamAlias("noted_at")
    private Calendar noted_at;

    public String getAuthor()
    {
        return author;
    }

    public void setAuthor(String author)
    {
        this.author = author;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public Calendar getNoted_at()
    {
        return noted_at;
    }

    public void setNoted_at(Calendar noted_at)
    {
        this.noted_at = noted_at;
    }

    public String getText()
    {
        return text;
    }

    public void setText(String text)
    {
        this.text = text;
    }
}
