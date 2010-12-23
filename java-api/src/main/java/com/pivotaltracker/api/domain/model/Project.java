package com.pivotaltracker.api.domain.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

/**
 * @author anders
 *
 */
@XStreamAlias("project")
public class Project
{

    private int id;
    @XStreamAlias("iteration_length")
    private Integer iterationLength;
    private String name;
    @XStreamAlias("week_start_day")
    private String weekStartDay;
    @XStreamAlias("point_scale")
    private String pointScale;
    private String account;
    @XStreamAlias("velocity_scheme")
    private String velocityScheme;
    @XStreamAlias("current_velocity")
    private int currentVelocity;
    @XStreamAlias("initial_velocity")
    private int initialVelocity;
    @XStreamAlias("number_of_done_iterations_to_show")
    private int numberOfDoneIterationsToShow;
    private String labels;
    @XStreamAlias("allow_attachments")
    private boolean allowAttachments;
    @XStreamAlias("public")
    private boolean _public;
    @XStreamAlias("use_https")
    private boolean useHttps;
    @XStreamAlias("bugs_and_chores_are_estimatable")
    private boolean bugsAndChoresAreEstimatable;
    @XStreamAlias("commit_mode")
    private boolean commitMode;
    @XStreamAlias("last_activity_at")
    private Calendar lastActivityAt;
    private List<Membership> memberships = new ArrayList<Membership>();
    private List<Integration> integrations = new ArrayList<Integration>();
    // <editor-fold defaultstate="collapsed" desc="getters & setters">

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

    public int getIterationLength()
    {
        return iterationLength;
    }

    public void setIterationLength(int iterationLength)
    {
        this.iterationLength = iterationLength;
    }

    public void setIterationLength(Integer iterationLength)
    {
        this.iterationLength = iterationLength;
    }

    public Calendar getLastActivityAt()
    {
        return lastActivityAt;
    }

    public void setLastActivityAt(Calendar lastActivityAt)
    {
        this.lastActivityAt = lastActivityAt;
    }

    public boolean isPublic()
    {
        return _public;
    }

    public void setPublic(boolean _public)
    {
        this._public = _public;
    }

    public String getAccount()
    {
        return account;
    }

    public void setAccount(String account)
    {
        this.account = account;
    }

    public boolean isAllowAttachments()
    {
        return allowAttachments;
    }

    public void setAllowAttachments(boolean allowAttachments)
    {
        this.allowAttachments = allowAttachments;
    }

    public boolean isBugsAndChoresAreEstimatable()
    {
        return bugsAndChoresAreEstimatable;
    }

    public void setBugsAndChoresAreEstimatable(boolean bugsAndChoresAreEstimatable)
    {
        this.bugsAndChoresAreEstimatable = bugsAndChoresAreEstimatable;
    }

    public boolean isCommitMode()
    {
        return commitMode;
    }

    public void setCommitMode(boolean commitMode)
    {
        this.commitMode = commitMode;
    }

    public int getCurrentVelocity()
    {
        return currentVelocity;
    }

    public void setCurrentVelocity(int currentVelocity)
    {
        this.currentVelocity = currentVelocity;
    }

    public int getInitialVelocity()
    {
        return initialVelocity;
    }

    public void setInitialVelocity(int initialVelocity)
    {
        this.initialVelocity = initialVelocity;
    }

    public String getLabels()
    {
        return labels;
    }

    public void setLabels(String labels)
    {
        this.labels = labels;
    }

    public int getNumberOfDoneIterationsToShow()
    {
        return numberOfDoneIterationsToShow;
    }

    public void setNumberOfDoneIterationsToShow(int numberOfDoneIterationsToShow)
    {
        this.numberOfDoneIterationsToShow = numberOfDoneIterationsToShow;
    }

    public String getPointScale()
    {
        return pointScale;
    }

    public void setPointScale(String pointScale)
    {
        this.pointScale = pointScale;
    }

    public boolean isUseHttps()
    {
        return useHttps;
    }

    public void setUseHttps(boolean userHttps)
    {
        this.useHttps = userHttps;
    }

    public String getVelocityScheme()
    {
        return velocityScheme;
    }

    public void setVelocityScheme(String velocityScheme)
    {
        this.velocityScheme = velocityScheme;
    }

    public String getWeekStartDay()
    {
        return weekStartDay;
    }

    public void setWeekStartDay(String weekStartDay)
    {
        this.weekStartDay = weekStartDay;
    }

    public List<Integration> getIntegrations()
    {
        return new ArrayList<Integration>(this.integrations);
    }

    public List<Membership> getMemberships()
    {
        return new ArrayList<Membership>(this.memberships);
    }

    public void addIntegration(Integration integration)
    {
        integrations.add(integration);
    }

    public void addMembership(Membership membership)
    {
        memberships.add(membership);
    }
    // </editor-fold>
}
