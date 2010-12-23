/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pivotaltracker.api.domain.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author eivar
 */
@XStreamAlias("tasks")
public class TaskGroup extends Group {
    @XStreamImplicit(itemFieldName = "task")
    private List<Task> tasks = new ArrayList<Task>();

    public List<Task> getTasks()
    {
        return new ArrayList<Task>(tasks);
    }

    public void addTask(Task task)
    {
        this.tasks.add(task);
    }
}
