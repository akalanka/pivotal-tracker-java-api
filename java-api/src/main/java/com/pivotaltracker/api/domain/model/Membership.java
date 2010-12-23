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
@XStreamAlias("membership")
public class Membership
{

    private int id;
    private Person person;
    private String role;
    private Project project;

    public Membership(int id, Person person, String role)
    {
        this.id = id;
        this.person = person;
        this.role = role;
    }

    public Membership()
    {
    }

    public int getId()
    {
        return id;
    }

    public Project getProject()
    {
        return project;
    }

    public void setProject(Project project)
    {
        this.project = project;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public Person getPerson()
    {
        return person;
    }

    public void setPerson(Person person)
    {
        this.person = person;
    }

    public String getRole()
    {
        return role;
    }

    public void setRole(String role)
    {
        this.role = role;
    }
}
