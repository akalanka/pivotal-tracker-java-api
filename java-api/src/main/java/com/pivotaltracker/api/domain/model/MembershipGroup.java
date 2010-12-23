/**
 * This class is requiered for correctly xml serialization and back.
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
@XStreamAlias("memberships")
public final class MembershipGroup extends Group
{

    @XStreamImplicit(itemFieldName = "membership")
    private List<Membership> memberships = new ArrayList<Membership>();

    public MembershipGroup()
    {
    }

    public List<Membership> getMemberships()
    {
        return new ArrayList<Membership>(this.memberships);
    }

    public void addMembership(Membership membership)
    {
        this.memberships.add(membership);
    }
}
