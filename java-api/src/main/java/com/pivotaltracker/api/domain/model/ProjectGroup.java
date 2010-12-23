package com.pivotaltracker.api.domain.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author eivar
 */
@XStreamAlias("projects")
public final class ProjectGroup extends Group
{

    @XStreamImplicit(itemFieldName = "project")
    private List<Project> projects = new ArrayList<Project>();

    public List<Project> getProjects()
    {
        return new ArrayList<Project>(this.projects);
    }

    public void addProject(Project project)
    {
        this.projects.add(project);
    }
}
