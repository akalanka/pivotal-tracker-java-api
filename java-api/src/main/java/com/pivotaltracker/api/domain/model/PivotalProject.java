/**
 * 
 */
package com.pivotaltracker.api.domain.model;

import java.util.List;

/**
 * @author anders
 *
 */
public class PivotalProject {

    private List<PivotalStory> stories;
    
    public List<PivotalStory> getAllStories() {
        return stories;
    }

    public void setStories(List<PivotalStory> stories) {
        this.stories = stories;
    }
}
