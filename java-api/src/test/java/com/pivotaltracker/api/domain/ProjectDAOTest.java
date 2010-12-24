/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pivotaltracker.api.domain;

import com.pivotaltracker.api.exceptions.TrackerException;
import com.pivotaltracker.api.domain.model.IterationGroup;
import com.pivotaltracker.api.domain.model.Membership;
import com.pivotaltracker.api.domain.model.MembershipGroup;
import com.pivotaltracker.api.domain.model.Project;
import com.pivotaltracker.api.domain.model.ProjectGroup;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 *
 * @author eivar
 */
public class ProjectDAOTest
{

    private ProjectDAO daoInstance;

    @BeforeTest
    @Parameters({"usertoken"})
    public void setUp(String userToken) throws Exception
    {
        daoInstance = new ProjectDAO();
        daoInstance.setUserToken(userToken);
    }

    @Test
    @Parameters({"project_id"})
    public void getProjectTest(int projectId)
    {
        try {
            System.out.println("getProject");
            Project result = daoInstance.getProject(projectId);
            Assert.assertEquals("pivotaltracker-java-api", result.getName());
        } catch (TrackerException ex) {
            Assert.fail("get project test fail", ex);
        }
    }

    @Test
    public void getProjectsTest() throws TrackerException
    {
        try {
            System.out.println("getProjects");
            ProjectGroup result = daoInstance.getProjects();
            Assert.assertEquals(result.getProjects().size(), 1);
        } catch (TrackerException ex) {
            Assert.fail("get projects test fail", ex);
        }
    }

    @Test
    @Parameters({"project_id"})
    public void getMembershipsTest(int projectId)
    {
        try {
            System.out.println("getMemberships");
            MembershipGroup result = daoInstance.getMemberships(projectId);
            Assert.assertTrue(result instanceof MembershipGroup);
            Assert.assertEquals(result.getMemberships().size(), 1);
        } catch (TrackerException ex) {
            Assert.fail("get memberships test fail", ex);
        }
    }

    @Test
    @Parameters({"project_id"})
    public void getMembershipTest(int projectId) throws TrackerException
    {
        System.out.println("getMembership");
        int membershipId = 494633;
        String expPersonName = "Eivar Montenegro";
        Membership result = daoInstance.getMembership(projectId, membershipId);
        Assert.assertTrue(result instanceof Membership);
        Assert.assertEquals(result.getId(), membershipId);
        Assert.assertEquals(result.getPerson().getName(), expPersonName);
    }

    @Test
    @Parameters({"project_id"})
    public void getIterationsByLimitAndOffset3ArgsTest(int projectId) throws TrackerException
    {
        System.out.println("getIterations 3 args");
        int limit = 10;
        int offset = 1;
        IterationGroup result = daoInstance.getIterations(projectId, limit, offset);
        Assert.assertTrue(result instanceof IterationGroup);
        Assert.assertTrue(result.getIterations().size() <= 10);
    }

    @Test
    @Parameters({"project_id"})
    public void getIterationsProjectTest(int projectId) throws TrackerException
    {
        System.out.println("getIterations by project");
        IterationGroup result = daoInstance.getIterations(projectId);
        Assert.assertTrue(result instanceof IterationGroup);
        Assert.assertTrue(result.getIterations().size() >= 1);
    }

    @Test
    @Parameters({"project_id"})
    public void getIterationsGroupTest(int projectId) throws TrackerException
    {
        System.out.println("getIterations by group");
        IterationGroup result = daoInstance.getIterationsByGroup(projectId, IterationGroup.GroupName.current);
        Assert.assertTrue(result instanceof IterationGroup);
        Assert.assertTrue(result.getIterations().size() > 0);
    }

    @AfterTest
    public void cleanUp()
    {
        daoInstance = null;
    }
}
