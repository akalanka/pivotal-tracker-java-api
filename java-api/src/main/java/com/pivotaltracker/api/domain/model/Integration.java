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
@XStreamAlias("integration")
public class Integration
{

    private Integer id;
    private String type;
    private String name;
    @XStreamAlias("field_name")
    private String fieldName;
    @XStreamAlias("field_label")
    private String fieldLabel;
    private boolean active;

    public Integration()
    {
    }

    public Integration(int id, String type, String name, String fieldName, String fieldLabel, boolean active)
    {
        this.id = id;
        this.type = type;
        this.name = name;
        this.fieldName = fieldName;
        this.fieldLabel = fieldLabel;
        this.active = active;
    }

    public boolean isActive()
    {
        return active;
    }

    public void setActive(boolean active)
    {
        this.active = active;
    }

    public String getFieldLabel()
    {
        return fieldLabel;
    }

    public void setFieldLabel(String fieldLabel)
    {
        this.fieldLabel = fieldLabel;
    }

    public String getFieldName()
    {
        return fieldName;
    }

    public void setFieldName(String fieldName)
    {
        this.fieldName = fieldName;
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

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }
}
