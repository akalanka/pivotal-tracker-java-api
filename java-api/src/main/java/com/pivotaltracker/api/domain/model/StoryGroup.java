/**
 * JOX requieres a container variable named as the root tag (for example 
 * <code>private StoryGroup stories</code> and it uses java beans convention names
 * which forces to use certain method names like setStory (even when it is an array setter) for xml parser to work correctly
 */
package com.pivotaltracker.api.domain.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author eivar
 */
@XStreamAlias("stories")
public class StoryGroup extends Group
{

    @XStreamImplicit(itemFieldName = "story")
    private List<Story> stories = new ArrayList<Story>();
    @XStreamAsAttribute
    private int count = 0;
    @XStreamAsAttribute
    private int total = 0;
    @XStreamAsAttribute
    private String filter = "'label:'needs feedback' type:bug";

    public enum SupportedStoryFilter {
        label, type, state, has_attachment, created_since, modified_since, requester,
        owner, id, integration, external_id, has_external_id;
    }
    public List<Story> getStories()
    {
        return stories;
    }

    public void addStory(Story story)
    {
        this.stories.add(story);
    }

    public int getCount()
    {
        return count;
    }

    public void setCount(int count)
    {
        this.count = count;
    }

    public String getFilter()
    {
        return filter;
    }

    public void setFilter(String filter)
    {
        this.filter = filter;
    }

    public int getTotal()
    {
        return total;
    }

    public void setTotal(int total)
    {
        this.total = total;
    }
}
