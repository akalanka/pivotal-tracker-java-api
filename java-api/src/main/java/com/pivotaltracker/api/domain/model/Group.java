/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pivotaltracker.api.domain.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 *
 * @author eivar
 */
public abstract class Group
{

    @XStreamAlias("type")
    @XStreamAsAttribute
    private final String TYPE = "array";

    public String getType()
    {
        return TYPE;
    }
}
