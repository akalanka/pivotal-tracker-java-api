/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pivotaltracker.api.domain.model;

/**
 *
 * @author eivar
 */
public class Person
{

    private String email, name, initials;

    public Person()
    {
    }

    public Person(String email, String name, String initials)
    {
        this.email = email;
        this.name = name;
        this.initials = initials;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getInitials()
    {
        return initials;
    }

    public void setInitials(String initials)
    {
        this.initials = initials;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}
