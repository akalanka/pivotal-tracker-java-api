/**
 * 
 */
package com.pivotaltracker.api.domain.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author anders
 * 
 */
@XStreamAlias("story_type")
public enum StoryType
{

    FEATURE, CHORE, BUG, RELEASE;
}
