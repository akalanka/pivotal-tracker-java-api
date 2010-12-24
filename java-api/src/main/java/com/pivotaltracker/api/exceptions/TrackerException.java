/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pivotaltracker.api.exceptions;

/**
 *
 * @author eivar
 */
public class TrackerException extends Exception
{

    public TrackerException()
    {
        super();
    }

    public TrackerException(String message)
    {
        super(message);
    }

    public TrackerException(Throwable throwable)
    {
        super(throwable);
    }

    public TrackerException(String message, Throwable throwable)
    {
        super(message, throwable);
    }
}
