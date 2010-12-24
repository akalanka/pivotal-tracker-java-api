/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pivotaltracker.api.domain;

import com.pivotaltracker.api.domain.model.Integration;
import com.pivotaltracker.api.domain.model.Iteration;
import com.pivotaltracker.api.domain.model.IterationGroup;
import com.pivotaltracker.api.domain.model.Membership;
import com.pivotaltracker.api.domain.model.Person;
import com.pivotaltracker.api.domain.model.Project;
import com.pivotaltracker.api.domain.model.Story;
import java.io.IOException;
import java.io.StringReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 *
 * @author eivar
 */
public class UtilTest {

    private final static String PROJECT_XML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><project><id>1</id><iteration_length type=\"integer\">2</iteration_length><name>Sample Project</name><week_start_day>Monday</week_start_day><point_scale>0,1,2,3</point_scale><account>James Kirks Account</account><velocity_scheme>Average of 4 iterations</velocity_scheme><current_velocity>10</current_velocity><initial_velocity>10</initial_velocity><number_of_done_iterations_to_show>12</number_of_done_iterations_to_show><labels>shields,transporter</labels><allow_attachments>true</allow_attachments><public>false</public><use_https>true</use_https><bugs_and_chores_are_estimatable>false</bugs_and_chores_are_estimatable><commit_mode>false</commit_mode><last_activity_at type=\"datetime\">2010/01/16 17:39:10 CST</last_activity_at><memberships><membership><id>1006</id><person><email>kirkybaby@earth.ufp</email><name>James T. Kirk</name><initials>JTK</initials></person><role>Owner</role></membership></memberships><integrations><integration><id type=\"integer\">2</id><type>Lighthouse</type><name>Lighthouse Feature Bin</name><field_name>lighthouse_id</field_name><field_label>Lighthouse Id</field_label><active>true</active></integration><integration><id type=\"integer\">2</id><type>Lighthouse</type><name>Lighthouse Bug Bin</name><field_name>lighthouse_id</field_name><field_label>Lighthouse Id</field_label><active>false</active></integration><integration><id type=\"integer\">3</id><type>Other</type><name>United Federation of Planets Bug Tracker</name><field_name>other_id</field_name><field_label>United Federation of Planets Bug Tracker Id</field_label><active>true</active></integration></integrations></project>";
    private final static String ITERATIONS_XML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><iterations type=\"array\"><iteration><id type=\"integer\">1</id><number type=\"integer\">1</number><start type=\"datetime\">2009/03/16 00:00:00 UTC</start><finish type=\"datetime\">2009/03/23 00:00:00 UTC</finish><stories><story><id type=\"integer\">1</id><project_id type=\"integer\">1</project_id><story_type>feature</story_type><url>STORY_URL</url><estimate type=\"integer\">2</estimate><current_state>accepted</current_state><description>Windoze Save Dialog thingy</description><name>The Save Dialog</name><requested_by>Dana Deer</requested_by><owned_by>Rob</owned_by><created_at type=\"datetime\">2009/03/16 16:55:04 UTC</created_at><accepted_at type=\"datetime\">2009/03/19 19:00:00 UTC</accepted_at></story></stories></iteration></iterations>";

    @Test
    public void xmlDeclarationTest() {
        String actual = APIUtil.createXMLDeclaration(1.0f, "UTF-8");
        String expected = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void marshalProjectTest() throws IOException, ParseException {
        Project project = new Project();
        project.setId(1);
        project.setName("Sample Project");
        project.setAccount("James Kirks Account");
        project.setAllowAttachments(true);
        project.setCommitMode(false);
        project.setCurrentVelocity(10);
        project.setInitialVelocity(10);
        project.setIterationLength(2);
        project.setLabels("shields,transporter");
        project.setLastActivityAt(createCalendar("2010/01/16 17:39:10 CST", "CST"));
        project.setNumberOfDoneIterationsToShow(12);
        project.setPointScale("0,1,2,3");
        project.setPublic(false);
        project.setUseHttps(true);
        project.setBugsAndChoresAreEstimatable(false);
        project.setVelocityScheme("Average of 4 iterations");
        project.setWeekStartDay("Monday");
        project.addMembership(new Membership(1006,
                new Person("kirkybaby@earth.ufp", "James T. Kirk", "JTK"),
                "Owner"));
        project.addIntegration(new Integration(2, "Lighthouse", "Lighthouse Feature Bin", "lighthouse_id", "Lighthouse Id", true));
        project.addIntegration(new Integration(2, "Lighthouse", "Lighthouse Bug Bin", "lighthouse_id", "Lighthouse Id", false));
        project.addIntegration(new Integration(3, "Other", "United Federation of Planets Bug Tracker", "other_id", "United Federation of Planets Bug Tracker Id", true));
        String expected = PROJECT_XML;
        String actual = APIUtil.createXMLDeclaration(1.0f, "UTF-8")
                + APIUtil.marshallEntityToXML(Project.class, project);
        actual = actual.replaceAll("\n", "").replaceAll(">\\s+<", "><");
        System.out.println("actual: " + actual);
        expected = expected.replaceAll(">\\s+<", "><");
        System.out.println("expected: " + expected);
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void parseIterationTest() throws IOException, ParseException {
        IterationGroup iterations = (IterationGroup) APIUtil.parseEntityFromXML(new StringReader(ITERATIONS_XML));
        Iteration iteration = iterations.getIterations().get(0);
        Assert.assertEquals(iteration.getId(), 1);
        List<Story> stories = iteration.getStoryGroup().getStories();
        Assert.assertEquals(stories.size(), 1);
        Assert.assertEquals(stories.get(0).getId(), 1);
        Assert.assertEquals(stories.get(0).getName(), "The Save Dialog");
        Assert.assertEquals(stories.get(0).getCreatedAt(), createCalendar("2009/03/16 16:55:04 UTC", "UTC"));
    }

    @Test
    public void parseProjectTest() throws IOException, ParseException {
        Project project = (Project) APIUtil.parseEntityFromXML(new StringReader(PROJECT_XML));
        Assert.assertNotNull(project);
        Assert.assertEquals(project.getId(), 1);
        Assert.assertEquals(project.getName(), "Sample Project");
        Assert.assertEquals(project.getLastActivityAt(), createCalendar("2010/01/16 17:39:10 CST", "CST"));
        Assert.assertTrue(project.getIntegrations().size() > 0);
        Assert.assertTrue(project.getMemberships().size() == 1);
        Assert.assertEquals(project.isUseHttps(), true);
    }

    private Calendar createCalendar(final String dateStr, final String timezoneStr) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss z");
        Calendar calendar = new GregorianCalendar(TimeZone.getTimeZone(timezoneStr));
        calendar.setTime(sdf.parse(dateStr));
        return calendar;
    }
}
