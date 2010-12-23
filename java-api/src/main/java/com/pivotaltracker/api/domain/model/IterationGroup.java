/**
 * JOX requieres a container variable named as the root tag (for example 
 * <code>private IterationGroup iterations</code> and it uses java beans convention names
 * which forces to use certain method names like setIteration (even when it is an array setter) for xml parser to work correctly
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
@XStreamAlias("iterations")
public final class IterationGroup extends Group
{

    @XStreamImplicit(itemFieldName = "iteration")
    private List<Iteration> iterations = new ArrayList<Iteration>();

    /**
     * Iteration group names defined in pivotal tracker api.
     * @see {@link https://www.pivotaltracker.com/help/api?version=v3#get_iterations}
     */
    public enum GroupName
    {

        done, current, backlog;
    }

    public List<Iteration> getIterations()
    {
        return new ArrayList<Iteration>(iterations);
    }

    public void addIteration(Iteration iteration)
    {
        this.iterations.add(iteration);
    }
}
