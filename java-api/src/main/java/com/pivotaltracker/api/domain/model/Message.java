/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pivotaltracker.api.domain.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 *
 * @author eivar
 */
@XStreamAlias("message")
public final class Message
{

    private String content;

    public String getContent()
    {
        return content;
    }

    public void setContent(String message)
    {
        this.content = message;
    }

    @Override
    public String toString()
    {
        return "Message{" + "content=" + content + '}';
    }
}
